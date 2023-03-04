package GDSCSCH.cherry.domain.defaultSiteCheck.exception;

import GDSCSCH.cherry.global.error.exception.CherryException;
import GDSCSCH.cherry.global.error.exception.ErrorCode;

public class DefaultSiteCheckNotFoundException extends CherryException {

    public static final CherryException EXCEPTION = new DefaultSiteCheckNotFoundException();

    private DefaultSiteCheckNotFoundException() {
        super(ErrorCode.DEFAULT_SITE_CHECK_NOT_FOUND);
    }
}
