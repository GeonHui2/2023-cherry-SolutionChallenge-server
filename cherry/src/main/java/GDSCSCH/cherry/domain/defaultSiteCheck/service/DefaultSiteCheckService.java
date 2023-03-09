package GDSCSCH.cherry.domain.defaultSiteCheck.service;

import GDSCSCH.cherry.domain.defaultSiteCheck.domain.DefaultSiteCheck;
import GDSCSCH.cherry.domain.defaultSiteCheck.exception.DefaultSiteCheckNotFoundException;
import GDSCSCH.cherry.domain.defaultSiteCheck.presentation.dto.request.ChangeDefaultCheckQuestionRequest;
import GDSCSCH.cherry.domain.defaultSiteCheck.repository.DefaultSiteCheckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultSiteCheckService {

    private final DefaultSiteCheckRepository defaultSiteCheckRepository;

    //기본 현장 체크 리스트 입력
    @Transactional
    public Long addDefaultCheck(String defaultCheck) {
        DefaultSiteCheck defaultSiteCheck = DefaultSiteCheck.createDefaultCheck(defaultCheck);

        defaultSiteCheckRepository.save(defaultSiteCheck);

        return defaultSiteCheck.getId();
    }

    //기본 현장 체크 리스트 수정
    @Transactional
    public void editDefaultCheck(ChangeDefaultCheckQuestionRequest changeQuestion, Long id) {
        DefaultSiteCheck defaultCheck = findDefaultCheck(id);

        defaultCheck.changeQuestion(changeQuestion.getQuestion());
    }

    // 기본 현장 체크 리스트 삭제
    @Transactional
    public void deleteDefaultCheck(Long id) {

        defaultSiteCheckRepository.deleteById(id);
    }

    // 해당 기본 현장 체크 리스트 조회
    public DefaultSiteCheck findDefaultCheck(Long id) {
        return defaultSiteCheckRepository.findById(id).orElseThrow(() -> DefaultSiteCheckNotFoundException.EXCEPTION);
    }

}
