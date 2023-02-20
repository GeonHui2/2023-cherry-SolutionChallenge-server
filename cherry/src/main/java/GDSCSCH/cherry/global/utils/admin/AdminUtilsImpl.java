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
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    @Override
    public Admin getAdminFromSecurityContext() {
        Long adminId = SecurityUtils.getCurrentAdminId();
        Admin admin = getAdminById(adminId);
        return admin;
    }
}
