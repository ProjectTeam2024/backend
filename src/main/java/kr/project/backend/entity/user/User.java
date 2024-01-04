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
    @Schema(description = "회원관리번호",example = "1ac83a62-578a-4421-b464-5511e9cfd89e")
    private UUID userId;

    /** eamil */
    @Schema(description = "eamil",example = "test_adress@email.com")
    private String userEmail;

    /** 이름 */
    @Schema(description = "이름",example = "아무개")
    private String userName;

    /** 패스워드 */
    @Schema(description = "패스워드",example = "qwe123aa")
    private String userPassword;

    /** push token */
    @Schema(description = "push token",example = "pomOIN123/sdfLAKsdf2/knsadfnaQWEBB")
    private String userPushToken;

    /** cino */
    @Schema(description = "cino",example = "asdklfn123LKNKasdfoiilnQWEB9124usdfksliWETSDFmlknoiple==")
    private String userCino;

    /** 생년월일 */
    @Schema(description = "생년월일" , example = "19940810")
    private String userBirth;

    /** 회원상태 */
    @Schema(description = "회원상태" , example = "01")
    private String userState;

    /** 로그아웃 일시 */
    @Schema(description = "로그아웃 일시" , example = "2024-01-02 20:00:11")
    private String userLogoutDttm;

    /** 회원가입 구분 */
    @Schema(description = "회원가입 구분" , example = "01")
    private String userJoinKind;

    @OneToOne(mappedBy = "user")
    @Schema(hidden = true)
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user")
    @Schema(hidden = true)
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
