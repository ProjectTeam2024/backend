package kr.project.backend.user.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.project.backend.common.Environment;
import kr.project.backend.common.Response;
import kr.project.backend.user.account.model.UserToken;
import kr.project.backend.user.account.service.AccountService;
import kr.project.backend.common.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인, 회원가입 API
 * @author kh
 * @version v1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/" + Environment.API_VERSION + "/account")
@RequiredArgsConstructor
public class AccountController {

    final AccountService accountService;

    @Operation(summary = "일반 로그인",description = "일반 로그인 입니다. (소셜로그인 제외)")
    @PostMapping("/login")
    public Response<UserToken> login(@RequestBody User user){
        Response<UserToken> r = accountService.userLogin(user);
        return r;
    }

    
}