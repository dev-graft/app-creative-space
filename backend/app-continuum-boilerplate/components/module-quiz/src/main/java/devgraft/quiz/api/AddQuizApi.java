package devgraft.quiz.api;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.quiz.app.AddQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Profile("admin")
@RequiredArgsConstructor
@RestController
public class AddQuizApi {
    private final AddQuizService addQuizService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(QuizConstants.DOMAIN_NAME)
    public Long addQuiz(@RequestBody final AddQuizRequest request) {
        return addQuizService.addQuiz(request);
    }
}