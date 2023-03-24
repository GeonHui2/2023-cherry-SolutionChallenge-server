package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponse {

    private Long id;
    private Role role;
    private boolean existSiteInfo;
    private boolean waitingAccept;

    public UserSignInResponse(UserInfoVo userInfoVo) {
        this.id = userInfoVo.getUserId();
        this.role = userInfoVo.getRole();
        if (role != Role.GUEST && userInfoVo.getSiteInfo() != null) {
            this.existSiteInfo = true;
        }
        else this.existSiteInfo = false;
        if (role == Role.GUEST && userInfoVo.getSiteInfo() != null) {
            this.waitingAccept = true;
        }
        else this.waitingAccept = false;
    }
}
