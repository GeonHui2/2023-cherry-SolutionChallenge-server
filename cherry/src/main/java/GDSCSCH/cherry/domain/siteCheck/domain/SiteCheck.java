package GDSCSCH.cherry.domain.siteCheck.domain;

import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class SiteCheck {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "site_check_id")
    private Long id;

    private String siteQuestion;
    private boolean siteAnswer;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "site_info_id")
    private SiteInfo siteInfo;

    @Builder
    public SiteCheck(String siteQuestion, boolean siteAnswer, SiteInfo siteInfo) {
        this.siteQuestion = siteQuestion;
        this.siteAnswer = siteAnswer;
        this.siteInfo = siteInfo;
    }


    public void mappingSiteCheck(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }
}
