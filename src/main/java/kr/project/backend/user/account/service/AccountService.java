package kr.project.backend.user.account.service;

import kr.project.backend.common.Response;
import kr.project.backend.common.UserToken;
import kr.project.backend.user.account.entity.User;

/**
 * 회원가입, 로그인 API serive interface
 * @author kh
 * @version v1.0
 */
public interface AccountService {

    Response<UserToken> userLogin(User user);
}
