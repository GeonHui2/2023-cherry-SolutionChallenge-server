package GDSCSCH.cherry.domain.user.domain.repository;

import GDSCSCH.cherry.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
