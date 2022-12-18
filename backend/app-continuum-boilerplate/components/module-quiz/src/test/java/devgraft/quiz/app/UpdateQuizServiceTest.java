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

class UpdateQuizServiceTest {
    private UpdateQuizService updateQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        BDDMockito.given(quizRepository.findQuizByOpenAt(any())).willReturn(Optional.empty());
        updateQuizService = new UpdateQuizService(quizRepository);
    }

    @DisplayName("퀴즈 요청문이 정확하지 않으면 예외처리")
    @Test
    void updateQuiz_throw_ValidationException() {
        final UpdateQuizRequest givenRequest = new UpdateQuizRequest();

        final ValidationException validationException = assertThrows(ValidationException.class, () -> updateQuizService.updateQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("quizId", "must null not be quizId"));
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
        final UpdateQuizRequest givenRequest = UpdateQuizRequest.builder()
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(2, 45))
                .build();

        final ValidationException validationException = assertThrows(ValidationException.class,
                () -> updateQuizService.updateQuiz(givenRequest));

        ValidationAsserts.assertHasCall(validationException.getErrors(), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"));
    }

    @DisplayName("openAt은 중복되어선 안된다.")
    @Test
    void updateQuiz_throw_DuplicatedOpenAtException() {
        final LocalDate givenOpenAt = LocalDate.of(2022, 12, 18);
        final UpdateQuizRequest givenRequest = UpdateQuizRequest.builder()
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .openAt(givenOpenAt)
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                .build();

        BDDMockito.given(quizRepository.findQuizByOpenAt(givenOpenAt)).willReturn(Optional.of(Quiz.builder().build()));

        final RequestException requestException = assertThrows(RequestException.class,
                () -> updateQuizService.updateQuiz(givenRequest));

        Assertions.assertThat(requestException.getMessage()).isEqualTo("openAt은 중복되어선 안된다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("id에 해당하는 Quiz가 존재해야한다.")
    @Test
    void updateQuiz_throw_NotExistsQuiz() {
        // given
        final Long quizId = 1L;
        final UpdateQuizRequest givenRequest = UpdateQuizRequest.builder()
                .quizId(quizId)
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .openAt(LocalDate.of(2022,12,18))
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                .build();

        BDDMockito.given(quizRepository.findById(quizId)).willReturn(Optional.empty());
        // when
        final RequestException requestException = assertThrows(RequestException.class,
                () -> updateQuizService.updateQuiz(givenRequest));
        //then
        Assertions.assertThat(requestException.getMessage()).isEqualTo("해당 퀴즈가 존재하지 않습니다.");
        Assertions.assertThat(requestException.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @DisplayName("UpdateQuizRequest값이 Quiz와 동일한지 검사")
    @Test
    void editQuiz_equalsTrue() {
        final UpdateQuizRequest givenRequest = UpdateQuizRequest.builder()
                .quizId(1L)
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .answer(2L)
                .openAt(LocalDate.of(2022, 12, 19))
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                .build();
        final Quiz returnQuiz = Quiz.builder()
                .title("AT")
                .desc("DE")
                .timer(100L)
                .answer(2L)
                .select1("SE1")
                .select2("SE1")
                .select3("SE1")
                .select4("SE1")
                .openAt(givenRequest.getOpenAt().plusDays(1L))
                .openTime(givenRequest.getOpenTime().plusHours(2L))
                .endTime(givenRequest.getEndTime().plusHours(4L))
                .build();

        BDDMockito.given(quizRepository.findById(givenRequest.getQuizId())).willReturn(Optional.of(returnQuiz));

        updateQuizService.updateQuiz(givenRequest);

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