package kr.project.backend.user.account.service;

import kr.project.backend.common.Response;
import kr.project.backend.user.account.model.UserToken;
import kr.project.backend.user.common.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Response<UserToken> userLogin(User user) {
        Response<UserToken> r = new Response<>();

        UserToken userToken = new UserToken();
        userToken.setWpayToken("test1111");
        userToken.setWpayRefreshToken("test2222");

        r.setCode("0000");
        r.setMsg("success");
        r.setResult(userToken);

        return r;
    }
}
