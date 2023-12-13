package kr.project.backend.repository;

import kr.project.backend.entity.StakingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StakingInfoRepository extends JpaRepository<StakingInfo, UUID> {
}
