package GDSCSCH.cherry.domain.siteCheck.domain.repository;

import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteCheckRepository extends JpaRepository<SiteCheck, Long> {

    List<SiteCheck> findAllBySiteInfo(SiteInfo siteInfo);
}
