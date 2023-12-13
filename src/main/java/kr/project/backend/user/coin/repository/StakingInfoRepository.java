package kr.project.backend.user.coin.repository;

import kr.project.backend.user.coin.model.StakingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StakingInfoRepository extends JpaRepository<StakingInfo, UUID> {
}
