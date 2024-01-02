package kr.project.backend.entity.coin;

import jakarta.persistence.*;
import kr.project.backend.common.BaseTimeEntity;
import kr.project.backend.entity.coin.enumType.CoinMarketType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StakingInfo extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID stakingId;
    private String coinName; //코인이름
    private String annualRewardRate; //연 추정 보상률
    private String stakingStatus; //스테이킹/언스테이킹 대기
    private String rewardCycle; //보상주기
    private String rewardRateForThreeMonth; //보상률 (3개월)
    private String rewardRateForSixMonth; //보상률 (6개월)
    private String rewardRateForOneYear; //보상률 (1년)
    private String rewardRateForThreeYear; //보상률 (3년)
    @Column(length = 50000)
    private String rewardRateTrendForThreeMonth; //보상률 변동 추세 (3개월)
    @Column(length = 50000)
    private String rewardRateTrendForSixMonth; //보상률 변동 추세 (6개월)
    @Column(length = 50000)
    private String rewardRateTrendForOneYear; //보상률 변동 추세 (1년)
    @Column(length = 50000)
    private String rewardRateTrendForThreeYear; //보상률 변동 추세 (3년)
    private String minimumOrderQuantity; // 최소신청수량
    private String verificationFee; //검증인 수수료
    @Enumerated(EnumType.STRING)
    private CoinMarketType coinMarketType; //코인거래소 종류

}
