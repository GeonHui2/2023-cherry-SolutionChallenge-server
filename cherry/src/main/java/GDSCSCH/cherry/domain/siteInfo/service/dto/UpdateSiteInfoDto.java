package GDSCSCH.cherry.domain.siteInfo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateSiteInfoDto {

    private String siteName;

    private Double siteLatitude;

    private Double siteLongitude;
}
