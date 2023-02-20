package GDSCSCH.cherry.global.utils.admin;

import GDSCSCH.cherry.domain.admin.domain.Admin;

public interface AdminUtils {

    Admin getAdminById(Long id);

    Admin getAdminFromSecurityContext();
}
