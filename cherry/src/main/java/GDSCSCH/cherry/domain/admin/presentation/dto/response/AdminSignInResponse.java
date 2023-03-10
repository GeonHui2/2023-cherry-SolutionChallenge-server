package GDSCSCH.cherry.domain.admin.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminSignInResponse {

    private Role role;
    private boolean existSiteInfo;

    public AdminSignInResponse(AdminInfoVo adminInfoVo, boolean result) {
        this.role = adminInfoVo.getRole();
        this.existSiteInfo = result;
    }
}
