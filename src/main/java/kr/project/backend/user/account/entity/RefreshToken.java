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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseTime implements Serializable {

    /** 리프레시토큰 키값 */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @NotNull
    @Schema(description = "리프레시토큰 키값",example = "3fdec9bc-1592-4e53-97e6-3454869f5f95")
    private UUID refreshTokenId;

    /** 리프레시 토큰 */
    @Schema(description = "리프레시토큰",example = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJhZGI1Y2M5Yy01NDVmLTRlMGItOGRjYy1iMzhkNTdlZTZkYjkiLCJtbWJyTm0iOiLquYDtmY3roYAiLCJ1c2VyRW1haWwiOiJ0ZXN0MjJAbWFpbC5jb20iLCJpYXQiOjE3MDQxMzA0MDAsImV4cCI6MTcwNDEzMDcwMH0.3zL8l4pmqg4sttKgTUGDVl1EpwCtvnIiaAon2VdvUMI")
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Schema(hidden = true)
    private User user;

    public RefreshToken(String refreshToken, User userInfo) {
        this.refreshToken =refreshToken;
        this.user = userInfo;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken =refreshToken;
    }

}
