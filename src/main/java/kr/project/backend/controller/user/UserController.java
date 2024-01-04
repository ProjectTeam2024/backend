package kr.project.backend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.project.backend.auth.ServiceUser;
import kr.project.backend.common.Environment;
import kr.project.backend.dto.user.request.UserLoginRequestDto;
import kr.project.backend.dto.user.request.UserRefreshTokenRequestDto;
import kr.project.backend.results.ObjectResult;
import kr.project.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인, 회원가입 API
 * @author kh
 * @version v1.0
 */

@Tag(name = "account", description = "로그인 / 회원가입")
@Slf4j
@RestController
@RequestMapping("/api/" + Environment.API_VERSION + "/account")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

    @Operation(summary = "일반 로그인",description = "일반 로그인 입니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){
        return ObjectResult.build(userService.userLogin(userLoginRequestDto));

    }

    @Operation(summary = "accessToken 재발급",description = "refreshToken을 통해 accessToken을 재발급 합니다.")
    @PostMapping("/refresh/authorize")
    public ResponseEntity<?> refreshAuthorize(@Valid @RequestBody UserRefreshTokenRequestDto userRefreshTokenRequestDto){
        return ObjectResult.build(userService.refreshAuthorize(userRefreshTokenRequestDto));
    }

    @Operation(summary = "로그아웃",description = "로그아웃 입니다.")
    @PostMapping("/user/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal ServiceUser serviceUser){
        userService.logout(serviceUser);
        return ObjectResult.ok();
    }

    @Operation(summary = "회원탈퇴",description = "회원탈퇴 입니다.")
    @PostMapping("/user/drop")
    public ResponseEntity<?> dropUser(@AuthenticationPrincipal ServiceUser serviceUser){
        userService.dropUser(serviceUser);
        return ObjectResult.ok();
    }

    @Operation(summary = "회원상태 체크",description = "회원상태 체크 입니다.")
    @GetMapping("/user/state/check")
    public ResponseEntity<?> userStateCheck(@AuthenticationPrincipal ServiceUser serviceUser){
        return ObjectResult.build(userService.userStateCheck(serviceUser));
    }
    
}