package devgraft.quiz.domain;

import devgraft.support.jpa.BaseEntity;
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
@AllArgsConstructor
@Table(name = "QUIZ")
@NoArgsConstructor
@Getter
@Entity
public class Quiz extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void update(final String title, final String desc, final Long answer, final Long timer,
                       final String select1, final String select2, final String select3, final String select4,
                       final LocalDate openAt, final LocalTime openTime, final LocalTime endTime) {
        this.title = title;
        this.desc = desc;
        this.answer = answer;
        this.timer = timer;
        this.isAnswer = 0L != answer;
        this.select1 = select1;
        this.select2 = select2;
        this.select3 = select3;
        this.select4 = select4;
        this.openAt = openAt;
        this.openTime = openTime;
        this.endTime = endTime;
    }
}
