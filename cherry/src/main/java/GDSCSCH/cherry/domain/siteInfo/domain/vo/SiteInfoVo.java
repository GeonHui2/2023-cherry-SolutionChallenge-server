package GDSCSCH.cherry.domain.siteInfo.domain.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SiteInfoVo {

    private final Long siteId;
    private final String siteCode;
    private final String siteName;
    private final Double siteLatitude;
    private final Double siteLongitude;
    private final String address1;
    private final String address2;
}
