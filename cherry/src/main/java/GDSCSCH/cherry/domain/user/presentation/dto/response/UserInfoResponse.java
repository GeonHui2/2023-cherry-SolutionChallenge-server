package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;

    private String userName;
    private Integer userAge;
    private Role role;

    public UserInfoResponse(UserInfoVo userInfoVo) {
        this.id = userInfoVo.getUserId();
        this.userName = userInfoVo.getUserName();
        this.userAge = userInfoVo.getUserAge();
        this.role = userInfoVo.getRole();
    }
}
