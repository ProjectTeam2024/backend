package kr.project.backend.user.coin.repository;

import kr.project.backend.user.coin.entity.StakingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface StakingInfoRepository extends JpaRepository<StakingInfo, UUID> {

    List<StakingInfo> findAllByCreatedDateBetween(String start, String end);
}
