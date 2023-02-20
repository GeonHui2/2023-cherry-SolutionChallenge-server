package GDSCSCH.cherry.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminSignUp {

    private String adminName;
    private String adminEmail;
    private String adminPhoneNum;
    private int adminAge;
}
