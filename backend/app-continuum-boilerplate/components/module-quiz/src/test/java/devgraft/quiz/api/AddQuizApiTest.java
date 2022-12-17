package devgraft.quiz.api;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.quiz.app.AddQuizService;
import devgraft.support.mapper.ObjectMapperTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.refEq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddQuizApiTest extends ObjectMapperTestSupport {
    private MockMvc mockMvc;
    private AddQuizService addQuizService;

    @BeforeEach
    void setUp() {
        addQuizService = Mockito.mock(AddQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new AddQuizApi(addQuizService)).build();
    }

    @DisplayName("퀴즈 추가 요청 Create Status 반환")
    @Test
    void addQuiz_returnCreateStatus() throws Exception {
        request(new AddQuizRequest())
                .andExpect(status().isCreated());
    }

    @DisplayName("퀴즈 추가 request AddQuizService 전달")
    @Test
    void addQuiz_passes_request_AddQuizService() throws Exception {
        final AddQuizRequest request = new AddQuizRequest();

        request(request);

        Mockito.verify(addQuizService).addQuiz(refEq(request));
    }

    @DisplayName("퀴즈 추가 결과")
    @Test
    void addQuiz_returnValue() throws Exception {
        final AddQuizRequest request = new AddQuizRequest();
        final Long givenReturn = 100L;
        BDDMockito.given(addQuizService.addQuiz(refEq(request))).willReturn(givenReturn);

        request(request)
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo(givenReturn.intValue())));
    }

    private ResultActions request(final AddQuizRequest request) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectMapper().writeValueAsString(request)));
    }
}