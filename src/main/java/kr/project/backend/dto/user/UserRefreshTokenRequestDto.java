package kr.project.backend.dto.user;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRefreshTokenRequestDto {

    @NotNull
    private UUID refreshTokenId;
}