package GDSCSCH.cherry.domain.defaultSiteCheck.presentation;

import GDSCSCH.cherry.domain.defaultSiteCheck.presentation.dto.request.AddDefaultCheck;
import GDSCSCH.cherry.domain.defaultSiteCheck.presentation.dto.request.ChangeDefaultCheckQuestionRequest;
import GDSCSCH.cherry.domain.defaultSiteCheck.service.DefaultSiteCheckService;
import GDSCSCH.cherry.global.successResponse.StatusCode;
import GDSCSCH.cherry.global.successResponse.SuccessResponse;
import GDSCSCH.cherry.global.successResponse.SuccessResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/defaultSiteCheck")
public class DefaultSiteCheckController {

    private final DefaultSiteCheckService defaultSiteCheckService;

    //기본 체크 리스트 생성
    @PostMapping("/createCheck")
    public ResponseEntity addDefaultCheck(@RequestBody AddDefaultCheck addDefaultCheck) {
        Long defaultCheckId = defaultSiteCheckService.addDefaultCheck(addDefaultCheck.getDefaultCheck());

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, defaultCheckId, SuccessResponseMessage.CREATE_DEFAULT_CHECK);
    }

    //기본 체크 리스트 수정
    @PatchMapping("/edit/{defaultCheckId}")
    public ResponseEntity editDefaultCheck(@PathVariable("defaultCheckId") Long defaultCheckId, @RequestBody ChangeDefaultCheckQuestionRequest changeQuestion) {
        defaultSiteCheckService.editDefaultCheck(changeQuestion, defaultCheckId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_DEFAULT_CHECKLIST);
    }

    //기본 체크 리스트 삭제
    @DeleteMapping("/{defaultCheckId}")
    public ResponseEntity deleteDefaultCheck(@PathVariable("defaultCheckId") Long defaultCheckId) {
        defaultSiteCheckService.deleteDefaultCheck(defaultCheckId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.DELETE_DEFAULT_CHECKLIST);
    }


}
