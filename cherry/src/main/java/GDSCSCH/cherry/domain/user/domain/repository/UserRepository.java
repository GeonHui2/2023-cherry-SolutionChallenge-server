package GDSCSCH.cherry.domain.user.domain.repository;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String userEmail);
    List<User> findAllBySiteInfo(SiteInfo siteInfo);

    List<User> findAllBySiteInfoAndUserHelmetCheck(SiteInfo siteInfo, boolean userHelmetCheck);
    List<User> findAllBySiteInfoAndUserHelmetCheckAndRole(SiteInfo siteInfo, boolean userHelmetCheck, Role role);
    List<User> findAllBySiteInfoAndRole(SiteInfo siteInfo, Role role);
}
