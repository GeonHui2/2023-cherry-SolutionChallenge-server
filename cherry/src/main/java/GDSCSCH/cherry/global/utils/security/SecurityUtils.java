package GDSCSCH.cherry.global.utils.security;

import GDSCSCH.cherry.global.exception.AdminNotFoundException;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw UserNotFoundException.EXCEPTION;
        }
        return Long.valueOf(authentication.getName());
    }

    public static Long getCurrentAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw AdminNotFoundException.EXCEPTION;
        }
        return Long.valueOf(authentication.getName());
    }
}
