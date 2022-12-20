package devgraft.quiz.api;

import devgraft.quiz.app.DeleteQuizService;
import devgraft.quiz.config.QuizConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("퀴즈 삭제 Api")
class DeleteQuizApiTest {
    private MockMvc mockMvc;
    private DeleteQuizService deleteQuizService;

    @BeforeEach
    void setUp() {
        deleteQuizService = Mockito.mock(DeleteQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DeleteQuizApi(deleteQuizService)).build();
    }

    @DisplayName("요청이 성공했을 경우 HttpStatus.OK를 반환한다.")
    @Test
    void deleteQuiz_returnOkHttpStatus() throws Exception {
        request(0L).andExpect(status().isOk());
    }

    @DisplayName("삭제하려는 QuizId는 Service에 전달되어야한다.")
    @Test
    void deleteQuiz_passes_QuizId_DeleteQuizService() throws Exception {
        final Long givenQuizId = 5L;

        request(givenQuizId);

        Mockito.verify(deleteQuizService).delete(eq(givenQuizId));
    }

    private ResultActions request(final Long quizId) throws Exception {
        return mockMvc.perform(delete(QuizConstants.DOMAIN_NAME)
                        .param("quizId", String.valueOf(quizId)));
    }
}