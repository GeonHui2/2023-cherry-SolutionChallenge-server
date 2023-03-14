package GDSCSCH.cherry.domain.siteInfo.presentation.dto.request;

import GDSCSCH.cherry.domain.siteInfo.service.dto.UpdateSiteInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateSiteInfoRequest {

    @NotNull
    private String siteName;

    @NotNull
    private Double siteLatitude;

    @NotNull
    private Double siteLongitude;

    private String address1;
    private String address2;

    public UpdateSiteInfoRequest(String siteName, double siteLatitude, double siteLongitude, String address1, String address2) {
    }
}
