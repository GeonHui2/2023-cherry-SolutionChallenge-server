package GDSCSCH.cherry.domain.admin.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminProfileResponse {

    private String adminName;
    private String adminPhoneNum;
    private int adminAge;

    public AdminProfileResponse(AdminInfoVo adminInfoVo) {
        this.adminName = adminInfoVo.getAdminName();
        this.adminPhoneNum = adminInfoVo.getAdminPhoneNum();
        this.adminAge = adminInfoVo.getAdminAge();
    }
}
