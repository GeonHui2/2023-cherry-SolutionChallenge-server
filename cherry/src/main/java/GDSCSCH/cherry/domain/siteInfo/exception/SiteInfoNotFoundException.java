package GDSCSCH.cherry.domain.siteInfo.exception;

import GDSCSCH.cherry.global.error.exception.CherryException;
import GDSCSCH.cherry.global.error.exception.ErrorCode;

public class SiteInfoNotFoundException extends CherryException {

    public static final CherryException EXCEPTION = new SiteInfoNotFoundException();

    private SiteInfoNotFoundException() {
        super(ErrorCode.SITE_NOT_FOUND);
    }
}
