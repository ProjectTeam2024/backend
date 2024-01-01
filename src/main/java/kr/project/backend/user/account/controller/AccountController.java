package kr.project.backend.user.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.project.backend.common.Environment;
import kr.project.backend.common.Response;
import kr.project.backend.security.model.ServiceUser;
import kr.project.backend.common.UserToken;
import kr.project.backend.user.account.entity.RefreshToken;
import kr.project.backend.user.account.service.AccountService;
import kr.project.backend.user.account.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "일반 로그인",description = "일반 로그인 입니다.")
    @PostMapping("/login")
    public Response<UserToken> login(@Valid @RequestBody User user){
        Response<UserToken> r = accountService.userLogin(user);
        return r;
    }

    @Operation(summary = "accessToken 재발급",description = "refreshToken을 통해 accessToken을 재발급 합니다.")
    @PostMapping("/refresh/authorize")
    public Response<UserToken> refreshAuthorize(@Valid @RequestBody RefreshToken refreshToken){
        Response<UserToken> r = accountService.refreshAuthorize(refreshToken);
        return r;
    }

    
}