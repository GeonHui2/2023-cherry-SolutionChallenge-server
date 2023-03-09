package GDSCSCH.cherry.domain.siteCheck.domain;

import GDSCSCH.cherry.domain.siteCheck.domain.vo.SiteCheckInfoVo;
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
    public SiteCheck(String siteQuestion, boolean siteAnswer) {
        this.siteQuestion = siteQuestion;
        this.siteAnswer = siteAnswer;
    }

    //생성 메서드
    public static SiteCheck createSiteCheck(String siteQuestion) {
        return builder()
                .siteQuestion(siteQuestion)
                .siteAnswer(false)
                .build();
    }

    // 체크 리스트 질문 수정
    public void changeQuestion(String siteQuestion) {
        this.siteQuestion = siteQuestion;
    }

    // 체크 리스트 상태 수정
    public void updateSiteCheckAnswer(boolean Answer) {
        this.siteAnswer = Answer;
    }

    public SiteCheckInfoVo getCheckInfo() {
        return new SiteCheckInfoVo(id, siteQuestion, siteAnswer);
    }


    public void mappingSiteCheck(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;
    }
}
