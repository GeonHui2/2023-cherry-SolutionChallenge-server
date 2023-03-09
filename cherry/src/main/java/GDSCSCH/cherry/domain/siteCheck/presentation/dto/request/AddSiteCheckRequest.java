package GDSCSCH.cherry.domain.siteCheck.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AddSiteCheckRequest {

    private Long siteId;
    private String siteQuestion;

    public AddSiteCheckRequest() {
    }
}
