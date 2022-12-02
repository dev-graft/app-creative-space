package devgraft.module.member;

import devgraft.module.member.app.SignUpService.SignUpRequest;

public class SignUpRequestFixture {

    public static SignUpRequest.SignUpRequestBuilder anRequest() {
        return SignUpRequest.builder()
                .nickname("nickname");
    }
}
