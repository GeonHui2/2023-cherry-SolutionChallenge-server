//package GDSCSCH.cherry.domain.admin.service;
//
//import GDSCSCH.cherry.domain.admin.domain.Admin;
//import GDSCSCH.cherry.domain.admin.domain.Role;
//import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
//import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
//import GDSCSCH.cherry.domain.admin.presentation.dto.request.AdminSignUp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import static org.assertj.core.api.Assertions.*;
//
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class AdminServiceTest {
//
//    @Autowired
//    AdminRepository adminRepository;
//
//    @Test
//    @DisplayName("관리자 회원가입")
//    void signUpAdmin() {
//
//        //given
//        AdminSignUp adminSignUp = new AdminSignUp();
//        adminSignUp.setAdminName("admin2");
//        adminSignUp.setAdminEmail("email2");
//        adminSignUp.setAdminPhoneNum("01000000000");
//        adminSignUp.setAdminAge(30);
//
//        //when
//        Admin admin1 = Admin.createAdmin(
//                adminSignUp.getAdminName(),
//                adminSignUp.getAdminEmail(),
//                adminSignUp.getAdminPhoneNum(),
//                adminSignUp.getAdminAge());
//
//        adminRepository.save(admin1);
//        Admin admin = adminRepository.findById(admin1.getId()).get();
//
//        //then
//        log.info("admin.name = {}", admin.getAdminName());
//        log.info("admin.email = {}", admin.getAdminEmail());
//        log.info("admin.phoneNum = {}", admin.getAdminPhoneNum());
//        log.info("admin.age = {}", admin.getAdminAge());
//        assertThat(admin1.getId()).isEqualTo(admin.getId());
//        assertThat(admin1.getAdminName()).isEqualTo(admin.getAdminName());
//        assertThat(admin1.getAdminEmail()).isEqualTo(admin.getAdminEmail());
//        assertThat(admin1.getAdminPhoneNum()).isEqualTo(admin.getAdminPhoneNum());
//        assertThat(admin1.getAdminAge()).isEqualTo(admin.getAdminAge());
//    }
//
//    @Test
//    @DisplayName("관리자 정보 변경")
//    void changeAdminInfo() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        //when
//        admin1.changeAdminInfo("admin2", "01000000001", 20);
//
//        //then
//        log.info("name = {}", admin1.getAdminName());
//        log.info("phoneNum = {}", admin1.getAdminPhoneNum());
//        log.info("age = {}", admin1.getAdminAge());
//        assertThat(admin1.getAdminName()).isEqualTo("admin2");
//        assertThat(admin1.getAdminPhoneNum()).isEqualTo("01000000001");
//        assertThat(admin1.getAdminAge()).isEqualTo(20);
//
//    }
//
//    @Test
//    @DisplayName("관리자 정보 조회")
//    void getProfile() {
//
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        //when
//        AdminInfoVo adminInfo = admin1.getAdminInfo();
//
//        //then
//        log.info("id = {}", adminInfo.getAdminId());
//        log.info("name = {}", adminInfo.getAdminName());
//        log.info("phoneNum = {}", adminInfo.getAdminPhoneNum());
//        log.info("age = {}", adminInfo.getAdminAge());
//        assertThat(adminInfo.getAdminId()).isEqualTo(admin1.getId());
//        assertThat(adminInfo.getAdminName()).isEqualTo(admin1.getAdminName());
//        assertThat(adminInfo.getAdminPhoneNum()).isEqualTo(admin1.getAdminPhoneNum());
//        assertThat(adminInfo.getAdminAge()).isEqualTo(admin1.getAdminAge());
//
//    }
//}