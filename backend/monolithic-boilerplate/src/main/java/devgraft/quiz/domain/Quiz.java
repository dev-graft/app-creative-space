package devgraft.quiz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "QUIZ")
@Entity
@Getter
public class Quiz {
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
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Quiz(final Long id, final String title, final String desc, final Long timer, final Long answer, final String select1, final String select2, final String select3, final String select4, final LocalDate openAt, final LocalTime openTime, final LocalTime endTime) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.timer = timer;
        this.answer = answer;
        this.select1 = select1;
        this.select2 = select2;
        this.select3 = select3;
        this.select4 = select4;
        this.openAt = openAt;
        this.openTime = openTime;
        this.endTime = endTime;
    }

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
