package devgraft.quiz.api;

import devgraft.quiz.app.DeleteQuizService;
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

class DeleteQuizApiTest {
    private MockMvc mockMvc;
    private DeleteQuizService deleteQuizService;

    @BeforeEach
    void setUp() {
        deleteQuizService = Mockito.mock(DeleteQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DeleteQuizApi(deleteQuizService)).build();
    }

    @DisplayName("퀴즈 삭제 요청 Ok status 반환")
    @Test
    void deleteQuiz_returnOkHttpStatus() throws Exception {
        request(0L).andExpect(status().isOk());
    }

    @DisplayName("퀴즈 삭제 대상 아이디 DeleteQuizService 전달")
    @Test
    void deleteQuiz_passes_QuizId_DeleteQuizService() throws Exception {
        final Long givenQuizId = 5L;

        request(givenQuizId);

        Mockito.verify(deleteQuizService).delete(eq(givenQuizId));
    }

    private ResultActions request(final Long quizId) throws Exception {
        return mockMvc.perform(delete("/quiz")
                        .param("quizId", String.valueOf(quizId)));
    }
}