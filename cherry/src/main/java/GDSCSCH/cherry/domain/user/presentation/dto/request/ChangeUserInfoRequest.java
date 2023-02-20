package GDSCSCH.cherry.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeUserInfoRequest {

    private String userName;
    private String userPhoneNum;
    private Integer userAge;
}
