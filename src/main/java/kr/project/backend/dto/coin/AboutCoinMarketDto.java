package kr.project.backend.dto.coin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AboutCoinMarketDto {
    private UUID stakingId;
    private String aboutCoinMarketType;
}
