package devgraft.quiz.app;

import devgraft.exception.ValidationAsserts;
import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizFixture;
import devgraft.quiz.domain.QuizRepository;
import devgraft.supports.exception.RequestException;
import devgraft.supports.exception.ValidationError;
import devgraft.supports.exception.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("퀴즈 추가 서비스")
class AddQuizServiceTest {
    private AddQuizService addQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        addQuizService = new AddQuizService(quizRepository);
    }

    @DisplayName("요청문이 요구사항에 맞지않으면 에러를 반환한다.")
    @Test
    void addQuiz_throw_ValidationException() {
        final AddQuizRequest givenRequest = AddQuizRequest.builder().build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

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
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(2, 45))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("openTime, endTime이 30분이상 차이 나지 않는다면 에러를 반환한다.")
    @Test
    void addQuiz_throw_ValidationException3() {
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(4, 0))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime은 endTime과 30분 차이가 나야한다."));
    }

    @DisplayName("저장된 Quiz 중 요청문과 동일한 openAt이 이미 존재할 경우 에러를 반환한다.")
    @Test
    void addQuiz_throw_DuplicatedOpenAtException() {
        final LocalDate givenOpenAt = LocalDate.of(2022, 12, 18);
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .openAt(givenOpenAt)
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                .build();

        BDDMockito.given(quizRepository.findTopByOpenAt(refEq(givenOpenAt))).willReturn(Optional.of(QuizFixture.anQuiz().build()));

        final RequestException requestException = assertThrows(RequestException.class,
                () -> addQuizService.addQuiz(givenRequest));

        Assertions.assertThat(requestException.getMessage()).isEqualTo("openAt은 중복되어선 안된다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("저장한 Quiz의 정보는 요청문의 정보와 일치한다.")
    @Test
    void addQuiz_equalsTrue() {
        final LocalDate givenOpenAt = LocalDate.of(2022, 12, 18);
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .openAt(givenOpenAt)
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                .build();

        addQuizService.addQuiz(givenRequest);

        final Quiz saveQuiz = Quiz.create(givenRequest.toDomain());
        verify(quizRepository, times(1)).save(refEq(saveQuiz));
    }
}