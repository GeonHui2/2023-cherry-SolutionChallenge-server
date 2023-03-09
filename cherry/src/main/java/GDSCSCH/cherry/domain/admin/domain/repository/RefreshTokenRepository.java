package GDSCSCH.cherry.domain.admin.domain.repository;

import GDSCSCH.cherry.domain.admin.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshToken(String token);

    RefreshToken findByRefreshToken(String token);

    @Transactional
    void deleteByRefreshToken(String token);
}

