package GDSCSCH.cherry.domain.siteCheck.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SiteCheckInfoVo {

    private final Long checkId;

    private final String siteQuestion;
    private final boolean siteAnswer;

}
