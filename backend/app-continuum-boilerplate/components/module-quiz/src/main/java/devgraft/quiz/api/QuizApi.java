package devgraft.quiz.api;

import devgraft.quiz.app.*;
import devgraft.quiz.query.QuizData;
import devgraft.quiz.query.QuizDataDao;
import devgraft.quiz.query.QuizDataSpec;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags="퀴즈 Api")
@RequestMapping(QuizConstants.DOMAIN_NAME)
@RequiredArgsConstructor
@RestController
public class QuizApi {
    private final AddQuizService addQuizService;
    private final UpdateQuizService updateQuizService;
    private final DeleteQuizService deleteQuizService;
    private final QuizDataDao quizDataDao;

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

    @ApiOperation("퀴즈 조회")
    @GetMapping
    public QuizResponse getQuiz(@RequestParam(name = "id") final Long id) {
        final QuizData quizData = quizDataDao.findOne(QuizDataSpec.idEquals(id))
                .orElse(null);
        return QuizResponse.builder()
                .id(quizData.getId())
                .title(quizData.getTitle())
                .desc(quizData.getDesc())
                .timer(quizData.getTimer())
                .answer(quizData.getAnswer())
                .select1(quizData.getSelect1())
                .select2(quizData.getSelect2())
                .select3(quizData.getSelect3())
                .select4(quizData.getSelect4())
                .openAt(quizData.getOpenAt())
                .openTime(quizData.getOpenTime())
                .endTime(quizData.getEndTime())
                .build();
    }
}
