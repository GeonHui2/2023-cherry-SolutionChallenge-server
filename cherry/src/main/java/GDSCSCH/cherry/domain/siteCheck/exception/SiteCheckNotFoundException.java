package GDSCSCH.cherry.domain.siteCheck.exception;

import GDSCSCH.cherry.domain.siteInfo.exception.SiteInfoNotFoundException;
import GDSCSCH.cherry.global.error.exception.CherryException;
import GDSCSCH.cherry.global.error.exception.ErrorCode;

public class SiteCheckNotFoundException extends CherryException {

    public static final CherryException EXCEPTION = new SiteCheckNotFoundException();

    private SiteCheckNotFoundException() {
        super(ErrorCode.SITE_CHECK_NOT_FOUND);
    }
}
