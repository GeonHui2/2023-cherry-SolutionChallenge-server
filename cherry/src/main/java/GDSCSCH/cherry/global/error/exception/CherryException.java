package GDSCSCH.cherry.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CherryException extends RuntimeException{

    private ErrorCode errorCode;
}
