package devgraft.quiz.api;

import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class UpdateQuizApi {
    private final UpdateQuizService updateQuizService;

    @PutMapping("quiz")
    public void updateQuiz(@RequestBody final UpdateQuizRequest request) {
        updateQuizService.updateQuiz(request);
    }
}
