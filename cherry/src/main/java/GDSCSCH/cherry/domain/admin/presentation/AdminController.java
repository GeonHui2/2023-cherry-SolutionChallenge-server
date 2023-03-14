package GDSCSCH.cherry.domain.admin.presentation;

import GDSCSCH.cherry.domain.admin.presentation.dto.request.AdminSignUp;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.ChangeAdminInfoRequest;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.ChangeRole;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminProfileResponse;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminSignInResponse;
import GDSCSCH.cherry.domain.admin.service.AdminService;
import GDSCSCH.cherry.domain.user.presentation.dto.response.AcceptUserList;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserDetailInfoResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserHelmetListResponse;
import GDSCSCH.cherry.domain.user.service.UserService;
import GDSCSCH.cherry.global.security.oauth.TokenVerifierer;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static GDSCSCH.cherry.global.successResponse.StatusCode.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final TokenVerifierer tokenVerifierer;


    //관리자 로그인
    @PostMapping("/signIn")
    public ResponseEntity userSignIn(@RequestParam String idToken, HttpServletResponse response) throws GeneralSecurityException, IOException {
        String email = tokenVerifierer.tokenVerify(idToken);
        log.info("email={}",email);
        AdminSignInResponse result = adminService.signIn(email, response);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.ADMIN_SIGNIN_SUCCESS);
    }

    //테스트 로그인
    @PostMapping("/testSignIn")
    public ResponseEntity testSignIn(@RequestParam String email, HttpServletResponse response) {
        AdminSignInResponse result = adminService.signIn(email, response);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.ADMIN_SIGNIN_SUCCESS);
    }

    //관리자 회원가입
    @PostMapping("/signUp")
    public ResponseEntity userSignUp(@RequestParam String idToken, @RequestBody AdminSignUp adminSignUp, HttpServletResponse response) throws GeneralSecurityException, IOException {
        String email = tokenVerifierer.tokenVerify(idToken);
        log.info("email={}",email);
        Long adminId = adminService.signUp(email, adminSignUp, response);

        return SuccessResponse.successtoResponseEntity(OK, adminId, SuccessResponseMessage.ADMIN_SIGNUP_SUCCESS);
    }

    //테스트 회원가입
    @PostMapping("/testSignUp")
    public ResponseEntity testSignUp(@RequestParam String email, @RequestBody AdminSignUp adminSignUp, HttpServletResponse response) {
        Long adminId = adminService.signUp(email, adminSignUp, response);

        return SuccessResponse.successtoResponseEntity(OK, adminId, SuccessResponseMessage.ADMIN_SIGNUP_SUCCESS);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("RefreshToken") String refreshToken) {
        boolean result = adminService.logout(refreshToken);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.ADMIN_LOGOUT_SUCCESS);
    }

    //관리자 본인 현장 정보 조회
    @GetMapping("/getSiteInfo")
    public ResponseEntity getSiteInfo(@RequestParam String email) {
        Long siteInfoId = adminService.getSiteInfo(email);

        return SuccessResponse.successtoResponseEntity(OK, siteInfoId, SuccessResponseMessage.GET_ADMIN_SITEINFO);
    }

    //관리자 개인정보 수정
    @PatchMapping("/editInfo")
    public ResponseEntity changeAdminInfo(@RequestBody ChangeAdminInfoRequest changeAdminInfo) {
        adminService.changeAdminInfo(changeAdminInfo);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_ADMIN_INFO);
    }

    //관리자 개인정보 조회
    @GetMapping("/{adminId}")
    public ResponseEntity getProfile(@PathVariable("adminId") Long adminId) {
        AdminProfileResponse profile = adminService.getProfile(adminId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, profile, SuccessResponseMessage.GET_ADMIN_INFO);
    }

    //승인 요청 리스트 조회
    @GetMapping("/acceptList/{siteId}")
    public ResponseEntity acceptUserList(@PathVariable("siteId") Long siteId) {
        List<AcceptUserList> acceptUserList = userService.acceptUserList(siteId);

        return SuccessResponse.successtoResponseEntity(OK, acceptUserList, SuccessResponseMessage.ACCEPT_USER_LIST);
    }

    //유저 개인정보 상세 조회
    @GetMapping("/detail/{userId}")
    public ResponseEntity getDetailInfo(@PathVariable("userId") Long userId) {
        UserDetailInfoResponse detailInfo = userService.getDetailInfo(userId);

        return SuccessResponse.successtoResponseEntity(OK, detailInfo, SuccessResponseMessage.USER_INFO_DETAIL);
    }

    //게스트 권한 근로자로 권한 승인
    @PatchMapping("/acceptGuest/{userId}")
    public ResponseEntity acceptRoleToUser(@PathVariable("userId") Long userId) {
        userService.acceptRoleToUser(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.ACCEPT_GUEST);
    }

    //근로자 권한 변경
    @PatchMapping("/acceptUser/{userId}")
    public ResponseEntity changeUserRole(@PathVariable("userId") Long userId, @RequestBody ChangeRole changeRole) {
        userService.changeUserRole(userId, changeRole.getRole());

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_ROLE);
    }

    //근로자 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.DELETE_USER);
    }

    //안전모 착용 유무 근로자 리스트
    @GetMapping("/checkHelmet/{siteId}")
    public ResponseEntity getUserHelmetList(@PathVariable("siteId") Long siteId) {
        UserHelmetListResponse userHelmetList = userService.getUserHelmetList(siteId);

        return SuccessResponse.successtoResponseEntity(OK, userHelmetList, SuccessResponseMessage.USER_HELMET_LIST);
    }

}
