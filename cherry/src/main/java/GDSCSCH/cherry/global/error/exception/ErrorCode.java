package GDSCSCH.cherry.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SITE_CHECK_NOT_FOUND(404, "CHECK-404-1", "SiteCheck Not Found"),
    DEFAULT_SITE_CHECK_NOT_FOUND(404, "DEFAULT-CHECK-404-1", "DefaultSiteCheck Not Found"),
    SITE_NOT_FOUND(404, "SITE-404-1", "SiteInfo Not Found"),
    ADMIN_NOT_SITE(400, "SITE-400-1", "Admin Not Site"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "GLOBAL-404-1", "User Not Found."),
    ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "GLOBAL-404-1", "Admin Not Found."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "GLOBAL-500-1", "Internal Server Error.");
    private int status;
    private String code;
    private String reason;
}
