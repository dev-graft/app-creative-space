package devgraft.quiz.domain;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.support.exception.RequestException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizHelper {
    public static Quiz create(final AddQuizRequest request) {
        return Quiz.builder()
                .title(request.getTitle())
                .desc(request.getDesc())
                .timer(request.getTimer())
                .answer(request.getAnswer())
                .isAnswer(0L != request.getAnswer())
                .select1(request.getSelect1())
                .select2(request.getSelect2())
                .select3(request.getSelect3())
                .select4(request.getSelect4())
                .openAt(request.getOpenAt())
                .openTime(request.getOpenTime())
                .endTime(request.getEndTime())
                .build();
    }

    public static void existThrowQuizByOpenAt(final QuizRepository repository, final LocalDate openAt) {
        if (repository.findQuizByOpenAt(openAt).isPresent()) {
            throw RequestException.of(HttpStatus.BAD_REQUEST, "openAt은 중복되어선 안된다.");
        }
    }
}
