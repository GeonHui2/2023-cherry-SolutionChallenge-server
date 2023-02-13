package GDSCSCH.cherry.domain.user.domain;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private Integer userAge;

    @Enumerated(STRING)
    private Role role;
    private boolean userHelmetCheck;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "site_info_id")
    private SiteInfo siteInfo;

    @Builder
    public User(String userName, String userEmail, String userPhoneNum, Integer userAge, Role role, boolean userHelmetCheck, SiteInfo siteInfo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNum = userPhoneNum;
        this.userAge = userAge;
        this.role = role;
        this.userHelmetCheck = userHelmetCheck;
        this.siteInfo = siteInfo;
    }

    //유저 정보 수정
    public void changeUserInfo(String userName,String userPhoneNum, Integer age) {
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userAge = age;
    }

    //헬멧 착용 여부 수정
    public void changeHelmetCheck(boolean userHelmetCheck) {
        this.userHelmetCheck = userHelmetCheck;
    }

    //유저 권한 수정
    public void changeRole(Role role) {
        this.role = role;
    }

    //
    public void mappingUser(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }
}
