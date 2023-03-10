package GDSCSCH.cherry.domain.siteInfo.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddSiteInfoRequest {

    private String siteName;
    private Double siteLatitude;
    private Double siteLongitude;
    private String address1;
    private String address2;
}
