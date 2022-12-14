package devgraft.quiz.api;

import devgraft.quiz.app.QuizResponse;
import devgraft.quiz.query.QuizAddAvailableDateDto;
import devgraft.quiz.query.QuizDetailDto;
import devgraft.quiz.query.QuizItemDto;
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

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuizQueryApi {
    private final QuizQueryDslRepository repository;

    @ApiOperation("퀴즈 조회")
    @GetMapping(QuizConstants.DOMAIN_NAME + "/detail")
    public QuizResponse getQuizDetail(@RequestParam(name = "quizId") final Long id) {
        final QuizDetailDto quizData = repository.findOne(id).orElseThrow(() -> RequestException.of(HttpStatus.NOT_FOUND, "뭘끼"));

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
    public Page<QuizItemDto> getQuizList(@RequestParam(value = "keyword", defaultValue = "%") final String keyword,
                                         @RequestParam(value = "ym", required = false) final YearMonth ym,
                                         @RequestParam(value = "page", defaultValue = "0") final int page,
                                         @RequestParam(value = "offset", defaultValue = "20") final int offset) {
        return repository.findAll(keyword, ym, PageRequest.of(page, offset));
    }

    @ApiOperation("퀴즈 추가 가능 일자 월 단위 조회")
    @GetMapping(QuizConstants.DOMAIN_NAME + "/available")
    public List<QuizAddAvailableDateResponse> getQuizAddAvailableDate(@RequestParam(value = "ym", defaultValue = "#{T(java.time.YearMonth).now()}") final YearMonth ym) {
        final List<QuizAddAvailableDateDto> data = repository.findAddNotAvailableDate(ym);
        List<LocalDate> dates = data.stream()
                .map(QuizAddAvailableDateDto::getDate)
                .toList();
        final List<LocalDate> month = ym.atDay(1).datesUntil(ym.atEndOfMonth().plusDays(1L)).toList();

        return month.stream()
                .map(value -> QuizAddAvailableDateResponse.of(value, !dates.contains(value)))
                .toList();
    }
}
