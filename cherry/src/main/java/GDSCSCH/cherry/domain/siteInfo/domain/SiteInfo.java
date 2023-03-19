package GDSCSCH.cherry.domain.siteInfo.domain;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
import GDSCSCH.cherry.domain.siteInfo.domain.vo.SiteInfoVo;
import GDSCSCH.cherry.domain.siteInfo.exception.NotAdminException;
import GDSCSCH.cherry.domain.siteInfo.presentation.dto.request.UpdateSiteInfoRequest;
import GDSCSCH.cherry.domain.siteInfo.service.dto.UpdateSiteInfoDto;
import GDSCSCH.cherry.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class SiteInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "site_info_id")
    private Long id;

    private String siteCode;
    private String siteName;
    private Double siteLatitude;
    private Double siteLongitude;
    private String address1;
    private String address2;

    @OneToMany(mappedBy = "siteInfo")
    private List<User> userList = new ArrayList<>();

    @OneToOne(fetch = LAZY, mappedBy = "siteInfo")
    private Admin admin;

    @OneToMany(mappedBy = "siteInfo", cascade = CascadeType.ALL)
    private List<SiteCheck> siteCheckList = new ArrayList<>();

    @Builder
    public SiteInfo(String siteCode, String siteName, Double siteLatitude, Double siteLongitude, String address1, String address2, Admin admin) {
        this.siteCode = siteCode;
        this.siteName = siteName;
        this.siteLatitude = siteLatitude;
        this.siteLongitude = siteLongitude;
        this.address1 = address1;
        this.address2 = address2;
        this.admin = admin;
    }

    //생성 메서드
    public static SiteInfo createSiteInfo(String siteName, Double siteLatitude, Double siteLongitude, String address1, String address2, Admin admin) {
        return builder()
                .siteCode(UUID.randomUUID().toString().substring(0, 8))
                .siteName(siteName)
                .siteLatitude(siteLatitude)
                .siteLongitude(siteLongitude)
                .address1(address1)
                .address2(address2)
                .admin(admin)
                .build();
    }

    //현장 정보 수정
    public void updateSiteInfo(UpdateSiteInfoRequest updateSiteInfoRequest) {
        this.siteName = updateSiteInfoRequest.getSiteName();
        this.siteLatitude = updateSiteInfoRequest.getSiteLatitude();
        this.siteLongitude = updateSiteInfoRequest.getSiteLongitude();
        this.address1 = updateSiteInfoRequest.getAddress1();
        this.address2 = updateSiteInfoRequest.getAddress2();
    }

    //관리자확인
    public void validAdminIsHost(Long id) {
        if (!checkAdminIsHost(id)) {
            throw NotAdminException.EXCEPTION;
        }
    }

    public SiteInfoVo getSiteInfoVo() {
        return SiteInfoVo.builder()
                .siteId(id)
                .siteCode(siteCode)
                .siteName(siteName)
                .siteLatitude(siteLatitude)
                .siteLongitude(siteLongitude)
                .address1(address1)
                .address2(address2)
                .build();
    }

    public boolean checkAdminIsHost(Long id) {
        return admin.getId().equals(id);
    }

    //== 연관 관계 메서드==//
    public void addUser(User user) {
        userList.add(user);
        user.mappingUser(this);
    }

    public void subUser(User user) {
        userList.remove(user);
        user.mappingUser(null);
    }

    public void addSiteCheck(SiteCheck siteCheck) {
        siteCheckList.add(siteCheck);
        siteCheck.mappingSiteCheck(this);
    }

    public void subSiteCheck(SiteCheck siteCheck) {
        siteCheckList.remove(siteCheck);
        siteCheck.mappingSiteCheck(this);
    }
}
