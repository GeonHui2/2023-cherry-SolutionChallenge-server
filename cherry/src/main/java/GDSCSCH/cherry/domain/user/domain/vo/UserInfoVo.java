package GDSCSCH.cherry.domain.user.domain.vo;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoVo {

    private final Long userId;
    private final String userName;
    private final String userEmail;
    private final String userPhoneNum;
    private final int userAge;
    private final Role role;
    private final SiteInfo siteInfo;
}
