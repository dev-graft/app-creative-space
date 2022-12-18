package devgraft.quiz.app;

import devgraft.quiz.domain.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class DeleteQuizServiceTest {
    private DeleteQuizService deleteQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        deleteQuizService = new DeleteQuizService(quizRepository);
    }

    @DisplayName("퀴즈 삭제 대상 아이디 DeleteRepository 전달")
    @Test
    void delete_passes_quizId_delete() {
        final Long givenQuizId = 10L;

        deleteQuizService.delete(givenQuizId);

        Mockito.verify(quizRepository).deleteQuizById(ArgumentMatchers.eq(givenQuizId));
    }
}