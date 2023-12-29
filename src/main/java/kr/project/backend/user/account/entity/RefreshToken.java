package kr.project.backend.user.account.entity;

import jakarta.persistence.*;
import kr.project.backend.user.coin.entity.common.BaseTime;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
public class RefreshToken extends BaseTime implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID refreshTokenId;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
