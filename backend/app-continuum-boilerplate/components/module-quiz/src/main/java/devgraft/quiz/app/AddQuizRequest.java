package devgraft.quiz.app;

import devgraft.quiz.domain.QuizCreatedEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AddQuizRequest {
    private String title;
    private String desc;
    private long timer;
    private long answer;
    private String select1;
    private String select2;
    private String select3;
    private String select4;
    private LocalDate openAt;
    private LocalTime openTime;
    private LocalTime endTime;

    public QuizCreatedEvent toDomain() {
        return new QuizCreatedEvent(
                title,
                desc,
                timer,
                answer,
                select1,
                select2,
                select3,
                select4,
                openAt,
                openTime,
                endTime
        );
    }
}
