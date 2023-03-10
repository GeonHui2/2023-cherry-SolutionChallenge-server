//package GDSCSCH.cherry.domain.siteInfo.service;
//
//import GDSCSCH.cherry.domain.admin.domain.Admin;
//import GDSCSCH.cherry.domain.admin.domain.Role;
//import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
//import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
//import GDSCSCH.cherry.domain.siteCheck.domain.repository.SiteCheckRepository;
//import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
//import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
//import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.UpdateSiteInfoRequest;
//import GDSCSCH.cherry.domain.siteInfo.presentation.dto.response.SiteInfoResponse;
//import GDSCSCH.cherry.domain.user.domain.User;
//import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class SiteInfoServiceTest {
//
//    @Autowired
//    SiteInfoRepository repository;
//
//    @Autowired
//    AdminRepository adminRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    SiteCheckRepository siteCheckRepository;
//
//    @Test
//    @DisplayName("현장 정보 생성")
//    void addSiteInfo() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        //when
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        SiteInfo findSite = repository.findById(site.getId()).get();
//
//        //then
//        log.info("admin.site = {}", admin1.getSiteInfo().getSiteName());
//        log.info("findSite.Id = {}", findSite.getId());
//        log.info("findSite.name = {}", findSite.getSiteName());
//        log.info("findSite.code = {}", findSite.getSiteCode());
//        log.info("findSite.latitude = {}", findSite.getSiteLatitude());
//        log.info("findSite.longitude = {}", findSite.getSiteLongitude());
//        log.info("find = {}", findSite.getAdmin().getSiteInfo().getSiteName());
//        assertEquals(findSite.getId(), site.getId());
//        assertEquals(findSite.getSiteName(), site.getSiteName());
//        assertEquals(findSite.getSiteCode(), site.getSiteCode());
//        assertEquals(findSite.getSiteLatitude(), site.getSiteLatitude());
//        assertEquals(findSite.getSiteLongitude(), site.getSiteLongitude());
//    }
//
//    @Test
//    @DisplayName("현장 정보 조회")
//    void getDetailSiteInfo() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        //when
//        SiteInfo findSite = repository.findById(site.getId()).get();
//        SiteInfoResponse response = new SiteInfoResponse(site.getSiteInfoVo());
//
//        //then
//        log.info("response.name = {}", response.getSiteName());
//        log.info("response.code = {}", response.getSiteCode());
//        log.info("response.latitude = {}", response.getSiteLatitude());
//        log.info("response.longitude = {}", response.getSiteLongitude());
//        assertEquals(findSite.getSiteName(), site.getSiteName());
//        assertEquals(findSite.getSiteCode(), site.getSiteCode());
//        assertEquals(findSite.getSiteLatitude(), site.getSiteLatitude());
//        assertEquals(findSite.getSiteLongitude(), site.getSiteLongitude());
//    }
//
//    @Test
//    @DisplayName("현장 정보 수정")
//    void updateSiteInfo() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        //when
//        UpdateSiteInfoRequest updateSiteInfoRequest = new UpdateSiteInfoRequest("site1", 12.1, 115.1);
//        site.updateSiteInfo(updateSiteInfoRequest);
//
//        //then
//        log.info("site.name = {}", site.getSiteName());
//        log.info("site.latitude = {}", site.getSiteLatitude());
//        log.info("site.longitude = {}", site.getSiteLongitude());
//        assertThat(site.getSiteName()).isEqualTo("site1");
//        assertThat(site.getSiteLatitude()).isEqualTo(12.1);
//        assertThat(site.getSiteLongitude()).isEqualTo(115.1);
//
//    }
//
//    @Test
//    @DisplayName("현장 정보 삭제")
//    void deleteSiteInfo() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        //when
//        admin1.subSiteInfo();
//        repository.delete(site);
//
//        //then
//        assertEquals(0, repository.count());
//    }
//
//    @Test
//    @DisplayName("현장 코드 유효 여부 조회")
//    void checkValidSiteCode() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//        adminRepository.save(admin1);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        //when
//        boolean result = repository.existsBySiteCode(site.getSiteCode());
//
//        //then
//        log.info("code = {}", site.getSiteCode());
//        assertEquals(true, result);
//    }
//
//    @Test
//    @DisplayName("출근 시스템")
//    void workStart() {
//        //given
//        Admin admin1 = new Admin("admin1", "email1", "01000000000", 30, Role.ADMIN);
//
//        adminRepository.save(admin1);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, admin1);
//
//        repository.save(site);
//        admin1.addSiteInfo(site);
//
//        User user1 = new User("user1", "email1", "010000000000", 20, Role.USER, true);
//        User user2 = new User("user1", "email1", "010000000000", 20, Role.USER, true);
//        User staff1 = new User("staff1", "email1", "010000000000", 20, Role.STAFF, true);
//        User staff2 = new User("staff2", "email1", "010000000000", 20, Role.STAFF, true);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(staff1);
//        userRepository.save(staff2);
//
//        site.addUser(user1);
//        site.addUser(user2);
//        site.addUser(staff1);
//        site.addUser(staff2);
//
//        SiteInfo findSite = repository.findById(site.getId()).get();
//
//        SiteCheck siteCheck1 = new SiteCheck("질문1", true);
//        SiteCheck siteCheck2 = new SiteCheck("질문2", true);
//        SiteCheck siteCheck3 = new SiteCheck("질문3", true);
//
//        siteCheckRepository.save(siteCheck1);
//        siteCheckRepository.save(siteCheck2);
//        siteCheckRepository.save(siteCheck3);
//
//        site.addSiteCheck(siteCheck1);
//        site.addSiteCheck(siteCheck2);
//        site.addSiteCheck(siteCheck3);
//
//        log.info("check = {}", siteCheck1.getSiteInfo().getSiteName());
//
//        //when
//        List<User> userList = userRepository.findAllBySiteInfoAndRole(site, Role.USER);
//        List<User> staffList = userRepository.findAllBySiteInfoAndRole(site, Role.STAFF);
//
//        for (User u : userList) u.changeHelmetCheck(false);
//        for (User u : staffList) u.changeHelmetCheck(false);
//
//        List<SiteCheck> checkList = siteCheckRepository.findAllBySiteInfo(site);
//        for (SiteCheck c : checkList) c.updateSiteCheckAnswer(false);
//
//        log.info("list = {}", siteCheckRepository.count());
//        //then
//        log.info("check.user = {}", userList.get(0).isUserHelmetCheck());
//        log.info("check.user = {}", userList.get(1).isUserHelmetCheck());
//        log.info("check.staff = {}", staffList.get(0).isUserHelmetCheck());
//        log.info("check.staff = {}", staffList.get(1).isUserHelmetCheck());
//        log.info("check.list = {}", checkList.get(0).isSiteAnswer());
//        log.info("check.list = {}", checkList.get(1).isSiteAnswer());
//        log.info("check.list = {}", checkList.get(2).isSiteAnswer());
//    }
//}