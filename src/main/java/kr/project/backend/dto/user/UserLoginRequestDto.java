package kr.project.backend.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "이메일을 입력하세요.")
    @Schema(description = "이메일", example = "test@test.com")
    private String userEmail;
    @NotBlank(message = "이름을 입력하세요.")
    @Schema(description = "이름", example = "홍길동")
    private String userName;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Schema(description = "비밀번호", example = "password")
    private String userPassword;
    @NotBlank(message = "푸쉬 토큰을 넣어주세요.")
    @Schema(description = "푸쉬 토큰", example = "123123")
    private String userPushToken;
    @NotBlank(message = "유저 cino를 넣어주세요.")
    @Schema(description = "cino", example = "123123")
    private String userCino;
    @NotBlank(message = "생일일자를 입력해주세요.")
    @Schema(description = "생일일자", example = "123456")
    private String userBirth;
}
