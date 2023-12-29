package kr.project.backend.user.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
public class RefreshToken {
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
