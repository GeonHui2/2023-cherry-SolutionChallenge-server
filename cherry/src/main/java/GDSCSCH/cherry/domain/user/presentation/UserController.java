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

    //유저 개인정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity editUserInfo(@PathVariable("userId") Long userId, ChangeUserInfoRequest changeUserInfo) {
        userService.changeUserInfo(changeUserInfo, userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_INFO);
    }

    //근로자 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.DELETE_USER);
    }

    //유저 개인정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity getProfile(@PathVariable("userId") Long userId) {
        UserProfileResponse profile = userService.getProfile(userId);

        return SuccessResponse.successtoResponseEntity(OK, profile, SuccessResponseMessage.USER_INFO);
    }

    //안전모 착용 유무 근로자 리스트
    @GetMapping("/checkHelmet/{siteId}")
    public ResponseEntity getUserHelmetList(@PathVariable("siteId") Long siteId) {
        UserHelmetListResponse userHelmetList = userService.getUserHelmetList(siteId);

        return SuccessResponse.successtoResponseEntity(OK, userHelmetList, SuccessResponseMessage.USER_HELMET_LIST);
    }

    //유저 개인정보 상세 조회
    @GetMapping("/detail/{userId}")
    public ResponseEntity getDetailInfo(@PathVariable("userId") Long userId) {
        UserDetailInfoResponse detailInfo = userService.getDetailInfo(userId);

        return SuccessResponse.successtoResponseEntity(OK, detailInfo, SuccessResponseMessage.USER_INFO_DETAIL);
    }

    //승인 요청 리스트 조회
    @GetMapping("/acceptList/{siteId}")
    public ResponseEntity acceptUserList(@PathVariable("siteId") Long siteId) {
        List<AcceptUserList> acceptUserList = userService.acceptUserList(siteId);

        return SuccessResponse.successtoResponseEntity(OK, acceptUserList, SuccessResponseMessage.ACCEPT_USER_LIST);
    }

    //게스트 권한 근로자로 권한 승인
    @PatchMapping("/acceptGuest/{userId}")
    public ResponseEntity acceptRoleToUser(@PathVariable("userId") Long userId) {
        userService.acceptRoleToUser(userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.ACCEPT_GUEST);
    }

    //근로자 권한 변경
    @PatchMapping("/acceptUser/{userId}")
    public ResponseEntity changeUserRole(@PathVariable("userId") Long userId, @RequestBody Role role) {
        userService.changeUserRole(userId, role);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_USER_ROLE);
    }

    //근로자 현장 코드 입력
    @PatchMapping("/acceptSite/{userId}")
    public ResponseEntity mappingSite(@PathVariable("userId") Long userId, @RequestBody String siteCode) {
        userService.mappingSite(siteCode, userId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.USER_MAPPING_SITE);
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
