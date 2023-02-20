package GDSCSCH.cherry.domain.siteInfo.exception;

import GDSCSCH.cherry.global.error.exception.CherryException;
import GDSCSCH.cherry.global.error.exception.ErrorCode;
import GDSCSCH.cherry.global.exception.AdminNotFoundException;

public class NotAdminException extends CherryException {

    public static final CherryException EXCEPTION = new NotAdminException();

    private NotAdminException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
