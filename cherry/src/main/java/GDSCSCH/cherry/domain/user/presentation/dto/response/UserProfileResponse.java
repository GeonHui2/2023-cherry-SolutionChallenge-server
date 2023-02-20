package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

    private String userName;
    private String userPhoneNum;
    private int userAge;

    public UserProfileResponse(UserInfoVo adminInfoVo) {
        this.userName = adminInfoVo.getUserName();
        this.userPhoneNum = adminInfoVo.getUserPhoneNum();
        this.userAge = adminInfoVo.getUserAge();
    }
}
