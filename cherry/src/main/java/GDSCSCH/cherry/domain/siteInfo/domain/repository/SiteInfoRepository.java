package GDSCSCH.cherry.domain.siteInfo.domain.repository;

import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SiteInfoRepository extends JpaRepository<SiteInfo, Long> {

    Optional<SiteInfo> findBySiteCode(String siteCode);

    boolean existsBySiteCode(String siteCode);
}
