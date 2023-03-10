package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponse {

    private Role role;
    private boolean existSiteInfo;

    public UserSignInResponse(UserInfoVo userInfoVo, boolean result) {
        this.role = userInfoVo.getRole();
        this.existSiteInfo = result;
    }
}
