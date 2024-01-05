package kr.project.backend.entity.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import kr.project.backend.common.BaseTimeEntity;
import kr.project.backend.common.Constants;
import kr.project.backend.dto.user.request.UserLoginRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 회원정보
 * @author kh
 * @version v1.0
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements Serializable {

    /** 회원관리번호 */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Comment(value = "유저 키값")
    private UUID userId;

    @Comment(value = "유저 이메일")
    private String userEmail;

    @Comment(value = "유저 이름")
    private String userName;

    @Comment(value = "유저 패스워드")
    private String userPassword;
    @Comment(value = "유저 푸시토큰")
    private String userPushToken;

    @Comment(value = "유저 cino")
    private String userCino;

    @Comment(value = "유저 생년월일")
    private String userBirth;

    @Comment(value = "회원 상태")
    private String userState;

    @Comment(value = "로그아웃 일시")
    private String userLogoutDttm;

    @Comment(value = "회원가입 구분")
    private String userJoinKind;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user")
    private List<DropUser> dropUser;

    public User(UserLoginRequestDto userLoginRequestDto){
        this.userEmail = userLoginRequestDto.getUserEmail();
        this.userName = userLoginRequestDto.getUserName();
        this.userPassword = userLoginRequestDto.getUserPassword();
        this.userPushToken = userLoginRequestDto.getUserPushToken();
        this.userCino = userLoginRequestDto.getUserCino();
        this.userBirth = userLoginRequestDto.getUserBirth();
        this.userJoinKind = userLoginRequestDto.getUserJoinKind();
        this.userState = Constants.USER_STATE.ACTIVE_USER;
        this.userLogoutDttm = "";
    }

    public void updateUserLogoutDttm(String userLogoutDttm) {
        this.userLogoutDttm = userLogoutDttm;
    }

    public void updateUserDrop() {
        this.userEmail = "";
        this.userName = "";
        this.userPassword = "";
        this.userPushToken = "";
        this.userCino = "";
        this.userBirth = "";
        this.userState = Constants.USER_STATE.DROP_USER;
        this.userLogoutDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
