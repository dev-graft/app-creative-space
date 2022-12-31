package devgraft.quiz.domain;

import devgraft.supports.exception.RequestException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizHelper {
    public static void existThrowQuizByOpenAt(final QuizRepository repository, final LocalDate openAt) {
        if (repository.findTopByOpenAt(openAt).isPresent()) {
            throw RequestException.of(HttpStatus.BAD_REQUEST, "openAt은 중복되어선 안된다.");
        }
    }
}
