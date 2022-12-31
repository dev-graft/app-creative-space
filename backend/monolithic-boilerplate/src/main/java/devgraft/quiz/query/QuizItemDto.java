package devgraft.quiz.query;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QuizItemDto {
    private Long id;
    private String title;
    private LocalDate openAt;
}
