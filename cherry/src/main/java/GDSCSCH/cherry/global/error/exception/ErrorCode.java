package GDSCSCH.cherry.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(400, "INVALID_REFRESH_TOKEN","리프레시 토큰이 유효하지 않습니다"),
    INVALID_ACCESS_TOKEN(400, "INVALID_ACCESS_TOKEN","Access 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(400, "MISMATCH_REFRESH_TOKEN", "리프레시 토큰의 유저 정보가 일치하지 않습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(401, "INVALID_AUTH_TOKEN","권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(401, "UNAUTHORIZED_MEMBER", "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(404, "MEMBER_NOT_FOUND","해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(404, "REFRESH_TOKEN_NOT_FOUND","로그아웃 된 사용자입니다"),
    NO_ERROR_TYPE(404, "NO_ERROR_TYPE","오류 발생"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(409, "DUPLICATE_RESOURCE","데이터가 이미 존재합니다"),


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
