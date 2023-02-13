package GDSCSCH.cherry.domain.admin.domain;

import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static GDSCSCH.cherry.domain.admin.domain.Role.ADMIN;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Admin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String adminName;
    private String adminEmail;
    private String adminPhoneNum;
    private Integer adminAge;

    @Enumerated(STRING)
    private Role role = ADMIN;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "site_info_id")
    private SiteInfo siteInfo;

    @Builder
    public Admin(String adminName, String adminEmail, String adminPhoneNum, Integer adminAge, Role role, SiteInfo siteInfo) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPhoneNum = adminPhoneNum;
        this.adminAge = adminAge;
        this.role = role;
        this.siteInfo = siteInfo;
    }

    //관리자 정보 수정
    public void changeAdminInfo(String adminName,String adminPhoneNum, Integer adminAge) {
        this.adminName = adminName;
        this.adminPhoneNum = adminPhoneNum;
        this.adminAge = adminAge;
    }
}
