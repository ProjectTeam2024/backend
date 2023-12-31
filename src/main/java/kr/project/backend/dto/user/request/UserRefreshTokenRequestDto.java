package kr.project.backend.dto.user.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRefreshTokenRequestDto {

    @NotNull
    @Schema(description = "refreshTokenId", example = "9854112a-f2a9-4cde-86b5-d54569db7120")
    private UUID refreshTokenId;
}