package GDSCSCH.cherry.domain.siteCheck.service;

import GDSCSCH.cherry.domain.defaultSiteCheck.domain.DefaultSiteCheck;
import GDSCSCH.cherry.domain.defaultSiteCheck.repository.DefaultSiteCheckRepository;
import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;

import GDSCSCH.cherry.domain.siteCheck.exception.SiteCheckNotFoundException;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.AddSiteCheckRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.ChangeCheckQuestionRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.request.UpdateSiteCheckAnswerRequest;
import GDSCSCH.cherry.domain.siteCheck.presentation.dto.response.SiteCheckResponse;
import GDSCSCH.cherry.domain.siteCheck.domain.repository.SiteCheckRepository;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
import GDSCSCH.cherry.domain.siteInfo.exception.SiteInfoNotFoundException;
import GDSCSCH.cherry.global.utils.admin.AdminUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SiteCheckService {

    private final SiteCheckRepository siteCheckRepository;
    private final SiteInfoRepository siteInfoRepository;
    private final DefaultSiteCheckRepository defaultSiteCheckRepository;
    private final AdminUtils adminUtils;

    //현장 체크 리스트 입력
    @Transactional
    public Long addSiteCheck(AddSiteCheckRequest addSiteCheckRequest) {
        SiteInfo site = siteInfoRepository.findById(addSiteCheckRequest.getSiteId()).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        SiteCheck siteCheck = SiteCheck.createSiteCheck(
                addSiteCheckRequest.getSiteQuestion()
        );

        siteCheckRepository.save(siteCheck);
        site.addSiteCheck(siteCheck);

        return siteCheck.getId();
    }

    //현장 체크 리스트 조회
    public List<SiteCheckResponse> getCheckList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<SiteCheck> siteCheckList = siteCheckRepository.findAllBySiteInfo(site);

        return siteCheckList.stream().map(c -> new SiteCheckResponse(c.getCheckInfo())).collect(Collectors.toList());
    }

    //현장 체크 질문 삭제
    @Transactional
    public void deleteSiteCheck(Long siteCheckId) {
        SiteCheck check = findSiteCheck(siteCheckId);
        check.getSiteInfo().subSiteCheck(check);
        SiteInfo site = check.getSiteInfo();
        site.subSiteCheck(check);
        siteCheckRepository.deleteById(siteCheckId);
    }

    //현장 체크 질문 수정
    @Transactional
    public void changeCheckQuestion(ChangeCheckQuestionRequest questionRequest, Long checkId) {
        SiteCheck check = findSiteCheck(checkId);

        check.changeQuestion(questionRequest.getQuestion());
    }

    //현장 체크 리스트 상태 수정
    @Transactional
    public void updateSiteCheckAnswer(UpdateSiteCheckAnswerRequest updateSiteCheckAnswerRequest, Long siteCheckId) {

        SiteCheck siteCheck = findSiteCheck(siteCheckId);
        siteCheck.updateSiteCheckAnswer(updateSiteCheckAnswerRequest.isAnswer());
    }

    //기본 현장 체크 리스트 입력
    @Transactional
    public void addDefaultCheckList(Long id) {
        List<DefaultSiteCheck> defaultCheckList = defaultSiteCheckRepository.findAll();

        for (DefaultSiteCheck d : defaultCheckList) {
            AddSiteCheckRequest addSiteCheckRequest = new AddSiteCheckRequest(id, d.getDefaultQuestion());

            addSiteCheck(addSiteCheckRequest);
        }
    }

    //해당 현장 체크 질문 조회
    public SiteCheck findSiteCheck(Long id) {
        return siteCheckRepository.findById(id).orElseThrow(() -> SiteCheckNotFoundException.EXCEPTION);
    }
}
