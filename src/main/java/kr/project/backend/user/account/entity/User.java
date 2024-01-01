package kr.project.backend.user.account.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.project.backend.user.coin.entity.common.BaseTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
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
public class User extends BaseTime implements Serializable {

    /** 회원관리번호 */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Schema(description = "회원관리번호",example = "1ac83a62-578a-4421-b464-5511e9cfd89e")
    private UUID userId;

    /** eamil */
    @NotNull
    @Schema(description = "eamil",example = "test_adress@email.com")
    private String userEmail;

    /** 이름 */
    @NotNull
    @Schema(description = "이름",example = "아무개")
    private String userName;

    /** 패스워드 */
    @Schema(description = "패스워드",example = "qwe123aa")
    private String userPassword;

    /** push token */
    @NotNull
    @Schema(description = "push token",example = "pomOIN123/sdfLAKsdf2/knsadfnaQWEBB")
    private String userPushToken;

    /** cino */
    @NotNull
    @Schema(description = "cino",example = "asdklfn123LKNKasdfoiilnQWEB9124usdfksliWETSDFmlknoiple==")
    private String userCino;

    /** 생년월일 */
    @NotNull
    @Schema(description = "생년월일" , example = "19940810")
    private String userBirth;

    @OneToOne(mappedBy = "user")
    @Schema(hidden = true)
    private RefreshToken refreshToken;

}
