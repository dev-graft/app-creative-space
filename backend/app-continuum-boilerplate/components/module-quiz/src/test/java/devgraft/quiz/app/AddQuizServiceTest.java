package devgraft.quiz.app;

import devgraft.support.exception.ValidationAsserts;
import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddQuizServiceTest {
    private AddQuizService addQuizService;

    @BeforeEach
    void setUp() {
        addQuizService = new AddQuizService();
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
}