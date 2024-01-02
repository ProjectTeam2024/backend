package kr.project.backend.service.user;

import io.jsonwebtoken.ExpiredJwtException;
import kr.project.backend.common.ResponseEntity;
import kr.project.backend.dto.user.UserLoginRequestDto;
import kr.project.backend.dto.user.UserRefreshTokenRequestDto;
import kr.project.backend.dto.user.UserTokenResponseDto;
import kr.project.backend.entity.user.RefreshToken;
import kr.project.backend.entity.user.User;
import kr.project.backend.exception.CommonErrorCode;
import kr.project.backend.exception.CommonException;
import kr.project.backend.repository.user.UserRepository;
import kr.project.backend.repository.user.RefreshTokenRepository;
import kr.project.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 회원가입, 로그인 API seriveImpl
 *
 * @author kh
 * @version v1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    private final long expiredMs = 1000 * 60;

    private final long accesTokenTime = 5L;

    private final long refreshTokenTime = 600L;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public UserTokenResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {


        //등록되어 있는 유저인지 아닌지 판단
        boolean checkUserInfo = userRepository.existsByUserCino(userLoginRequestDto.getUserCino());

        //등록되어 있지 않는 유저
        if (!checkUserInfo) {
            //회원가입
            UUID userId = userRepository.save(new User(userLoginRequestDto)).getUserId();

            //방금 회원가입 된 유저 정보 가져오기.
            User userInfo = userRepository.findById(userId)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
            //응답 토큰 세팅(리스레시 토큰은 키값으로 응답)
            String accessToken = JwtUtil.createJwt(userId, userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * accesTokenTime);
            String refreshToken = JwtUtil.createJwt(userId, userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * refreshTokenTime);

            //리프레시 토큰 저장
            refreshTokenRepository.save(new RefreshToken(refreshToken, userInfo));

            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_TOKEN.getCode(), CommonErrorCode.NOT_EXIST_TOKEN.getMessage()));

            return new UserTokenResponseDto(accessToken, String.valueOf(refreshTokenInfo.getRefreshTokenId()));
        }
        //등록되어 있는 유저

        //정보 조회
        User userInfo = userRepository.findByUserCino(userLoginRequestDto.getUserCino())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        String accessToken = JwtUtil.createJwt(userInfo.getUserId(), userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * accesTokenTime);
        String refreshToken = JwtUtil.createJwt(userInfo.getUserId(), userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * refreshTokenTime);

        RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_TOKEN.getCode(), CommonErrorCode.NOT_EXIST_TOKEN.getMessage()));

        //dirty checking 으로 인한 리프레시토큰 업데이트
        refreshTokenInfo.updateRefreshToken(refreshToken);

        return new UserTokenResponseDto(accessToken, String.valueOf(refreshTokenInfo.getRefreshTokenId()));
    }

    @Transactional
    public UserTokenResponseDto refreshAuthorize(UserRefreshTokenRequestDto userRefreshTokenRequestDto) {

        //리프레시토큰키값으로 리프레시 토큰 조회
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenId(userRefreshTokenRequestDto.getRefreshTokenId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_TOKEN.getCode(), CommonErrorCode.NOT_EXIST_TOKEN.getMessage()));

        //회원정보
        User userInfo = userRepository.findById(refreshToken.getUser().getUserId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        //토큰 유효성검사
        try {
            JwtUtil.isExpired(refreshToken.getRefreshToken(), jwtSecretKey);
        } catch (ExpiredJwtException e) {
            //만료시 refreshToken 재발급(db 업데이트)
            String newRefreshToken = JwtUtil.createJwt(userInfo.getUserId(), userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * refreshTokenTime);

            //리프레시 토큰 저장
            refreshTokenRepository.save(new RefreshToken(newRefreshToken, userInfo));
        }

        //토큰 재발급
        String accessToken = JwtUtil.createJwt(userInfo.getUserId(), userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * accesTokenTime);
        String refreshTokenId = String.valueOf(refreshToken.getRefreshTokenId());

        return new UserTokenResponseDto(accessToken, refreshTokenId);
    }

    @Transactional
    public UserTokenResponseDto test() {

        String accessToken = "11111";
        String refreshTokenId = "22222";

        //예측하지 못한에러 예시
        String sizeStr = null;
        int sizeInt = 10;
        try {
            int test = Integer.parseInt(sizeStr) / sizeInt;
        }catch (Exception e){
            log.info("에러 발생 -> httpstatus 200 but api자체는 실패");
        }


        return new UserTokenResponseDto(accessToken, refreshTokenId);
    }
}
