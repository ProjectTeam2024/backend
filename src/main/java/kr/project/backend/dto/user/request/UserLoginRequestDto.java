package kr.project.backend.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequestDto {;

    @NotBlank(message = "이메일을 넣어주세요.")
    @Schema(description = "이메일", example = "test@test.com")
    private String userEmail;

    @NotBlank(message = "푸쉬 토큰을 넣어주세요.")
    @Schema(description = "푸쉬 토큰", example = "asWERds123/sdkmmal2WED/sdmpPalm")
    private String userPushToken;

    @NotBlank(message = "회원가입 구분을 넣어주세요.")
    @Schema(description = "회원가입 구분", example = "01")
    private String userJoinKind;
}
