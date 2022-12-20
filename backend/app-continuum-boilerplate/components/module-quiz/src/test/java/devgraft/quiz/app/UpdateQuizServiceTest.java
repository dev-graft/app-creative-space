package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizRepository;
import devgraft.quiz.service.UpdateQuizRequestFixture;
import devgraft.support.exception.RequestException;
import devgraft.support.exception.ValidationAsserts;
import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("퀴즈 업데이트 서비스")
class UpdateQuizServiceTest {
    private UpdateQuizService updateQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        updateQuizService = new UpdateQuizService(quizRepository);
    }

    @DisplayName("요청문이 요구사항에 맞지않으면 에러를 반환한다.")
    @Test
    void updateQuiz_throw_ValidationException() {
        final UpdateQuizRequest givenRequest = UpdateQuizRequestFixture.anEmptyRequest().build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> updateQuizService.updateQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("quizId", "must quizId not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("title", "must title not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("desc", "must desc not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("select1", "must select1 not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("select2", "must select2 not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openAt", "must openAt not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "must openTime not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("endTime", "must endTime not be null"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("openTime이 endTime 이후라면 에러를 반환한다.")
    @Test
    void addQuiz_throw_ValidationException2() {
        final UpdateQuizRequest givenRequest = UpdateQuizRequestFixture.anRequest()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(2, 45))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class,
                () -> updateQuizService.updateQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("openAt을 업데이트 할 때 Repo에 [자신 제외] 동일한 openAt이 이미 존재할 경우 에러를 반환한다.")
    @Test
    void updateQuiz_throw_DuplicatedOpenAtException() {
        final LocalDate givenOpenAt = LocalDate.of(2022, 12, 18);
        final UpdateQuizRequest givenRequest = UpdateQuizRequestFixture.anRequest()
                .openAt(givenOpenAt)
                .build();

        BDDMockito.given(quizRepository.findById(any())).willReturn(Optional.of(QuizFixture.anQuiz().build()));
        BDDMockito.given(quizRepository.findTopByOpenAt(givenOpenAt)).willReturn(Optional.of(Quiz.builder().build()));

        final RequestException requestException = assertThrows(RequestException.class,
                () -> updateQuizService.updateQuiz(givenRequest));

        Assertions.assertThat(requestException.getMessage()).isEqualTo("openAt은 중복되어선 안된다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("id에 해당하는 Quiz가 존재하지 않는다면 에러를 반환한다.")
    @Test
    void updateQuiz_throw_NotExistsQuiz() {
        final Long quizId = 1L;
        final UpdateQuizRequest givenRequest = UpdateQuizRequestFixture.anRequest().build();
        BDDMockito.given(quizRepository.findById(quizId)).willReturn(Optional.empty());

        final RequestException requestException = assertThrows(RequestException.class,
                () -> updateQuizService.updateQuiz(givenRequest));

        Assertions.assertThat(requestException.getMessage()).isEqualTo("해당 퀴즈가 존재하지 않습니다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @DisplayName("Quiz의 업데이트 된 내용은 요청문과 동일하다.")
    @Test
    void editQuiz_equalsTrue() {
        final Long givenQUizId = 2L;
        final UpdateQuizRequest givenRequest = UpdateQuizRequestFixture.anRequest()
                .quizId(givenQUizId)
                .build();
        final Quiz updateTargetQuiz = QuizFixture.anQuiz()
                .id(givenQUizId)
                .build();

        BDDMockito.given(quizRepository.findById(givenRequest.getQuizId())).willReturn(Optional.of(updateTargetQuiz));

        updateQuizService.updateQuiz(givenRequest);

        final Quiz saveQuiz = QuizFixture.anQuiz()
                .id(givenQUizId)
                .title(givenRequest.getTitle())
                .desc(givenRequest.getDesc())
                .timer(givenRequest.getTimer())
                .answer(givenRequest.getAnswer())
                .isAnswer(0L != givenRequest.getAnswer())
                .select1(givenRequest.getSelect1())
                .select2(givenRequest.getSelect2())
                .select3(givenRequest.getSelect3())
                .select4(givenRequest.getSelect4())
                .openAt(givenRequest.getOpenAt())
                .openTime(givenRequest.getOpenTime())
                .endTime(givenRequest.getEndTime())
                .build();

        Mockito.verify(quizRepository).save(ArgumentMatchers.refEq(saveQuiz));
    }
}