package GDSCSCH.cherry.domain.user.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserHelmetListResponse {
    private List<UserInfoResponse> checked;
    private List<UserInfoResponse> unchecked;

    public UserHelmetListResponse(List<UserInfoResponse> checked, List<UserInfoResponse> unchecked) {
        this.checked = checked;
        this.unchecked = unchecked;
    }
}
