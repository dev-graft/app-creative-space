package devgraft.quiz.app;

import devgraft.quiz.domain.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("퀴즈 삭제 서비스")
class DeleteQuizServiceTest {
    private DeleteQuizService deleteQuizService;
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        quizRepository = Mockito.mock(QuizRepository.class);
        deleteQuizService = new DeleteQuizService(quizRepository);
    }

    @DisplayName("전달받은 아이디를 기준으로 QuizRepo에 삭제를 요청한다.")
    @Test
    void delete_passes_quizId_delete() {
        final Long givenQuizId = 10L;

        deleteQuizService.delete(givenQuizId);

        Mockito.verify(quizRepository).deleteById(ArgumentMatchers.eq(givenQuizId));
    }
}