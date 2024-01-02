package kr.project.backend.dto.user;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userPushToken;
    private String userCino;
    private String userBirth;
}
