package kr.project.backend.user.account.repository;

import kr.project.backend.user.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, String> {

    User findByUserCino(String userCino);
}
