package kr.project.backend.user.account.repository;

import kr.project.backend.user.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserCino(String userCino);
}