package GDSCSCH.cherry.domain.defaultSiteCheck.presentation;

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

    @PostMapping("/createCheck")
    public ResponseEntity addDefaultCheck(String defaultCheck) {
        Long defaultCheckId = defaultSiteCheckService.addDefaultCheck(defaultCheck);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, defaultCheckId, SuccessResponseMessage.CREATE_DEFAULT_CHECK);
    }

    @PatchMapping("/edit/{defaultCheckId}")
    public ResponseEntity editDefaultCheck(@PathVariable("defaultCheckId") Long defaultCheckId, @RequestBody String changeQuestion) {
        defaultSiteCheckService.editDefaultCheck(changeQuestion, defaultCheckId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.EDIT_DEFAULT_CHECKLIST);
    }

    @DeleteMapping("/{defaultCheckId}")
    public ResponseEntity deleteDefaultCheck(@PathVariable("defaultCheckId") Long defaultCheckId) {
        defaultSiteCheckService.deleteDefaultCheck(defaultCheckId);

        return SuccessResponse.successtoResponseEntity(StatusCode.OK, null, SuccessResponseMessage.DELETE_DEFAULT_CHECKLIST);
    }


}
