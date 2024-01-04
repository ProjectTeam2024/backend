package kr.project.backend.dto.coin;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.project.backend.entity.coin.StakingInfo;
import kr.project.backend.entity.coin.enumType.CoinMarketType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
public class StakingInfoDetailResponseDto implements Serializable {
    @Schema(description = "코인이름",example = "폴리곤 (MATIC)")
    private String coinName;
    @Schema(description = "연 추정 보상률",example = "5.3%")
    private String annualRewardRate;
    @Schema(description = "거래소",example = "업비트")
    private CoinMarketType coinMarketType;
    @Schema(description = "스테이킹/언스테이킹 대기",example = "3시간 / 3일")
    private String stakingStatus;
    @Schema(description = "보상주기",example = "매일")
    private String rewardCycle;
    @Schema(description = "최소신청수량",example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String minimumOrderQuantity;
    @Schema(description = "검증인 수수료",example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String verificationFee;

    public StakingInfoDetailResponseDto(StakingInfo stakingInfo){
        this.coinName = stakingInfo.getCoinName();
        this.annualRewardRate = stakingInfo.getAnnualRewardRate();
        this.coinMarketType = stakingInfo.getCoinMarketType();
        this.stakingStatus = stakingInfo.getStakingStatus();
        this.rewardCycle = stakingInfo.getRewardCycle();
        this.minimumOrderQuantity = stakingInfo.getMinimumOrderQuantity();
        this.verificationFee = stakingInfo.getVerificationFee();
    }
}