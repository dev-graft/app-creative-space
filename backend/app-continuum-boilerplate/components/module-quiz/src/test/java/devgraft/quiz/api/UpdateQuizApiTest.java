package devgraft.quiz.api;

import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import devgraft.support.mapper.ObjectMapperTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UpdateQuizApiTest extends ObjectMapperTestSupport {
    private MockMvc mockMvc;
    private UpdateQuizService updateQuizService;
    @BeforeEach
    void setUp() {
        updateQuizService = Mockito.mock(UpdateQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UpdateQuizApi(updateQuizService)).build();
    }

    @DisplayName("퀴즈 수정 요청 OK Status 반환")
    @Test
    void updateQuiz_returnOkStatus() throws Exception {
        request(new UpdateQuizRequest())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @DisplayName("updateQuiz 서비스 요청문 전달")
    @Test
    void updateQuiz_passes_request_UpdateQuizService() throws Exception {
        final UpdateQuizRequest request = new UpdateQuizRequest();

        request(request);

        Mockito.verify(updateQuizService).updateQuiz(ArgumentMatchers.refEq(request));
    }

    private ResultActions request(UpdateQuizRequest updateQuizRequest) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put("/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectMapper().writeValueAsString(updateQuizRequest)));
    }
}