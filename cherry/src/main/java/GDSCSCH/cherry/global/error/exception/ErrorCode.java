package GDSCSCH.cherry.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
;
    private int status;
    private String code;
    private String reason;
}
