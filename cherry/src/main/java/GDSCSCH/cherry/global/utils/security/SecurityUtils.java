package GDSCSCH.cherry.global.utils.security;

import GDSCSCH.cherry.global.exception.AdminNotFoundException;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw UserNotFoundException.EXCEPTION;
        }
        return authentication.getName();
    }

    public static String getCurrentAdminId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw AdminNotFoundException.EXCEPTION;
        }
        return authentication.getName();
    }
}
