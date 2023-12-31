package kr.project.backend.entity.user;

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

    @Comment(value = "유저 푸시토큰")
    private String userPushToken;

    @Comment(value = "회원 상태")
    private String userState;

    @Comment(value = "로그아웃 일시")
    private String userLogoutDttm;

    @Comment(value = "회원가입 sns 구분")
    private String userJoinSnsKind;

    @Comment(value = "회원가입 os 구분")
    private String userJoinOsKind;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user")
    private List<DropUser> dropUser;

    public User(UserLoginRequestDto userLoginRequestDto){
        this.userEmail = userLoginRequestDto.getUserEmail();
        this.userPushToken = userLoginRequestDto.getUserPushToken();
        this.userJoinSnsKind = userLoginRequestDto.getUserJoinSnsKind();
        this.userJoinOsKind = userLoginRequestDto.getUserJoinOsKind();
        this.userState = Constants.USER_STATE.ACTIVE_USER;
        this.userLogoutDttm = "";
    }

    public void updateUserLogoutDttm(String userLogoutDttm) {
        this.userLogoutDttm = userLogoutDttm;
    }

    public void updateUserDrop() {
        this.userEmail = "";
        this.userPushToken = "";
        this.userJoinSnsKind = "";
        this.userJoinOsKind = "";
        this.userState = Constants.USER_STATE.DROP_USER;
        this.userLogoutDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
