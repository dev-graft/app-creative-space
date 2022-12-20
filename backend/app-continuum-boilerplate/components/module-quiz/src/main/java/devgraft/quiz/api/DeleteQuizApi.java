package devgraft.quiz.api;

import devgraft.quiz.app.DeleteQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("admin")
@RequiredArgsConstructor
@RestController
public class DeleteQuizApi {
    private final DeleteQuizService deleteQuizService;

    @DeleteMapping(QuizConstants.DOMAIN_NAME)
    public void deleteQuiz(final Long quizId) {
        deleteQuizService.delete(quizId);
    }
}
