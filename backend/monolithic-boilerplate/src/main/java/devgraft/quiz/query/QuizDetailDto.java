package devgraft.quiz.query;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class QuizDetailDto {
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
}
