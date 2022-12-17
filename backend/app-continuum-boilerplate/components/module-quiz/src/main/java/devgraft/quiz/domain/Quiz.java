package devgraft.quiz.domain;

import devgraft.support.jpa.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "QUIZ")
@Getter
@Entity
public class Quiz extends BaseEntity {
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
}
