package GDSCSCH.cherry.domain.admin.presentation;

import GDSCSCH.cherry.domain.admin.presentation.dto.request.AdminSignUp;
import GDSCSCH.cherry.domain.admin.presentation.dto.request.ChangeAdminInfoRequest;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminProfileResponse;
import GDSCSCH.cherry.domain.admin.service.AdminService;
import GDSCSCH.cherry.domain.user.service.UserService;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    //회원 가입 요청
    @PostMapping("/signUp")
    public ResponseEntity adminSingUp(@RequestBody AdminSignUp signUp) {

        adminService.signUpAdmin(signUp);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.ADMIN_SIGNUP_SUCCESS);
    }

    //관리자 개인정보 수정
    @PatchMapping("/editInfo/{adminId}")
    public ResponseEntity changeAdminInfo(@PathVariable("adminId") Long adminId, @RequestBody ChangeAdminInfoRequest changeAdminInfo) {
        adminService.changeAdminInfo(changeAdminInfo, adminId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_ADMIN_INFO);
    }

    //관리자 개인정보 조회
    @GetMapping("/{adminId}")
    public ResponseEntity getProfile(@PathVariable("adminId") Long adminId) {
        AdminProfileResponse profile = adminService.getProfile(adminId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, profile, SuccessResponseMessage.GET_ADMIN_INFO);
    }
}
