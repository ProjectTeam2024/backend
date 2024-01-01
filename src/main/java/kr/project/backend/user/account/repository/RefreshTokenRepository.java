package kr.project.backend.user.account.repository;

import kr.project.backend.user.account.entity.RefreshToken;
import kr.project.backend.user.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    RefreshToken findByUser(User userInfo);
}
