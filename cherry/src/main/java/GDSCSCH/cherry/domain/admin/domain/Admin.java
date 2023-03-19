package GDSCSCH.cherry.domain.admin.domain;

import GDSCSCH.cherry.domain.admin.domain.vo.AdminInfoVo;
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
    private int adminAge;

    @Enumerated(STRING)
    private Role role = ADMIN;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "site_info_id")
    private SiteInfo siteInfo;

    @Builder
    public Admin(String adminName, String adminEmail, String adminPhoneNum, int adminAge, Role role) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPhoneNum = adminPhoneNum;
        this.adminAge = adminAge;
        this.role = role;
    }

    //생성 메서드
    public static Admin createAdmin(String adminName, String adminEmail, String adminPhoneNum, int adminAge) {
        return builder()
                .adminName(adminName)
                .adminEmail(adminEmail)
                .adminPhoneNum(adminPhoneNum)
                .adminAge(adminAge)
                .role(ADMIN)
                .build();
    }

    //관리자 정보 수정
    public void changeAdminInfo(String adminName,String adminPhoneNum, int adminAge) {
        this.adminName = adminName;
        this.adminPhoneNum = adminPhoneNum;
        this.adminAge = adminAge;
    }

    public AdminInfoVo getAdminInfo() {
        return new AdminInfoVo(id, adminName, adminEmail, adminPhoneNum, adminAge, role, siteInfo);
    }

    //== 연관 관계 메서드==//
    public void addSiteInfo(SiteInfo site) {
        this.siteInfo = site;
    }
    public void subSiteInfo() {
        this.siteInfo = null;
    }
}
