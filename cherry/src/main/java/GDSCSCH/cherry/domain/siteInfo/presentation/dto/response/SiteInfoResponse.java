package GDSCSCH.cherry.domain.siteInfo.presentation.dto.response;

import GDSCSCH.cherry.domain.siteInfo.domain.vo.SiteInfoVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SiteInfoResponse {

    private String siteCode;
    private String siteName;
    private Double siteLatitude;
    private Double siteLongitude;
    private String address1;
    private String address2;

    public SiteInfoResponse(SiteInfoVo siteInfoVo) {
        siteCode = siteInfoVo.getSiteCode();
        siteName = siteInfoVo.getSiteName();
        siteLatitude = siteInfoVo.getSiteLatitude();
        siteLongitude = siteInfoVo.getSiteLongitude();
        address1 = siteInfoVo.getAddress1();
        address2 = siteInfoVo.getAddress2();
    }
}
