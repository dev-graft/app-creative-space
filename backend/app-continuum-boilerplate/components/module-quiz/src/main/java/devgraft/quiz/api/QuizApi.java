package devgraft.quiz.api;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.quiz.app.AddQuizService;
import devgraft.quiz.app.DeleteQuizService;
import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="퀴즈 Api")
@RequestMapping(QuizConstants.DOMAIN_NAME)
@RequiredArgsConstructor
@RestController
public class QuizApi {
    private final AddQuizService addQuizService;
    private final UpdateQuizService updateQuizService;
    private final DeleteQuizService deleteQuizService;

    @ApiOperation("퀴즈 추가")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Long addQuiz(@RequestBody final AddQuizRequest request) {
        return addQuizService.addQuiz(request);
    }

    @ApiOperation("퀴즈 수정")
    @PutMapping
    public void updateQuiz(@RequestBody final UpdateQuizRequest request) {
        updateQuizService.updateQuiz(request);
    }

    @ApiOperation("퀴즈 삭제")
    @DeleteMapping
    public void deleteQuiz(final Long quizId) {
        deleteQuizService.delete(quizId);
    }
}
