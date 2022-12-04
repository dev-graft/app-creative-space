package devgraft.module.member.api;

import devgraft.module.member.app.SignUpService;
import devgraft.support.mapper.ObjectMapperTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SignUpApiTest extends ObjectMapperTestSupport {
    private MockMvc mockMvc;
    private SignUpService signUpService;

    @BeforeEach
    void setUp() {
        signUpService = Mockito.mock(SignUpService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new SignUpApi(signUpService)).build();
    }

    @DisplayName("회원가입 created 상태 반환")
    @Test
    void signUp_createdHttpStatus() throws Exception {
        SignUpService.SignUpRequest givenRequest = SignUpService.SignUpRequest.builder().build();

        requestSignUp(givenRequest)
                .andExpect(status().isCreated());
    }

    @DisplayName("회원가입 정보 서비스 전달")
    @Test
    void signUp_passesSignUpRequestToService() throws Exception {
        SignUpService.SignUpRequest givenRequest = SignUpService.SignUpRequest.builder().build();

        requestSignUp(givenRequest);

        Mockito.verify(signUpService).signUp(refEq(givenRequest));
    }

    @DisplayName("회원가입 후 생성되는 아이디 반환")
    @Test
    void signUp_returnValue() throws Exception {
        SignUpService.SignUpRequest givenRequest = SignUpService.SignUpRequest.builder().build();
        BDDMockito.given(signUpService.signUp(any())).willReturn("givenId");

        requestSignUp(givenRequest)
                .andExpect(jsonPath("$", equalTo("givenId")))
        ;
    }

    private ResultActions requestSignUp(final SignUpService.SignUpRequest givenRequest) throws Exception {
        return mockMvc.perform(post("/members/v1/sign-up")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getObjectMapper().writeValueAsString(givenRequest)));
    }
}