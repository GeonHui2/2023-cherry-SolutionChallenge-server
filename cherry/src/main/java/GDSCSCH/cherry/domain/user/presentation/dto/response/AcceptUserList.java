package GDSCSCH.cherry.domain.user.presentation.dto.response;

import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AcceptUserList {

    private Long userId;
    private String userName;
    private int userAge;

    public AcceptUserList(UserInfoVo userInfoVo) {
        this.userId = userInfoVo.getUserId();
        this.userName = userInfoVo.getUserName();
        this.userAge = userInfoVo.getUserAge();
    }
}
