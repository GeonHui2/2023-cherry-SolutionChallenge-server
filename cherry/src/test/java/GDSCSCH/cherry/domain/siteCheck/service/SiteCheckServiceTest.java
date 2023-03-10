//package GDSCSCH.cherry.domain.siteCheck.service;
//
//import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
//import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
//import GDSCSCH.cherry.domain.siteCheck.domain.repository.SiteCheckRepository;
//import GDSCSCH.cherry.domain.siteCheck.presentation.dto.response.SiteCheckResponse;
//import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
//import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class SiteCheckServiceTest {
//
//    @Autowired
//    SiteCheckRepository siteCheckRepository;
//
//    @Autowired
//    SiteInfoRepository siteInfoRepository;
//
//    @Autowired
//    AdminRepository adminRepository;
//
////    @Test
////    @DisplayName("현장 체크 리스트 입력")
////    void addSiteCheck() {
////        //given
////        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
////
////        siteInfoRepository.save(site);
////
////        //when
////        SiteCheck check = SiteCheck.createSiteCheck("질문1");
////
////        siteCheckRepository.save(check);
////
////        site.addSiteCheck(check);
////
////        //then
////        log.info("check = {}, {}, {}", check.isSiteAnswer(), check.getSiteQuestion(), check.getSiteInfo().getSiteName());
////    }
//
//    @Test
//    @DisplayName("현장 체크 리스트 조회")
//    void getCheckList() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        SiteCheck check1 = SiteCheck.createSiteCheck("질문1");
//        SiteCheck check2 = SiteCheck.createSiteCheck("질문2");
//        SiteCheck check3 = SiteCheck.createSiteCheck("질문3");
//        SiteCheck check4 = SiteCheck.createSiteCheck("질문4");
//
//        siteCheckRepository.save(check1);
//        siteCheckRepository.save(check2);
//        siteCheckRepository.save(check3);
//        siteCheckRepository.save(check4);
//
//        site.addSiteCheck(check1);
//        site.addSiteCheck(check2);
//        site.addSiteCheck(check3);
//        site.addSiteCheck(check4);
//
//        //when
//        List<SiteCheck> siteCheckList = siteCheckRepository.findAllBySiteInfo(site);
//
//        List<SiteCheckResponse> collect = siteCheckList.stream().map(c -> new SiteCheckResponse(c.getCheckInfo())).collect(Collectors.toList());
//
//        //then
//        for (SiteCheckResponse s : collect) {
//            log.info("info = {}, {}", s.getSiteQuestion(), s.isSiteAnswer());
//        }
//    }
//
//    @Test
//    @DisplayName("현장 체크 질문 삭제")
//    void deleteSiteCheck() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        SiteCheck check = SiteCheck.createSiteCheck("질문1");
//
//        siteCheckRepository.save(check);
//
//        site.addSiteCheck(check);
//
//        //when
//        check.getSiteInfo().subSiteCheck(check);
//        siteCheckRepository.deleteById(check.getId());
//
//        //then
//        assertEquals(0, siteCheckRepository.count());
//    }
//
//    @Test
//    @DisplayName("현장 체크 질문 수정")
//    void changeCheckQuestion() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        SiteCheck check = SiteCheck.createSiteCheck("질문1");
//
//        siteCheckRepository.save(check);
//
//        site.addSiteCheck(check);
//
//        //when
//        check.updateSiteCheckAnswer(true);
//
//        //then
//        log.info("check = {}", check.isSiteAnswer());
//        assertEquals(true, check.isSiteAnswer());
//    }
//
//    @Test
//    @DisplayName("현장 체크 리스트 상태 수정")
//    void updateSiteCheckAnswer() {
//        //given
//        SiteInfo site = SiteInfo.createSiteInfo("site", 104.2, 210.1, null);
//
//        siteInfoRepository.save(site);
//
//        SiteCheck check = SiteCheck.createSiteCheck("질문1");
//
//        siteCheckRepository.save(check);
//
//        site.addSiteCheck(check);
//
//        //when
//        check.changeQuestion("질문 수정");
//
//        //then
//        assertEquals("질문 수정", check.getSiteQuestion());
//    }
//}