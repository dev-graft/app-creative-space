package devgraft.module.member.app;

import devgraft.module.member.domain.Member;
import devgraft.module.member.domain.MemberIdGenerateAction;
import devgraft.module.member.domain.MemberRepository;
import devgraft.support.event.Events;
import devgraft.support.event.member.SignUpEvent;
import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class SignUpService {
    private final MemberIdGenerateAction memberIdGenerateAction;
    private final MemberRepository memberRepository;
    private final Events events;

    public String signUp(final SignUpRequest request) {
        /* 요청문 검사 */
        Validation.collection()
                .ifFalse(()-> StringUtils.hasText(request.getNickname()), ValidationError.of("nickname", "SignUpRequest.nickname must not be null."))
            .ifThrow();

        final String memberId = memberIdGenerateAction.generate();
        final Member member = Member.builder()
                .id(memberId)
                .nickname(request.getNickname())
                .build();

        memberRepository.save(member);

        events.raise(SignUpEvent.from(memberId));

        return memberId;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class SignUpRequest {
        private String nickname;
    }
}
