package GDSCSCH.cherry.domain.admin.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminInfoVo {

    private final Long adminId;
    private final String adminName;
    private final String adminPhoneNum;
    private final int adminAge;
}
