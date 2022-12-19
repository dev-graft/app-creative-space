package devgraft.quiz.api;

import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import devgraft.quiz.config.QuizConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("admin")
@RequiredArgsConstructor
@RestController
public class UpdateQuizApi {
    private final UpdateQuizService updateQuizService;

    @PutMapping(QuizConstants.DOMAIN_NAME)
    public void updateQuiz(@RequestBody final UpdateQuizRequest request) {
        updateQuizService.updateQuiz(request);
    }
}
