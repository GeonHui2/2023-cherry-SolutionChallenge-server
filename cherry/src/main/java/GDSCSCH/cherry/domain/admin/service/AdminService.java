package GDSCSCH.cherry.domain.admin.service;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.RefreshToken;
import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
import GDSCSCH.cherry.domain.admin.domain.repository.RefreshTokenRepository;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.AdminSignUp;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.ChangeAdminInfoRequest;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminProfileResponse;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminSignInResponse;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
import GDSCSCH.cherry.global.exception.AdminNotFoundException;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import GDSCSCH.cherry.global.security.jwt.JwtTokenProvider;
import GDSCSCH.cherry.global.utils.admin.AdminUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminUtils adminUtils;
    private final SiteInfoRepository siteInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    //로그인
    @Transactional
    public AdminSignInResponse signIn (String email, HttpServletResponse response) {

        Admin admin = findAdmin(email);
        createToken(email, response);
        log.info(admin.getAdminEmail() + " (id : " + admin.getId() + ") login");

        return new AdminSignInResponse(admin.getAdminInfo(), admin.getSiteInfo() == null ? false : true);
    }

    //회원가입 및 로그인
    @Transactional
    public Long signUp (String email, AdminSignUp signUp, HttpServletResponse response) {

        if(adminRepository.findByAdminEmail(email).isEmpty()) {

            Admin admin = Admin.createAdmin(
                    signUp.getAdminName(),
                    email,
                    signUp.getAdminPhoneNum(),
                    signUp.getAdminAge()
            );

            adminRepository.save(admin);

            // 어세스, 리프레시 토큰 발급 및 헤더 설정
            createToken(email, response);
            log.info(admin.getAdminEmail() + " (id : " + admin.getId() + ") login");
            return admin.getId();
        }
        return null;
    }

    // 로그아웃
    @Transactional
    public boolean logout(String refreshToken) {
        if (!refreshTokenRepository.existsByRefreshToken(refreshToken))
            return false;

        refreshTokenRepository.deleteByRefreshToken(refreshToken);

        return true;
    }

    //관리자 본인 현장 정보 조회
    public Long getSiteInfo(String email) {
        Admin admin = adminRepository.findByAdminEmail(email).orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        return admin.getSiteInfo().getId();
    }

    //관리자 개인정보 수정
    @Transactional
    public void changeAdminInfo(ChangeAdminInfoRequest changeAdminInfo) {
        Admin admin = adminUtils.getAdminFromSecurityContext();

        admin.changeAdminInfo(
                changeAdminInfo.getAdminName(),
                changeAdminInfo.getAdminPhoneNum(),
                changeAdminInfo.getAdminAge()
        );
    }

    //관리자 개인정보 조회
    public AdminProfileResponse getProfile(Long adminId) {
        Admin admin = findAdminById(adminId);

        return new AdminProfileResponse(admin.getAdminInfo());
    }

    private void createToken(String email, HttpServletResponse response) {
        String accessToken = jwtTokenProvider.createAccessToken(email, Role.ADMIN);
        String refreshToken = jwtTokenProvider.createRefreshToken(email, Role.ADMIN);

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        refreshTokenRepository.save(new RefreshToken(refreshToken));
    }

    public Admin findAdmin(String email) {
        return adminRepository
                .findByAdminEmail(email)
                .orElseThrow(() -> new RuntimeException(UserNotFoundException.EXCEPTION));
    }

    public Admin findAdminById(Long id) {
        return adminRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(UserNotFoundException.EXCEPTION));
    }
}
