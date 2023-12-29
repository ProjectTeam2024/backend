package kr.project.backend.user.account.service;

import kr.project.backend.common.Response;
import kr.project.backend.security.model.ServiceUser;
import kr.project.backend.user.account.model.UserToken;
import kr.project.backend.common.User;
import kr.project.backend.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Override
    public Response<UserToken> userLogin(User user) {
        Response<UserToken> r = new Response<>();

        //TODO 회원 검증로직 추가
        ServiceUser serviceUser = new ServiceUser();

        UserToken userToken = new UserToken();

        String accessToken = JwtUtil.createJwt("CS00000001","testmail@naver.com","홍길동", jwtSecretKey, expiredMs*5L);
        String refreshToken = JwtUtil.createJwt("CS00000001","testmail@naver.com","홍길동", jwtSecretKey, expiredMs*600L);

        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);

        r.setCode("0000");
        r.setMsg("success");
        r.setResult(userToken);

        return r;
    }
}
