//package GDSCSCH.cherry.domain.user.service;
//
//import GDSCSCH.cherry.domain.admin.domain.Role;
//import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
//import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
//import GDSCSCH.cherry.domain.user.domain.User;
//import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
//import GDSCSCH.cherry.domain.user.domain.vo.UserInfoVo;
//import GDSCSCH.cherry.domain.user.presentation.dto.request.UserSignUp;
//import GDSCSCH.cherry.domain.user.presentation.dto.response.UserHelmetListResponse;
//import GDSCSCH.cherry.domain.user.presentation.dto.response.UserInfoResponse;
//import GDSCSCH.cherry.domain.user.presentation.dto.response.UserProfileResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class UserServiceTest {
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    SiteInfoRepository siteInfoRepository;
//
//    @Test
//    @DisplayName("유저 회원가입")
//    void signUpUser() {
//        //given
//        UserSignUp userSignUp = new UserSignUp();
//        userSignUp.setUserName("user");
//        userSignUp.setUserEmail("email");
//        userSignUp.setUserPhoneNum("01000000000");
//        userSignUp.setUserAge(20);
//
//        //when
//        User user = User.createUser(
//                userSignUp.getUserName(),
//                userSignUp.getUserEmail(),
//                userSignUp.getUserPhoneNum(),
//                userSignUp.getUserAge()
//        );
//        userRepository.save(user);
//        User user1 = userRepository.findById(user.getId()).get();
//
//        //then
//        log.info("name = {}", user.getUserName());
//        log.info("email = {}", user.getUserEmail());
//        log.info("phoneNum = {}", user.getUserPhoneNum());
//        log.info("age = {}", user.getUserAge());
//        assertThat(user1.getId()).isEqualTo(user.getId());
//        assertThat(user1.getUserName()).isEqualTo(user.getUserName());
//        assertThat(user1.getUserEmail()).isEqualTo(user.getUserEmail());
//        assertThat(user1.getUserPhoneNum()).isEqualTo(user.getUserPhoneNum());
//        assertThat(user1.getUserAge()).isEqualTo(user.getUserAge());
//
//    }
//
//    @Test
//    @DisplayName("유저 개인정보 수정")
//    void changeUserInfo() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        user.changeUserInfo("user2", "01011111111", 20);
//
//        //then
//        log.info("name = {}", user.getUserName());
//        log.info("phoneNum = {}", user.getUserPhoneNum());
//        log.info("age = {}", user.getUserAge());
//        assertThat(user.getUserName()).isEqualTo("user2");
//        assertThat(user.getUserPhoneNum()).isEqualTo("01011111111");
//        assertThat(user.getUserAge()).isEqualTo(20);
//    }
//
//    @Test
//    @DisplayName("근로자 삭제")
//    void deleteUser() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        userRepository.delete(user);
//
//        //then
//        assertEquals(0, userRepository.count());
//    }
//
//    @Test
//    @DisplayName("유저 개인정보 조회")
//    void getProfile() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        UserProfileResponse userProfileResponse = new UserProfileResponse(user.getUserInfo());
//
//        //then
//        log.info("name = {}", userProfileResponse.getUserName());
//        log.info("phoneNum = {}", userProfileResponse.getUserPhoneNum());
//        log.info("age = {]", userProfileResponse.getUserAge());
//        assertEquals(user.getUserName(), userProfileResponse.getUserName());
//        assertEquals(user.getUserPhoneNum(), userProfileResponse.getUserPhoneNum());
//        assertEquals(user.getUserAge(), userProfileResponse.getUserAge());
//    }
//
//    @Test
//    @DisplayName("안전모 쓴 근로자 리스트 확인")
//    void getTrueHelmetUserList() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        User user1 = new User("user1", "email1", "01000000000", 20, Role.USER, true);
//        User user2 = new User("user2", "email1", "01000000000", 20, Role.USER, true);
//        User user3 = new User("user3", "email1", "01000000000", 20, Role.USER, true);
//        User user4 = new User("user4", "email1", "01000000000", 20, Role.USER, true);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//
//        site.addUser(user1);
//        site.addUser(user2);
//        site.addUser(user3);
//        site.addUser(user4);
//        //when
//
//        List<User> trueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, true);
//
//        List<UserInfoResponse> result = trueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
//
//        log.info("count = {}", result.size());
//        //then
//        for (UserInfoResponse r : result) {
//            log.info("result = {}", r.getUserName());
//        }
//    }
//
//    @Test
//    @DisplayName("안전모 안 쓴 근로자 리스트 확인")
//    void getFalseHelmetUserList() {
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        User user1 = new User("user1", "email1", "01000000000", 20, Role.USER, false);
//        User user2 = new User("user2", "email1", "01000000000", 20, Role.USER, false);
//        User user3 = new User("user3", "email1", "01000000000", 20, Role.USER, false);
//        User user4 = new User("user4", "email1", "01000000000", 20, Role.USER, false);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//
//        site.addUser(user1);
//        site.addUser(user2);
//        site.addUser(user3);
//        site.addUser(user4);
//
//        //when
//        List<User> flaseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, false);
//
//        List<UserInfoResponse> result = flaseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
//
//        log.info("count = {}", result.size());
//        //then
//        for (UserInfoResponse r : result) {
//            log.info("result = {}", r.getUserName());
//        }
//    }
//
//    @Test
//    @DisplayName("안전모 착용 유무 근로자 리스트")
//    void getUserHelmetList() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        User user1 = new User("user1", "email1", "01000000000", 20, Role.USER, false);
//        User user2 = new User("user2", "email1", "01000000000", 20, Role.USER, false);
//        User user3 = new User("user3", "email1", "01000000000", 20, Role.USER, false);
//        User user4 = new User("user4", "email1", "01000000000", 20, Role.USER, false);
//        User user5 = new User("user5", "email1", "01000000000", 20, Role.USER, true);
//        User user6 = new User("user6", "email1", "01000000000", 20, Role.USER, true);
//        User user7 = new User("user7", "email1", "01000000000", 20, Role.USER, true);
//        User user8 = new User("user8", "email1", "01000000000", 20, Role.USER, true);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//        userRepository.save(user5);
//        userRepository.save(user6);
//        userRepository.save(user7);
//        userRepository.save(user8);
//
//        site.addUser(user1);
//        site.addUser(user2);
//        site.addUser(user3);
//        site.addUser(user4);
//        site.addUser(user5);
//        site.addUser(user6);
//        site.addUser(user7);
//        site.addUser(user8);
//
//        //when
//        List<User> trueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, true);
//        List<UserInfoResponse> checked = trueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
//
//        List<User> flaseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, false);
//        List<UserInfoResponse> unchecked = flaseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
//
//        UserHelmetListResponse result = new UserHelmetListResponse(checked, unchecked);
//
//        //then
//        log.info("result.check1 = {}", result.getChecked().get(0).getUserName());
//        log.info("result.check2 = {}", result.getChecked().get(1).getUserName());
//        log.info("result.check3 = {}", result.getChecked().get(2).getUserName());
//        log.info("result.check4 = {}", result.getChecked().get(3).getUserName());
//        log.info("result.uncheck1 = {}", result.getUnchecked().get(0).getUserName());
//        log.info("result.uncheck2 = {}", result.getUnchecked().get(1).getUserName());
//        log.info("result.uncheck3 = {}", result.getUnchecked().get(2).getUserName());
//        log.info("result.uncheck4 = {}", result.getUnchecked().get(3).getUserName());
//    }
//
//    @Test
//    @DisplayName("유저 개인정보 상세 조회")
//    void getDetailInfo() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        UserInfoVo userInfoVo = user.getUserInfo();
//
//        //then
//        log.info("id = {}", userInfoVo.getUserId());
//        log.info("name = {}", userInfoVo.getUserName());
//        log.info("email = {}", userInfoVo.getUserEmail());
//        log.info("phoneNum = {}", userInfoVo.getUserPhoneNum());
//        log.info("age = {}", userInfoVo.getUserAge());
//        assertEquals(userInfoVo.getUserId(), user.getId());
//        assertEquals(userInfoVo.getUserName(), user.getUserName());
//        assertEquals(userInfoVo.getUserEmail(), user.getUserEmail());
//        assertEquals(userInfoVo.getUserPhoneNum(), user.getUserPhoneNum());
//        assertEquals(userInfoVo.getUserAge(), user.getUserAge());
//    }
//
//    @Test
//    @DisplayName("승인 요청 리스트 조회")
//    void acceptUserList() {
//        //given
//        User user1 = User.createUser("user1", "email1", "01000000000", 30);
//        User user2 = User.createUser("user2", "email2", "01000000000", 30);
//        User user3 = User.createUser("user3", "email3", "01000000000", 30);
//        User user4 = User.createUser("user4", "email4", "01000000000", 30);
//        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//
//        //when
//        List<User> list = userRepository.findAllBySiteInfoAndRole(null, Role.GUEST);
//
//        //then
//        for (User u : list) log.info("user = {}", u.getUserName());
//        assertEquals(4, list.size());
//    }
//
//    @Test
//    @DisplayName("게스트 권한 근로자로 권한 승인")
//    void changeRoleToUser() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        user.changeRole(Role.USER);
//
//        //then
//        log.info("role = {}", user.getRole());
//        assertEquals(user.getRole(), Role.USER);
//    }
//
//    @Test
//    @DisplayName("근로자들 권한 변경")
//    void changeUserRole() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        user.changeRole(Role.STAFF);
//
//        //then
//        log.info("role = {}", user.getRole());
//        assertEquals(user.getRole(), Role.STAFF);
//    }
//
//    @Test
//    @DisplayName("근로자들 현장 코드 입력")
//    void mappingSite() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 40.1, null);
//
//        //when
//        site.addUser(user);
//
//        //then
//        log.info("siteUser.name = {}", site.getUserList().get(0).getUserName());
//        log.info("userSite = {}", user.getSiteInfo().getSiteName());
//        assertEquals(1, site.getUserList().size());
//        assertEquals(user.getUserName(), site.getUserList().get(0).getUserName());
//    }
//
//    @Test
//    @DisplayName("근로자 안전모 착용 유무 조회")
//    void checkHelmet() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        boolean check = user.isUserHelmetCheck();
//
//        //then
//        log.info("helmetCheck = {}", check);
//        assertEquals(check, false);
//    }
//
//    @Test
//    @DisplayName("근로자 안전모 착용 변경")
//    void updateHelmetCheck() {
//        //given
//        User user = User.createUser("user1", "email1", "01000000000", 30);
//        userRepository.save(user);
//
//        //when
//        user.changeHelmetCheck(true);
//
//        //then
//        log.info("helmetCheck = {}", user.isUserHelmetCheck());
//        assertEquals(user.isUserHelmetCheck(), true);
//    }
//}