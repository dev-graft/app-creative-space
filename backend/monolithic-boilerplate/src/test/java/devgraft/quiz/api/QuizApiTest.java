package devgraft.quiz.api;

import devgraft.quiz.app.AddQuizRequest;
import devgraft.quiz.app.AddQuizService;
import devgraft.quiz.app.DeleteQuizService;
import devgraft.quiz.app.UpdateQuizRequest;
import devgraft.quiz.app.UpdateQuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("퀴즈 Api")
@TestMethodOrder(MethodOrderer.MethodName.class)
class QuizApiTest {
    private MockMvc mockMvc;
    private AddQuizService addQuizService;
    private UpdateQuizService updateQuizService;
    private DeleteQuizService deleteQuizService;

    @BeforeEach
    void setUp() {
        addQuizService = Mockito.mock(AddQuizService.class);
        updateQuizService = Mockito.mock(UpdateQuizService.class);
        deleteQuizService = Mockito.mock(DeleteQuizService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuizApi(addQuizService, updateQuizService, deleteQuizService)).build();
    }

    @DisplayName("[퀴즈 추가]요청이 성공했을 경우 HttpStatus.CREATED를 반환한다.")
    @Test
    void addQuiz_returnCreateStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(QuizConstants.DOMAIN_NAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @DisplayName("[퀴즈 추가]요청문은 Service에 전달되어야한다.")
    @Test
    void addQuiz_passes_request_AddQuizService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(QuizConstants.DOMAIN_NAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {  
                            "title": "title",
                            "desc": "desc",
                            "timer": 0,
                            "answer": 0,
                            "select1": "select1",
                            "select2": "select2",
                            "select3": "select3",
                            "select4": "select4",
                            "openAt": "2023-12-25",
                            "openTime": "10:00:00",
                            "endTime": "12:00:00"
                        }
                        """));

        ArgumentCaptor<AddQuizRequest> requestArgumentCaptor = ArgumentCaptor.forClass(AddQuizRequest.class);
        Mockito.verify(addQuizService).addQuiz(requestArgumentCaptor.capture());
    }

    @DisplayName("[퀴즈 추가]서비스의 결과와 Api요청 결과는 동일하다.")
    @Test
    void addQuiz_returnValue() throws Exception {
        final Long givenReturn = 100L;
        BDDMockito.given(addQuizService.addQuiz(any())).willReturn(givenReturn);

        mockMvc.perform(MockMvcRequestBuilders.post(QuizConstants.DOMAIN_NAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", equalTo(givenReturn.intValue())));
    }

    @DisplayName("[퀴즈 업데이트]요청이 성공했을 경우 HttpStatus.OK를 반환한다.")
    @Test
    void updateQuiz_returnOkStatus() throws Exception {
        mockMvc.perform(put(QuizConstants.DOMAIN_NAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("[퀴즈 업데이트]요청문은 Service에 전달되어야한다.")
    @Test
    void updateQuiz_passes_request_UpdateQuizService() throws Exception {
        mockMvc.perform(put(QuizConstants.DOMAIN_NAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {  
                            "quizId": 0,
                            "title": "title",
                            "desc": "desc",
                            "timer": 0,
                            "answer": 0,
                            "select1": "select1",
                            "select2": "select2",
                            "select3": "select3",
                            "select4": "select4",
                            "openAt": "2023-12-25",
                            "openTime": "10:00:00",
                            "endTime": "12:00:00"
                        }
                        """));

        ArgumentCaptor<UpdateQuizRequest> updateQuizRequestArgumentCaptor = ArgumentCaptor.forClass(UpdateQuizRequest.class);
        Mockito.verify(updateQuizService).updateQuiz(updateQuizRequestArgumentCaptor.capture());
    }

    @DisplayName("[퀴즈 삭제]요청이 성공했을 경우 HttpStatus.OK를 반환한다.")
    @Test
    void deleteQuiz_returnOkHttpStatus() throws Exception {
        mockMvc.perform(delete(QuizConstants.DOMAIN_NAME)
                .param("quizId", String.valueOf(0L)))
                .andExpect(status().isOk());
    }

    @DisplayName("[퀴즈 삭제]QuizId는 Service에 전달되어야한다.")
    @Test
    void deleteQuiz_passes_QuizId_DeleteQuizService() throws Exception {
        final Long givenQuizId = 5L;
        mockMvc.perform(delete(QuizConstants.DOMAIN_NAME)
                        .param("quizId", String.valueOf(givenQuizId)))
                .andExpect(status().isOk());

        Mockito.verify(deleteQuizService).delete(eq(givenQuizId));
    }
}