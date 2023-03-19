package GDSCSCH.cherry.domain.user.presentation;

import GDSCSCH.cherry.domain.user.presentation.dto.request.*;
import GDSCSCH.cherry.domain.user.presentation.dto.response.*;
import GDSCSCH.cherry.domain.user.service.UserService;
import GDSCSCH.cherry.global.security.oauth.TokenVerifierer;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static GDSCSCH.cherry.global.successResponse.StatusCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenVerifierer tokenVerifierer;


    //유저 로그인
    @PostMapping("/signIn")
    public ResponseEntity userSignIn(@RequestParam String idToken, HttpServletResponse response) throws GeneralSecurityException, IOException {
        String email = tokenVerifierer.tokenVerify(idToken);

        UserSignInResponse result = userService.signIn(email, response);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.USER_SIGNIN_SUCCESS);
    }

    //테스트 로그인
    @PostMapping("/testSignIn")
    public ResponseEntity testSignIn(@RequestParam String email, HttpServletResponse response) {
        UserSignInResponse result = userService.signIn(email, response);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.USER_SIGNIN_SUCCESS);
    }

    //유저 회원가입
    @PostMapping("/signUp")
    public ResponseEntity userSignUp(@RequestParam String idToken, @RequestBody UserSignUp userSignUp, HttpServletResponse response) throws GeneralSecurityException, IOException {
        String email = tokenVerifierer.tokenVerify(idToken);
        Long userId = userService.signUp(email, userSignUp, response);

        return SuccessResponse.successtoResponseEntity(OK, userId, SuccessResponseMessage.USER_SIGNUP_SUCCESS);
    }

    //테스트 유저 회원가입
    @PostMapping("/testSignUp")
    public ResponseEntity testSignUp(@RequestParam String email, @RequestBody UserSignUp userSignUp, HttpServletResponse response) {
        Long userId = userService.signUp(email, userSignUp, response);

        return SuccessResponse.successtoResponseEntity(OK, userId, SuccessResponseMessage.USER_SIGNUP_SUCCESS);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("RefreshToken") String refreshToken) {
        boolean result = userService.logout(refreshToken);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.USER_LOGOUT_SUCCESS);
    }

    //유저 본인 현장 정보 조회
    @GetMapping("/getSiteInfo")
    public ResponseEntity getSiteInfo(@RequestParam String email) {
        Long siteInfoId = userService.getSiteInfo(email);

        return SuccessResponse.successtoResponseEntity(OK, siteInfoId, SuccessResponseMessage.GET_USER_SITEINFO);
    }

    //근로자 현장 코드 입력
    @PatchMapping("/acceptSite")
    public ResponseEntity mappingSite(@RequestBody GrantSiteInfo grantSiteInfo) {
        userService.mappingSite(grantSiteInfo.getSiteCode());

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.USER_MAPPING_SITE);
    }

    //유저 개인정보 수정
    @PatchMapping("/editInfo")
    public ResponseEntity editUserInfo1(@RequestBody ChangeUserInfoRequest changeUserInfo) {
        userService.changeUserInfo(changeUserInfo);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_INFO);
    }

    //유저 개인정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity getProfile(@PathVariable("userId") Long userId) {
        UserProfileResponse profile = userService.getProfile(userId);

        return SuccessResponse.successtoResponseEntity(OK, profile, SuccessResponseMessage.USER_INFO);
    }

    //근로자 안전모 착용 유무 조회
    @GetMapping("/helmetCheck")
    public ResponseEntity checkHelmet() {
        boolean result = userService.checkHelmet();

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.USER_HELMET_CHECK);
    }

    //근로자 안전모 착용 변경
    @PatchMapping("/editHelmet")
    public ResponseEntity updateHelmetCheck(@RequestBody ChangeHelmetCheck changeHelmetCheck) {
        userService.updateHelmetCheck(changeHelmetCheck.isHelmetCheck());

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_HELMET_CHECK);
    }

    //현장 승인 대기 중 취소
    @PatchMapping("/cancelAccept/{userId}")
    public ResponseEntity cancelAccept(@PathVariable("userId") Long userId) {
        userService.cancelAccept(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.CANCEL_SITE_ACCEPT);
    }
}
