package kr.project.backend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.project.backend.common.Environment;
import kr.project.backend.dto.user.UserLoginRequestDto;
import kr.project.backend.dto.user.UserRefreshTokenRequestDto;
import kr.project.backend.dto.user.UserTokenResponseDto;
import kr.project.backend.entity.user.RefreshToken;
import kr.project.backend.entity.user.User;
import kr.project.backend.results.ObjectResult;
import kr.project.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return ObjectResult.build(userService.test());
    }
    
}