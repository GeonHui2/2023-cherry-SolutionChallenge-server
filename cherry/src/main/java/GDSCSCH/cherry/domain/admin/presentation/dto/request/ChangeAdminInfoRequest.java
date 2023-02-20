package GDSCSCH.cherry.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeAdminInfoRequest {

    private String adminName;
    private String adminPhoneNum;
    private int adminAge;
}