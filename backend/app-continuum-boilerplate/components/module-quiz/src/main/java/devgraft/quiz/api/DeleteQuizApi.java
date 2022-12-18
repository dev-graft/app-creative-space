package devgraft.quiz.api;

import devgraft.quiz.app.DeleteQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteQuizApi {
    private final DeleteQuizService deleteQuizService;

    @DeleteMapping(value = "quiz")
    public void deleteQuiz(final Long quizId) {
        deleteQuizService.delete(quizId);
    }
}
