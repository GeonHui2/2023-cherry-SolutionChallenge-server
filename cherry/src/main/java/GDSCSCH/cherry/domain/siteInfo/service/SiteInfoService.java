package GDSCSCH.cherry.domain.siteInfo.service;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
import GDSCSCH.cherry.domain.siteCheck.domain.repository.SiteCheckRepository;
import GDSCSCH.cherry.domain.siteCheck.service.SiteCheckService;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
import GDSCSCH.cherry.domain.siteInfo.exception.SiteInfoNotFoundException;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.AddSiteInfoRequest;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.UpdateSiteInfoRequest;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.response.SiteInfoResponse;
import GDSCSCH.cherry.domain.user.domain.User;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.global.utils.admin.AdminUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SiteInfoService implements SiteInfoUtils{

    private final SiteInfoRepository siteInfoRepository;
    private final SiteCheckRepository siteCheckRepository;
    private final UserRepository userRepository;
    private final SiteCheckService siteCheckService;
    private final AdminUtils adminUtils;

    //현장 정보 생성
    @Transactional
    public Long addSiteInfo(AddSiteInfoRequest addSiteInfoRequest) {
        Admin currentAdmin = adminUtils.getAdminFromSecurityContext();

        SiteInfo siteInfo = SiteInfo.createSiteInfo(
                addSiteInfoRequest.getSiteName(),
                addSiteInfoRequest.getSiteLatitude(),
                addSiteInfoRequest.getSiteLongitude(),
                addSiteInfoRequest.getAddress1(),
                addSiteInfoRequest.getAddress2(),
                currentAdmin
        );

        siteInfoRepository.save(siteInfo);

        currentAdmin.addSiteInfo(siteInfo);

        siteCheckService.addDefaultCheckList(siteInfo.getId());

        return siteInfo.getId();
    }

    //현장 정보 조회
    public SiteInfoResponse getDetailSiteInfo(Long siteId) {
        SiteInfo site = findSiteInfo(siteId);

        return new SiteInfoResponse(site.getSiteInfoVo());
    }

    //현장 정보 수정
    @Transactional
    public void updateSiteInfo(Long siteId, UpdateSiteInfoRequest updateSiteInfoRequest) {
        Admin admin = adminUtils.getAdminFromSecurityContext();

        SiteInfo site = findSiteInfo(siteId);

        site.updateSiteInfo(updateSiteInfoRequest);
    }

    //현장 정보 삭제
    @Transactional
    public void deleteSiteInfo(Long siteId) {
        Admin admin = adminUtils.getAdminFromSecurityContext();

        SiteInfo site = findSiteInfo(siteId);

        admin.subSiteInfo();
        List<User> userList = userRepository.findAllBySiteInfo(site);
        for (User u : userList) {
            site.subUser(u);
            u.changeRole(Role.GUEST);
        }

        siteInfoRepository.delete(site);
    }

    //현장 코드 유효 여부 조회
    public boolean checkValidSiteCode(String siteCode) {
        return siteInfoRepository.existsBySiteCode(siteCode);
    }

    // 출근 시스템
    @Transactional
    public void workStart(Long siteId) {
        SiteInfo site = findSiteInfo(siteId);

        List<User> userList = userRepository.findAllBySiteInfoAndRole(site, Role.USER);
        List<User> staffList = userRepository.findAllBySiteInfoAndRole(site, Role.STAFF);

        for (User u : userList) u.changeHelmetCheck(false);
        for (User u : staffList) u.changeHelmetCheck(false);

        List<SiteCheck> checkList = siteCheckRepository.findAllBySiteInfo(site);
        for (SiteCheck c : checkList) c.updateSiteCheckAnswer(false);
    }

    //해당 현장 조회
    @Override
    public SiteInfo findSiteInfo(Long id) {
        return siteInfoRepository.findById(id).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
    }


}
