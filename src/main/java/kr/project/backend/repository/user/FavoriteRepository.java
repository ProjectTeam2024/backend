package kr.project.backend.repository.user;

import kr.project.backend.entity.coin.StakingInfo;
import kr.project.backend.entity.user.Favorite;
import kr.project.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    Optional<Favorite> findByStakingInfoAndUser(StakingInfo stakingInfo, User userInfo);
}
