package GDSCSCH.cherry.global.utils.admin;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
import GDSCSCH.cherry.global.exception.AdminNotFoundException;
import GDSCSCH.cherry.global.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminUtilsImpl implements AdminUtils{

    private final AdminRepository adminRepository;

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByAdminEmail(email).orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    @Override
    public Admin getAdminFromSecurityContext() {
        String currentAdminEmail = SecurityUtils.getCurrentAdminId();
        Admin admin = getAdminByEmail(currentAdminEmail);
        return admin;
    }
}
