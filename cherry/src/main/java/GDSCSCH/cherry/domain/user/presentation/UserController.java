package GDSCSCH.cherry.domain.user.presentation;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.user.presentation.dto.request.ChangeUserInfoRequest;
import GDSCSCH.cherry.domain.user.presentation.dto.request.UserSignUp;
import GDSCSCH.cherry.domain.user.presentation.dto.response.AcceptUserList;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserDetailInfoResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserHelmetListResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserProfileResponse;
import GDSCSCH.cherry.domain.user.service.UserService;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static GDSCSCH.cherry.global.successResponse.StatusCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //유저 회원가입
    @PostMapping("/signUp")
    public ResponseEntity userSignUp(@RequestBody UserSignUp userSignUp) {
        userService.signUpUser(userSignUp);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.USER_SIGNUP_SUCCESS);
    }

    //근로자 현장 코드 입력
    @PatchMapping("/acceptSite/{userId}")
    public ResponseEntity mappingSite(@PathVariable("userId") Long userId, @RequestBody String siteCode) {
        userService.mappingSite(siteCode, userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.USER_MAPPING_SITE);
    }

    //유저 개인정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity editUserInfo(@PathVariable("userId") Long userId, ChangeUserInfoRequest changeUserInfo) {
        userService.changeUserInfo(changeUserInfo, userId);

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
    public ResponseEntity checkHelmet(@PathVariable("userId") Long userId) {
        userService.checkHelmet(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.USER_HELMET_CHECK);
    }

    //근로자 안전모 착용 변경
    @PatchMapping("/editHelmet")
    public ResponseEntity updateHelmetCheck(@RequestBody boolean helmetCheck) {
        userService.updateHelmetCheck(helmetCheck);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_HELMET_CHECK);
    }
}
