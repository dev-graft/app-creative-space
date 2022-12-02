package devgraft.client.member;

import devgraft.module.member.app.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberClientImpl implements MemberClient {
    private final SignUpService signUpService;
    @Override
    public SignUpResult signUp(final SignUpRequest request) {
        SignUpService.SignUpRequest serviceRequest = SignUpService.SignUpRequest.builder()
                .nickname(request.getNickname())
                .build();

        final String memberId = signUpService.signUp(serviceRequest);

        return SignUpResult.builder()
                .memberId(memberId)
                .build();
    }
}
