package kr.project.backend.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequestDto {

    @Schema(description = "회원관리번호", example = "33669090-cc33-401d-973f-32df9b0787d4", hidden = true)
    private String userId;

    @Schema(description = "이메일", example = "test@test.com")
    private String userEmail;

    @NotBlank(message = "이름을 입력하세요.")
    @Schema(description = "이름", example = "홍길동")
    private String userName;

    @Schema(description = "비밀번호", example = "qwer1234")
    private String userPassword;

    @NotBlank(message = "푸쉬 토큰을 넣어주세요.")
    @Schema(description = "푸쉬 토큰", example = "asWERds123/sdkmmal2WED/sdmpPalm")
    private String userPushToken;

    @NotBlank(message = "유저 cino를 넣어주세요.")
    @Schema(description = "cino", example = "akn3/Nlklakknelk2KmapPomkdonSEDFmapoiqnnmmlasn/gn3JNklks==")
    private String userCino;

    @NotBlank(message = "생일일자를 넣어주세요.")
    @Schema(description = "생일일자", example = "19900315")
    private String userBirth;

    @NotBlank(message = "회원가입 구분을 넣어주세요.")
    @Schema(description = "회원가입 구분", example = "01")
    private String userJoinKind;
}
