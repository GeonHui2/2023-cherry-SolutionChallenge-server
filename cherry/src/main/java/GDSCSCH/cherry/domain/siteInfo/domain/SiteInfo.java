package GDSCSCH.cherry.domain.siteInfo.domain;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.siteCheck.domain.SiteCheck;
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

    private String siteCode = UUID.randomUUID().toString().substring(0, 8);
    private String siteName;
    private Double siteLatitude;
    private Double siteLongitude;

    @OneToMany(mappedBy = "siteInfo")
    private List<User> userList = new ArrayList<>();

    @OneToOne(fetch = LAZY, mappedBy = "siteInfo")
    private Admin admin;

    @OneToMany(mappedBy = "siteInfo")
    private List<SiteCheck> siteCheckList = new ArrayList<>();

    @Builder
    public SiteInfo(String siteName, Double siteLatitude, Double siteLongitude, List<User> userList, Admin admin, List<SiteCheck> siteCheckList) {
        this.siteName = siteName;
        this.siteLatitude = siteLatitude;
        this.siteLongitude = siteLongitude;
        this.userList = userList;
        this.admin = admin;
        this.siteCheckList = siteCheckList;
    }

    //현장 정보 수정
    public void changeSiteInfo(String siteName, Double siteLatitude, Double siteLongitude) {
        this.siteName = siteName;
        this.siteLatitude = siteLatitude;
        this.siteLongitude = siteLongitude;
    }

    //== 연관 관계 메서드==//
    public void addUser(User user) {
        userList.add(user);
        user.mappingUser(this);
    }

    public void subUser(User user) {
        userList.remove(user);
        user.mappingUser(this);
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
