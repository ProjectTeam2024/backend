package kr.project.backend.user.account.service;

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

        User findUser = userRepository.findByUserCino(user.getUserCino());

        if(findUser == null){
            //회원가입
            UUID userId = userRepository.save(user).getUserId();

            //응답 토큰 세팅(리스레시 토큰은 키값으로 응답)

            //accountRepository.save()
            //RefreshToken refreshTokenData = accountRepository.findByUserId(joinUserData.getUserId());

            accessToken = JwtUtil.createJwt(userId,user.getUserEmail(),user.getUserName(), jwtSecretKey, expiredMs*accesTokenTime);
            refreshToken = JwtUtil.createJwt(userId,user.getUserEmail(),user.getUserName(), jwtSecretKey, expiredMs*refreshTokenTime);

            User userInfo = userRepository.findById(userId).orElse(null);
            if(!ObjectUtils.isEmpty(userInfo)){
                refreshTokenRepository.save(new RefreshToken(refreshToken,userInfo));
            }
        }else{
            //응답 토큰 세팅(리스레시 토큰은 키값으로 응답)
            accessToken = JwtUtil.createJwt(findUser.getUserId(),findUser.getUserEmail(),findUser.getUserName(), jwtSecretKey, expiredMs*accesTokenTime);
            refreshToken = JwtUtil.createJwt(findUser.getUserId(),findUser.getUserEmail(),findUser.getUserName(), jwtSecretKey, expiredMs*refreshTokenTime);
        }

        UserToken userToken = new UserToken();

        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);

        r.setCode("0000");
        r.setMsg("success");
        r.setResult(userToken);

        return r;
    }
}
