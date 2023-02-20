package GDSCSCH.cherry.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUp {

    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private Integer userAge;
}
