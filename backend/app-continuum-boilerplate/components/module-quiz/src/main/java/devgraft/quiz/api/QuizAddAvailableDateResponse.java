package devgraft.quiz.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
public class QuizAddAvailableDateResponse {
    private LocalDate date;
    private boolean isAvailable;

    public static QuizAddAvailableDateResponse of(final LocalDate date, final boolean isAvailable) {
        return QuizAddAvailableDateResponse.builder()
                .date(date)
                .isAvailable(isAvailable)
                .build();
    }
}
