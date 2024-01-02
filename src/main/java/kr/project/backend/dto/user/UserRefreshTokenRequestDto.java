package kr.project.backend.dto.user;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRefreshTokenRequestDto {
    private UUID refreshTokenId;
}
