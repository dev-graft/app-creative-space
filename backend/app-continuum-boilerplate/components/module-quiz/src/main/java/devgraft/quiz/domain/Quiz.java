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
    private Long timer;
    private Long answer;
    private String select1;
    private String select2;
    private String select3;
    private String select4;
    private LocalDate openAt;
    private LocalTime openTime;
    private LocalTime endTime;

    public static Quiz create(final QuizCreatedData body) {
        return new Quiz(
                null,
                body.getTitle(),
                body.getDesc(),
                body.getTimer(),
                body.getAnswer(),
                body.getSelect1(),
                body.getSelect2(),
                body.getSelect3(),
                body.getSelect4(),
                body.getOpenAt(),
                body.getOpenTime(),
                body.getEndTime());
    }

    public void update(final QuizUpdatedData body) {
        this.title = body.getTitle();
        this.desc = body.getDesc();
        this.answer = body.getAnswer();
        this.timer = body.getTimer();
        this.select1 = body.getSelect1();
        this.select2 = body.getSelect2();
        this.select3 = body.getSelect3();
        this.select4 = body.getSelect4();
        this.openAt = body.getOpenAt();
        this.openTime = body.getOpenTime();
        this.endTime = body.getEndTime();
    }
}
