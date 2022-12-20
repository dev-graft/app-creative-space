package devgraft.quiz.api;

import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import devgraft.quiz.service.UpdateQuizRequestFixture;
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

@DisplayName("퀴즈 업데이트 Api")
class UpdateQuizApiTest extends ObjectMapperTestSupport {
    private MockMvc mockMvc;
    private UpdateQuizService updateQuizService;
    @BeforeEach
    void setUp() {
        updateQuizService = Mockito.mock(UpdateQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UpdateQuizApi(updateQuizService)).build();
    }

    @DisplayName("요청이 성공했을 경우 HttpStatus.OK를 반환한다.")
    @Test
    void updateQuiz_returnOkStatus() throws Exception {
        request(UpdateQuizRequestFixture.anRequest().build())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
    @DisplayName("요청문은 Service에 전달되어야한다.")
    @Test
    void updateQuiz_passes_request_UpdateQuizService() throws Exception {
        final UpdateQuizRequest request = UpdateQuizRequestFixture.anRequest().build();

        request(request);

        Mockito.verify(updateQuizService).updateQuiz(ArgumentMatchers.refEq(request));
    }

    private ResultActions request(UpdateQuizRequest updateQuizRequest) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(QuizConstants.DOMAIN_NAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectMapper().writeValueAsString(updateQuizRequest)));
    }
}