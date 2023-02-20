package GDSCSCH.cherry.domain.siteCheck.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddSiteCheckRequest {

    private Long siteId;
    private String siteQuestion;
}
