package devgraft.quiz.app;

import devgraft.support.exception.ValidationAsserts;
import devgraft.support.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        ValidationAsserts.assertHasCall(validationException.getErrors(), "title", "must null not be Title");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "desc", "must null not be desc");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "select1", "must null not be select1");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "select2", "must null not be select2");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "openAt", "must null not be openAt");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "openTime", "must null not be openTime");
        ValidationAsserts.assertHasCall(validationException.getErrors(), "endTime", "must null not be endTime");
    }
}