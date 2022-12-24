package devgraft.quiz.api;

import devgraft.quiz.app.QuizResponse;
import devgraft.quiz.query.QuizDto;
import devgraft.quiz.query.QuizQueryDslRepository;
import devgraft.support.exception.RequestException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizQueryApi {
    private final QuizQueryDslRepository repository;

    @ApiOperation("퀴즈 조회")
    @GetMapping(QuizConstants.DOMAIN_NAME + "/one")
    public QuizResponse getQuiz(@RequestParam(name = "target") final Long id) {
        final QuizDto quizData = repository.findOne(id).orElseThrow(() -> RequestException.of(HttpStatus.NOT_FOUND, "뭘끼"));

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

    @ApiOperation("퀴즈 목록 조회")
    @GetMapping(QuizConstants.DOMAIN_NAME)
    public Page<QuizDto> getQuizList(@RequestParam(value = "keyword", defaultValue = "%") final String keyword,
                                     @RequestParam(value = "page", defaultValue = "0") final int page,
                                     @RequestParam(value = "offset", defaultValue = "20") final int offset) {
        return repository.findAll(keyword, PageRequest.of(page, offset));
    }
}
