package kr.project.backend.dto.coin;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.project.backend.entity.coin.enumType.CoinMarketType;
import kr.project.backend.entity.coin.StakingInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StakingInfoResponseDto implements Serializable {
    @Schema(description = "키값",example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID stakingId;
    @Schema(description = "코인이름",example = "폴리곤 (MATIC)")
    private String coinName;
    @Schema(description = "연 추정 보상률",example = "5.3%")
    private String annualRewardRate;
    @Schema(description = "스테이킹/언스테이킹 대기",example = "3시간 / 3일")
    private String stakingStatus;
    @Schema(description = "보상주기",example = "매일")
    private String rewardCycle;
    @Schema(description = "보상률 (3개월)",example = "보상률 최소 4.9% ~ 최대 5.5%")
    private String rewardRateForThreeMonth;
    @Schema(description = "보상률 (6개월)",example = "보상률 최소 4.9% ~ 최대 5.5%")
    private String rewardRateForSixMonth;
    @Schema(description = "보상률 (1년)",example = "보상률 최소 4.9% ~ 최대 5.5%")
    private String rewardRateForOneYear;
    @Schema(description = "보상률 (3년)",example = "보상률 최소 4.9% ~ 최대 5.5%")
    private String rewardRateForThreeYear;
    @Schema(description = "보상률 변동 추세 (3개월)",example = "좌표 값임(너무길어서 생략)")
    private String rewardRateTrendForThreeMonth;
    @Schema(description = "보상률 변동 추세 (6개월)",example = "좌표 값임(너무길어서 생략)")
    private String rewardRateTrendForSixMonth;
    @Schema(description = "보상률 변동 추세 (1년)",example = "좌표 값임(너무길어서 생략)")
    private String rewardRateTrendForOneYear;
    @Schema(description = "보상률 변동 추세 (3년)",example = "좌표 값임(너무길어서 생략)")
    private String rewardRateTrendForThreeYear;
    @Schema(description = "최소신청수량",example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String minimumOrderQuantity;
    @Schema(description = "검증인 수수료",example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String verificationFee;
    @Schema(description = "거래소",example = "업비트")
    private CoinMarketType coinMarketType;

    public StakingInfoResponseDto(StakingInfo stakingInfo){
        this.stakingId = stakingInfo.getStakingId();
        this.coinName = stakingInfo.getCoinName();
        this.annualRewardRate = stakingInfo.getAnnualRewardRate();
        this.stakingStatus = stakingInfo.getStakingStatus();
        this.rewardCycle = stakingInfo.getRewardCycle();
        this.rewardRateForThreeMonth = stakingInfo.getRewardRateForThreeMonth();
        this.rewardRateForSixMonth = stakingInfo.getRewardRateForSixMonth();
        this.rewardRateForOneYear = stakingInfo.getRewardRateForOneYear();
        this.rewardRateForThreeYear = stakingInfo.getRewardRateForThreeYear();
        this.rewardRateTrendForThreeMonth = stakingInfo.getRewardRateTrendForThreeMonth();
        this.rewardRateTrendForSixMonth = stakingInfo.getRewardRateTrendForSixMonth();
        this.rewardRateTrendForOneYear = stakingInfo.getRewardRateTrendForOneYear();
        this.rewardRateTrendForThreeYear = stakingInfo.getRewardRateTrendForThreeYear();
        this.minimumOrderQuantity = stakingInfo.getMinimumOrderQuantity();
        this.verificationFee = stakingInfo.getVerificationFee();
        this.coinMarketType = stakingInfo.getCoinMarketType();
    }
}
