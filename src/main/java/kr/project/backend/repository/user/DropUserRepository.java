package kr.project.backend.repository.user;

import kr.project.backend.entity.user.DropUser;
import kr.project.backend.entity.user.RefreshToken;
import kr.project.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DropUserRepository extends JpaRepository<DropUser, UUID> {

    Optional<DropUser> existsByUserEmail(String userEmail);

    Optional<DropUser> findByUserEmail(String userEmail);
}
