package GDSCSCH.cherry.domain.siteInfo.presentation;

import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.AddSiteInfoRequest;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.UpdateSiteInfoRequest;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.response.SiteInfoResponse;
import GDSCSCH.cherry.domain.siteInfo.service.SiteInfoService;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserHelmetListResponse;
import GDSCSCH.cherry.domain.user.service.UserService;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static GDSCSCH.cherry.global.successResponse.StatusCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/site")
public class SiteInfoController {

    private final SiteInfoService siteInfoService;

    //현장 정보 생성
    @PostMapping("/createSite")
    public ResponseEntity addSiteInfo(@RequestBody AddSiteInfoRequest addSiteInfoRequest) {
        Long siteId = siteInfoService.addSiteInfo(addSiteInfoRequest);

        return SuccessResponse.successtoResponseEntity(OK, siteId, SuccessResponseMessage.CREATE_SITE);
    }

    //현장 정보 조회
    @GetMapping("/{siteId}")
    public ResponseEntity getDetailSiteInfo(@PathVariable("siteId") Long siteId) {
        SiteInfoResponse siteInfo = siteInfoService.getDetailSiteInfo(siteId);

        return SuccessResponse.successtoResponseEntity(OK, siteInfo, SuccessResponseMessage.GET_SITE);
    }

    //현장 정보 수정
    @PatchMapping("/{siteId}")
    public ResponseEntity updateSiteInfo(@PathVariable("siteId") Long siteId, @RequestBody UpdateSiteInfoRequest updateSiteInfoRequest) {
        siteInfoService.updateSiteInfo(siteId, updateSiteInfoRequest);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.EDIT_SITE);
    }

    //현장 정보 삭제
    @DeleteMapping("/{siteId}")
    public ResponseEntity deleteSiteInfo(@PathVariable("siteId") Long siteId) {
        siteInfoService.deleteSiteInfo(siteId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.DELETE_SITE);
    }

    //현장 코드 유효 여부 조회
    @GetMapping("/valid/{siteCode}")
    public ResponseEntity checkValidSiteCode(@PathVariable("siteCode") String siteCode) {
        boolean result = siteInfoService.checkValidSiteCode(siteCode);

        return SuccessResponse.successtoResponseEntity(OK, result, SuccessResponseMessage.VALID_SITE_CODE);
    }

    // 출근 시스템
    @PatchMapping("/workStart/{siteId}")
    public ResponseEntity workStart(@PathVariable("siteId") Long siteId) {
        siteInfoService.workStart(siteId);

        return SuccessResponse.successtoResponseEntity(OK, null, SuccessResponseMessage.WORK_START);
    }

}
