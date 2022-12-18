package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizRepository;
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
import static org.mockito.ArgumentMatchers.refEq;

class AddQuizServiceTest {
    private AddQuizService addQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        addQuizService = new AddQuizService(quizRepository);
    }

    @DisplayName("퀴즈 요청문이 정확하지 않으면 예외처리")
    @Test
    void addQuiz_throw_ValidationException() {
        final AddQuizRequest givenRequest = new AddQuizRequest();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("title", "must null not be Title"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("desc", "must null not be desc"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("select1", "must null not be select1"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("select2", "must null not be select2"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openAt", "must null not be openAt"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "must null not be openTime"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("endTime", "must null not be endTime"));
        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("퀴즈 openTime이 endTime보다 이전이 아닐 경우 예외처리")
    @Test
    void addQuiz_throw_ValidationException2() {
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(2, 45))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("퀴즈 openTime은 endTime과 30분이상 차이가 나야한다.")
    @Test
    void addQuiz_throw_ValidationException3() {
        final AddQuizRequest givenRequest = AddQuizRequest.builder()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(4, 0))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> addQuizService.addQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime은 endTime과 30분 차이가 나야한다."));
    }

    @DisplayName("openAt은 중복되어선 안된다.")
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

        BDDMockito.given(quizRepository.findQuizByOpenAt(refEq(givenOpenAt))).willReturn(Optional.of(Quiz.builder().build()));


        final RequestException requestException = assertThrows(RequestException.class,
                () -> addQuizService.addQuiz(givenRequest));

        Assertions.assertThat(requestException.getMessage()).isEqualTo("openAt은 중복되어선 안된다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("AddQuizRequest값이 Quiz와 동일한지 검사")
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
        BDDMockito.given(quizRepository.findQuizByOpenAt(any())).willReturn(Optional.empty());

        addQuizService.addQuiz(givenRequest);

        final Quiz saveQuiz = Quiz.builder()
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