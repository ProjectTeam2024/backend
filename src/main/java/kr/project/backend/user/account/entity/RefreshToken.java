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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseTime implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @NotNull
    private UUID refreshTokenId;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public RefreshToken(String refreshToken, User userInfo) {
        this.refreshToken =refreshToken;
        this.user = userInfo;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken =refreshToken;
    }


}
