package devgraft.client.member;

import lombok.Builder;
import lombok.Getter;

public interface MemberClient {
    SignUpResult signUp(final SignUpRequest request);

    @Builder
    @Getter
    class SignUpRequest {
        private final String nickname;
        private final String profileImage;
        private final String stateMessage;
    }

    @Builder
    @Getter
    class SignUpResult {
        private final String memberId;
    }
}
