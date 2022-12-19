package devgraft.quiz.domain;

import devgraft.support.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "QUIZ")
@Entity
@Getter
public class Quiz extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String desc;
    private boolean isAnswer;
    private Long timer;
    private Long answer;
    private String select1;
    private String select2;
    private String select3;
    private String select4;
    private LocalDate openAt;
    private LocalTime openTime;
    private LocalTime endTime;

    public static Quiz create(final IQuizBody body) {
        return builder()
                .title(body.getTitle())
                .desc(body.getDesc())
                .timer(body.getTimer())
                .answer(body.getAnswer())
                .isAnswer(0L != body.getAnswer())
                .select1(body.getSelect1())
                .select2(body.getSelect2())
                .select3(body.getSelect3())
                .select4(body.getSelect4())
                .openAt(body.getOpenAt())
                .openTime(body.getOpenTime())
                .endTime(body.getEndTime())
                .build();
    }

    public void update(final IQuizBody body) {
        this.title = body.getTitle();
        this.desc = body.getDesc();
        this.answer = body.getAnswer();
        this.timer = body.getTimer();
        this.isAnswer = 0L != body.getAnswer();
        this.select1 = body.getSelect1();
        this.select2 = body.getSelect2();
        this.select3 = body.getSelect3();
        this.select4 = body.getSelect4();
        this.openAt = body.getOpenAt();
        this.openTime = body.getOpenTime();
        this.endTime = body.getEndTime();
    }
}
