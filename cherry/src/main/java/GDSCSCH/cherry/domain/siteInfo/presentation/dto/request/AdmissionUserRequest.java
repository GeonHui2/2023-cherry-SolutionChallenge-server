package GDSCSCH.cherry.domain.siteInfo.presentation.dto.request;

import GDSCSCH.cherry.domain.admin.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdmissionUserRequest {

    private Long userId;
    private Role role;
}
