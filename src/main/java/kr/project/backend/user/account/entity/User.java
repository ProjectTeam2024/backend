package kr.project.backend.user.account.entity;

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
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    /** 회원관리번호 */
    private UUID userId;
    /** eamil */
    @NotNull
    private String userEmail;

    /** 이름 */
    private String userName;

    /** 패스워드 */
    private String userPassword;

    /** push token */
    @NotNull
    private String userPushToken;

    /** cino */
    @NotNull
    private String userCino;

    /** 생년월일 */
    @NotNull
    private String userBirth;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

}
