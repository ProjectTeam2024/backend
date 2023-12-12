package kr.project.backend.dto;

import kr.project.backend.entity.StakingInfo;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Data
public class StakingInfoResponseDto implements Serializable {
    private UUID stakingId;
    private String coinName; //코인이름
    private String annualRewardRate; //연 추정 보상률
    private String stakingStatus; //스테이킹/언스테이킹 대기
    private String rewardCycle; //보상주기
    private String rewardRateForThreeMonth; //보상률 (3개월)
    private String rewardRateForSixMonth; //보상률 (6개월)
    private String rewardRateForOneYear; //보상률 (1년)
    private String rewardRateForThreeYear; //보상률 (3년)
    private String rewardRateTrendForThreeMonth; //보상률 변동 추세 (3개월)
    private String rewardRateTrendForSixMonth; //보상률 변동 추세 (6개월)
    private String rewardRateTrendForOneYear; //보상률 변동 추세 (1년)
    private String rewardRateTrendForThreeYear; //보상률 변동 추세 (3년)
    private String minimumOrderQuantity; // 최소신청수량
    private String verificationFee; //검증인 수수료

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
    }
}
