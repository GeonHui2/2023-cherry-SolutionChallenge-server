package GDSCSCH.cherry.domain.admin.presentation.dto.request;

import GDSCSCH.cherry.domain.admin.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRole {

    private Role role;
}
