package devgraft.member.app;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    public SignUpResponse signUp(final SignUpRequest request) {
        return null;
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class SignUpRequest {
        private String nickname;
    }

    @AllArgsConstructor
    @Getter
    public static class SignUpResponse {
        private String accessToken;
        private String refreshToken;

        public void test() {
        }
    }

}
