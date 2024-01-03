package kr.project.backend.service.user;

import io.jsonwebtoken.ExpiredJwtException;
import kr.project.backend.auth.ServiceUser;
import kr.project.backend.common.Constants;
import kr.project.backend.dto.user.UserLoginRequestDto;
import kr.project.backend.dto.user.UserRefreshTokenRequestDto;
import kr.project.backend.dto.user.UserTokenResponseDto;
import kr.project.backend.entity.user.DropUser;
import kr.project.backend.entity.user.RefreshToken;
import kr.project.backend.entity.user.User;
import kr.project.backend.exception.CommonErrorCode;
import kr.project.backend.exception.CommonException;
import kr.project.backend.repository.user.DropUserRepository;
import kr.project.backend.repository.user.UserRepository;
import kr.project.backend.repository.user.RefreshTokenRepository;
import kr.project.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    private final Integer reJoinTermDate = 30;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final DropUserRepository dropUserRepository;

    @Transactional
    public UserTokenResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {

        //등록되어 있는 유저인지 아닌지 판단
        boolean checkUserInfo = userRepository.existsByUserCino(userLoginRequestDto.getUserCino());

        //등록되어 있지 않는 유저
        if (!checkUserInfo) {
            //회원가입 1달 제한 정책 체크
            DropUser dropCheck = dropUserRepository.findByUserCino(userLoginRequestDto.getUserCino()).orElse(null);

            if(dropCheck != null){
                try {
                    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dropDate = transFormat.parse(dropCheck.getDropDttm());
                    Date nowDate = transFormat.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                    Long dropAndJoinTerm = (nowDate.getTime() - dropDate.getTime()) / 1000 / (24*60*60);

                    if(reJoinTermDate > dropAndJoinTerm.intValue()){
                        throw new CommonException(CommonErrorCode.JOIN_TERM_DATE.getCode(),CommonErrorCode.JOIN_TERM_DATE.getMessage());
                    }
                }catch (ParseException e){
                    log.info("date 변환 파싱 error");
                }
            }

            //회원가입
            userLoginRequestDto.setUserState(Constants.USER_STATE.ACTIVE_USER);
            userLoginRequestDto.setUserLogoutDttm("");
            UUID userId = userRepository.save(new User(userLoginRequestDto)).getUserId();

            //방금 회원가입 된 유저 정보 가져오기
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

        refreshTokenRepository.save(refreshTokenInfo);

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
            refreshToken.updateRefreshToken(newRefreshToken);
        }

        //토큰 재발급
        String accessToken = JwtUtil.createJwt(userInfo.getUserId(), userInfo.getUserEmail(), userInfo.getUserName(), jwtSecretKey, expiredMs * accesTokenTime);
        String refreshTokenId = String.valueOf(refreshToken.getRefreshTokenId());

        return new UserTokenResponseDto(accessToken, refreshTokenId);
    }

    @Transactional
    public void logout(ServiceUser serviceUser) {

        //회원정보
        User userInfo = userRepository.findById(UUID.fromString(serviceUser.getUserId()))
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        //로그아웃시간 업데이트
        userInfo.updateUserLogoutDttm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        userRepository.save(userInfo);
    }

    @Transactional
    public void dropUser(ServiceUser serviceUser) {

        //회원정보
        User userInfo = userRepository.findById(UUID.fromString(serviceUser.getUserId()))
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        //탈퇴 중복 처리 방어로직
        if(userInfo.getUserState().equals(Constants.USER_STATE.DROP_USER)){
            throw new CommonException(CommonErrorCode.ALREADY_DROP_USER.getCode(), CommonErrorCode.ALREADY_DROP_USER.getMessage());
        }

        RefreshToken refreshToken = refreshTokenRepository.findByUser(userInfo)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOKEN.getCode(), CommonErrorCode.NOT_FOUND_TOKEN.getMessage()));


        userInfo.updateUserDrop();

        //회원상태 업데이트(기본 개인정보 삭제)
        userRepository.save(userInfo);

        //탈퇴 테이블 저장
        dropUserRepository.save(new DropUser(userInfo));

        //리프레시 테이블 삭제
        refreshTokenRepository.delete(refreshToken);
    }
    
}
