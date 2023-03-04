package GDSCSCH.cherry.domain.defaultSiteCheck.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class DefaultSiteCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String defaultQuestion;

    @Builder
    public DefaultSiteCheck(String defaultQuestion) {
        this.defaultQuestion = defaultQuestion;
    }

    //기본 생성자
    public static DefaultSiteCheck createDefaultCheck(String defaultQuestion) {
        return builder()
                .defaultQuestion(defaultQuestion)
                .build();
    }

    // 기본 체크 리스트 질문 수정
    public void changeQuestion(String defaultQuestion) {
        this.defaultQuestion = defaultQuestion;
    }
}
