package GDSCSCH.cherry.global.exception;

import GDSCSCH.cherry.global.error.exception.CherryException;
import GDSCSCH.cherry.global.error.exception.ErrorCode;

public class AdminNotFoundException extends CherryException {

    public static final CherryException EXCEPTION = new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
