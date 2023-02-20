package GDSCSCH.cherry.domain.admin.service;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.AdminSignUp;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.ChangeAdminInfoRequest;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminProfileResponse;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.global.utils.admin.AdminUtils;
import GDSCSCH.cherry.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminUtils adminUtils;

    //관리자 회원가입
    @Transactional
    public Long signUpAdmin(AdminSignUp signUp) {
        Admin admin = Admin.createAdmin(
                signUp.getAdminName(),
                signUp.getAdminEmail(),
                signUp.getAdminPhoneNum(),
                signUp.getAdminAge()
        );

        adminRepository.save(admin);

        return admin.getId();
    }

    //관리자 개인정보 수정
    @Transactional
    public void changeAdminInfo(ChangeAdminInfoRequest changeAdminInfo, Long adminId) {
        Admin admin = adminUtils.getAdminById(adminId);

        admin.changeAdminInfo(
                changeAdminInfo.getAdminName(),
                changeAdminInfo.getAdminPhoneNum(),
                changeAdminInfo.getAdminAge()
        );
    }

    //관리자 개인정보 조회
    public AdminProfileResponse getProfile(Long adminId) {
        Admin admin = adminUtils.getAdminById(adminId);

        return new AdminProfileResponse(admin.getAdminInfo());
    }
}
