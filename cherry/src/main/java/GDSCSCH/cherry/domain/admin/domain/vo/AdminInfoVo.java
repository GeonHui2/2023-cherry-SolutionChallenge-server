package GDSCSCH.cherry.domain.admin.domain.vo;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminInfoVo {

    private final Long adminId;
    private final String adminName;
    private final String adminEmail;
    private final String adminPhoneNum;
    private final int adminAge;
    private final Role role;
    private final SiteInfo siteInfo;
}
