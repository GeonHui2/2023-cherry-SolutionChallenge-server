package GDSCSCH.cherry.domain.siteCheck.presentation;

import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.AddSiteCheckRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.ChangeCheckQuestionRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.UpdateSiteCheckAnswerRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.response.SiteCheckResponse;
import GDSCSCH.cherry.domain.siteCheck.service.SiteCheckService;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/siteCheck")
public class SiteCheckController {

    private final SiteCheckService siteCheckService;

    //현장 체크 리스트 입력
    @PostMapping("/createCheck")
    public ResponseEntity addSiteCheck(@RequestBody AddSiteCheckRequest addSiteCheckRequest) {
        Long checkId = siteCheckService.addSiteCheck(addSiteCheckRequest);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, checkId, SuccessResponseMessage.CREATE_CHECK);
    }

    //현장 체크 리스트 조회
    @GetMapping("/list/{siteId}")
    public ResponseEntity getCheckList(@PathVariable("siteId") Long siteId) {
        List<SiteCheckResponse> checkList = siteCheckService.getCheckList(siteId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, checkList, SuccessResponseMessage.GET_CHECKLIST);
    }

    //현장 체크 질문 수정
    @PatchMapping("/question/{checkId}")
    public ResponseEntity editQuestion(@PathVariable("checkId") Long checkId, @RequestBody ChangeCheckQuestionRequest questionRequest) {
        siteCheckService.changeCheckQuestion(questionRequest, checkId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_CHECKLIST);
    }

    //현장 체크 질문 삭제
    @DeleteMapping("/{checkId}")
    public ResponseEntity deleteSiteCheck(@PathVariable("checkId") Long checkId) {
        siteCheckService.deleteSiteCheck(checkId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.DELETE_CHECKLIST);
    }

    //현장 체크 리스트 상태 수정
    @PatchMapping("/answer/{checkId}")
    public ResponseEntity editAnswer(@PathVariable("checkId") Long checkId, @RequestBody UpdateSiteCheckAnswerRequest updateSiteCheckAnswerRequest) {
        siteCheckService.updateSiteCheckAnswer(updateSiteCheckAnswerRequest, checkId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_CHECK_ANSWER);
    }


}
