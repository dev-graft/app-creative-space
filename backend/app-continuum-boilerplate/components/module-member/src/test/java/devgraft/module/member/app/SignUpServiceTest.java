package devgraft.module.member.app;

import devgraft.module.member.SignUpRequestFixture;
import devgraft.module.member.domain.Member;
import devgraft.module.member.domain.MemberIdGenerateAction;
import devgraft.module.member.domain.MemberRepository;
import devgraft.support.event.Events;
import devgraft.support.event.member.SignUpEvent;
import devgraft.support.exception.ValidationAsserts;
import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

class SignUpServiceTest {
    private SignUpService signUpService;
    private MemberIdGenerateAction memberIdGenerateAction;
    private MemberRepository memberRepository;
    private Events events;

    @BeforeEach
    void setUp() {
        memberIdGenerateAction = Mockito.mock(MemberIdGenerateAction.class);
        memberRepository = Mockito.mock(MemberRepository.class);
        events = Mockito.mock(Events.class);
        signUpService = new SignUpService(memberIdGenerateAction, memberRepository, events);
    }

    @DisplayName("요청문이 잘못되었을 경우 예외처리")
    @Test
    void signUp_hasCall_RequestValidationFails() {
        final SignUpService.SignUpRequest givenRequest = SignUpService.SignUpRequest.builder().build();
        final List<ValidationError> errors = assertThrows(ValidationException.class, () -> signUpService.signUp(givenRequest))
                .getErrors();

        ValidationAsserts.assertHasCall(errors, "nickname", "SignUpRequest.nickname must not be null.");
    }

    @DisplayName("[회원 아이디 생성] 액션을 호출하는지 검사")
    @Test
    void signUp_wasCall_memberIdGenerateAction() {
        final SignUpService.SignUpRequest givenRequest = SignUpRequestFixture.anRequest().build();

        signUpService.signUp(givenRequest);

        verify(memberIdGenerateAction, Mockito.times(1)).generate();
    }

    @DisplayName("회원 저장")
    @Test
    void signUp_passMemberToRepository() {
        final String givenMemberId = "givenMemberId";
        final SignUpService.SignUpRequest givenRequest = SignUpRequestFixture.anRequest().build();
        BDDMockito.given(memberIdGenerateAction.generate()).willReturn(givenMemberId);

        final String result = signUpService.signUp(givenRequest);

        final Member compare = Member.builder()
                .id(givenMemberId)
                .nickname(givenRequest.getNickname())
                .build();
        Mockito.verify(memberRepository).save(refEq(compare));
    }

    @DisplayName("회원가입 아이디 반환 검사")
    @Test
    void signUp_returnValue() {
        final String givenMemberId = "givenMemberId";
        final SignUpService.SignUpRequest givenRequest = SignUpRequestFixture.anRequest().build();
        BDDMockito.given(memberIdGenerateAction.generate()).willReturn(givenMemberId);

        final String result = signUpService.signUp(givenRequest);

        Assertions.assertThat(result).isEqualTo(givenMemberId);
    }

    @DisplayName("회원 추가 이벤트 발행")
    @Test
    void signUp_publish_signUpEvent() {
        final String givenMemberId = "givenMemberId";
        final SignUpService.SignUpRequest givenRequest = SignUpRequestFixture.anRequest().build();
        BDDMockito.given(memberIdGenerateAction.generate()).willReturn(givenMemberId);

        signUpService.signUp(givenRequest);

        Mockito.verify(events).raise(refEq(SignUpEvent.from(givenMemberId)));
    }
}