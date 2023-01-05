package devgraft.member.api;

import devgraft.member.app.SignUpService;
import devgraft.member.app.SignUpService.SignUpRequest;
import devgraft.member.app.SignUpService.SignUpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

class MemberApiTest {
    private MockMvc mockMvc;
    private SignUpService signUpService;

    @BeforeEach
    void setUp() {
        signUpService = Mockito.mock(SignUpService.class);
        MemberApi memberApi = new MemberApi(signUpService);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();

        BDDMockito.given(signUpService.signUp(any())).willReturn(new SignUpResponse("", ""));
    }

    // CREATED
    @DisplayName("회원가입 요청이 성공했을 경우 HttpStatus.CREATED를 반환한다.")
    @Test
    void signUp_expectCreatedStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // 내가 전달한 값이(nickname)이 서비스에 넘어가는지
    @DisplayName("전달한 값이(nickname)이 서비스에 넘어가야한다.")
    @Test
    void signUp_passesRequestToService() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nickname": "givenNickname"
                        }
                        """));

        ArgumentCaptor<SignUpRequest> requestArgumentCaptor = ArgumentCaptor.forClass(SignUpRequest.class);
        Mockito.verify(signUpService, Mockito.times(1)).signUp(requestArgumentCaptor.capture());
        Assertions.assertThat(requestArgumentCaptor.getValue().getNickname()).isEqualTo("givenNickname");
    }

    @DisplayName("헤더랑 쿠키에 값을 서비스에서 반환된 값을 담아야 한다.")
    @Test
    void signUp_returnAuthInfoToHeaderAndCookie() throws Exception {
        // given
        final SignUpResponse givenSignUpResponse = new SignUpResponse("ACC", "REF");
        BDDMockito.given(signUpService.signUp(any())).willReturn(givenSignUpResponse);
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.header().string("ACCESS-TOKEN", givenSignUpResponse.getAccessToken()))
                .andExpect(MockMvcResultMatchers.cookie().value("REFRESH-TOKEN", givenSignUpResponse.getRefreshToken()));
    }


    /** 헤더와 쿠키에 특정 값이 있는지 검사하는 방법!
     * .andExpect(MockMvcResultMatchers.header().string("ACCESS-TOKEN", "ACCESS"))
     * .andExpect(MockMvcResultMatchers.cookie().value("REFRESH-TOKEN", "REFRESH"))
     */

    /** 서비스에 전달한 값을 가져와서 비교하는 방법
     * ArgumentCaptor<SignUpRequest> requestArgumentCaptor = ArgumentCaptor.forClass(SignUpRequest.class);
     * Mockito.verify(signUpUseCase).signUp(requestArgumentCaptor.capture());
     * Assertions.assertThat(requestArgumentCaptor.getValue().getNickname()).isEqualTo("givenNickname");
     */
}