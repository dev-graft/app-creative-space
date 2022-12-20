package devgraft.quiz.api;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.quiz.app.AddQuizService;
import devgraft.quiz.config.QuizConstants;
import devgraft.quiz.service.AddQuizRequestFixture;
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

@DisplayName("퀴즈 생성 Api")
class AddQuizApiTest extends ObjectMapperTestSupport {
    private MockMvc mockMvc;
    private AddQuizService addQuizService;

    @BeforeEach
    void setUp() {
        addQuizService = Mockito.mock(AddQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new AddQuizApi(addQuizService)).build();
    }

    @DisplayName("요청이 성공했을 경우 HttpStatus.CREATED를 반환한다.")
    @Test
    void addQuiz_returnCreateStatus() throws Exception {
        request(AddQuizRequestFixture.anRequest().build())
                .andExpect(status().isCreated());
    }

    @DisplayName("요청문은 Service에 전달되어야한다.")
    @Test
    void addQuiz_passes_request_AddQuizService() throws Exception {
        final AddQuizRequest request = AddQuizRequestFixture.anRequest().build();

        request(request);

        Mockito.verify(addQuizService).addQuiz(refEq(request));
    }

    @DisplayName("서비스의 결과와 Api요청 결과는 동일하다.")
    @Test
    void addQuiz_returnValue() throws Exception {
        final AddQuizRequest request = AddQuizRequestFixture.anRequest().build();
        final Long givenReturn = 100L;
        BDDMockito.given(addQuizService.addQuiz(refEq(request))).willReturn(givenReturn);

        request(request)
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo(givenReturn.intValue())));
    }

    private ResultActions request(final AddQuizRequest request) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(QuizConstants.DOMAIN_NAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectMapper().writeValueAsString(request)));
    }
}