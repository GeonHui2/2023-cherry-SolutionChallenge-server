package GDSCSCH.cherry.global.utils.user;

import GDSCSCH.cherry.domain.user.domain.User;

public interface UserUtils {

    User getUserByEmail(String email);

    User getUserFromSecurityContext();
}
