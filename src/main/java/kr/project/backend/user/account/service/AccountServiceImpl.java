package kr.project.backend.user.account.service;

import io.jsonwebtoken.ExpiredJwtException;
import kr.project.backend.common.Response;
import kr.project.backend.common.UserToken;
import kr.project.backend.user.account.entity.RefreshToken;
import kr.project.backend.user.account.entity.User;
import kr.project.backend.user.account.repository.UserRepository;
import kr.project.backend.user.account.repository.RefreshTokenRepository;
import kr.project.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 회원가입, 로그인 API seriveImpl
 * @author kh
 * @version v1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    private long expiredMs = 1000 * 60;

    private long accesTokenTime = 5L;

    private long refreshTokenTime = 600L;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public Response<UserToken> userLogin(User user) {
        Response<UserToken> r = new Response<>();

        String accessToken = null;
        String refreshToken = null;
        String refreshTokenId = null;

        User findUser = userRepository.findByUserCino(user.getUserCino());

        if(findUser == null){
            //회원가입
            UUID userId = userRepository.save(user).getUserId();

            //응답 토큰 세팅(리스레시 토큰은 키값으로 응답)
            accessToken = JwtUtil.createJwt(userId,user.getUserEmail(),user.getUserName(), jwtSecretKey, expiredMs*accesTokenTime);
            refreshToken = JwtUtil.createJwt(userId,user.getUserEmail(),user.getUserName(), jwtSecretKey, expiredMs*refreshTokenTime);

            User userInfo = userRepository.findById(userId).orElse(null);

            if(!ObjectUtils.isEmpty(userInfo)){
                //리프레시 토큰 저장
                refreshTokenRepository.save(new RefreshToken(refreshToken,userInfo));
            }

            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo);

            refreshTokenId = String.valueOf(refreshTokenInfo.getRefreshTokenId());

        }else{
            //응답 토큰 세팅(리스레시 토큰은 키값으로 응답)
            accessToken = JwtUtil.createJwt(findUser.getUserId(),findUser.getUserEmail(),findUser.getUserName(), jwtSecretKey, expiredMs*accesTokenTime);
            refreshToken = JwtUtil.createJwt(findUser.getUserId(),findUser.getUserEmail(),findUser.getUserName(), jwtSecretKey, expiredMs*refreshTokenTime);

            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(findUser);

            refreshTokenInfo.updateRefreshToken(refreshToken);
            //리프레시 토큰 저장
            refreshTokenRepository.save(refreshTokenInfo);

            refreshTokenId = String.valueOf(refreshTokenInfo.getRefreshTokenId());
        }

        UserToken userToken = new UserToken();

        userToken.setAccessToken(accessToken);
        userToken.setRefreshTokenId(refreshTokenId);

        r.setCode("0000");
        r.setMsg("success");
        r.setResult(userToken);

        return r;
    }

    @Override
    public Response<UserToken> refreshAuthorize(RefreshToken refreshToken){
        Response<UserToken> r = new Response<>();
        UserToken userToken = new UserToken();

        String accessToken = null;
        String newRefreshToken = null;
        String refreshTokenId = null;

        //리프레시토큰키값으로 리프레시 토큰 조회
        RefreshToken refreshTokenData = refreshTokenRepository.findByRefreshTokenId(refreshToken.getRefreshTokenId());

        if(refreshTokenData == null){
            r.setCode("8001");
            r.setMsg("유효하지 않는 토큰입니다.");
        }else{
            //회원정보
            User userInfo = userRepository.findById(refreshTokenData.getUser().getUserId()).orElse(null);

            //토큰 유효성검사
            try {
                JwtUtil.isExpired(refreshTokenData.getRefreshToken(), jwtSecretKey);
            }catch (ExpiredJwtException e){
                //만료시 refreshToken 재발급(db 업데이트)
                newRefreshToken = JwtUtil.createJwt(userInfo.getUserId(),userInfo.getUserEmail(),userInfo.getUserName(), jwtSecretKey, expiredMs*refreshTokenTime);

                //리프레시 토큰 저장
                refreshTokenRepository.save(new RefreshToken(newRefreshToken,userInfo));
            }

            //토큰 재발급
            accessToken = JwtUtil.createJwt(userInfo.getUserId(),userInfo.getUserEmail(),userInfo.getUserName(), jwtSecretKey, expiredMs*accesTokenTime);
            refreshTokenId = String.valueOf(refreshToken.getRefreshTokenId());
            userToken.setAccessToken(accessToken);
            userToken.setRefreshTokenId(refreshTokenId);

            r.setCode("0000");
            r.setMsg("success");
            r.setResult(userToken);
        }

        return r;
    }
}
