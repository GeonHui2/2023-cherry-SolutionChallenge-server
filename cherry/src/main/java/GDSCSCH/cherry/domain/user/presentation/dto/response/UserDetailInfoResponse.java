package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailInfoResponse {

    private Long id;

    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private Integer userAge;
    private Role role;

    public UserDetailInfoResponse(UserInfoVo userInfoVo) {
        this.id = userInfoVo.getUserId();
        this.userName = userInfoVo.getUserName();
        this.userEmail = userInfoVo.getUserEmail();
        this.userPhoneNum = userInfoVo.getUserPhoneNum();
        this.userAge = userInfoVo.getUserAge();
        this.role = userInfoVo.getRole();
    }
}
