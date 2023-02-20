package GDSCSCH.cherry.domain.siteInfo.presentation.dto.request;

import GDSCSCH.cherry.domain.siteInfo.service.dto.UpdateSiteInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateSiteInfoRequest {

    @NotNull
    private String siteName;

    @NotNull
    private Double siteLatitude;

    @NotNull
    private Double siteLongitude;

    public UpdateSiteInfoDto updateSiteInfoDto() {
        return UpdateSiteInfoDto.builder()
                .siteName(siteName)
                .siteLatitude(siteLatitude)
                .siteLongitude(siteLongitude)
                .build();
    }
}
