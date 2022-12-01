package devgraft.module.member.app;

import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import devgraft.support.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    public String signUp(final SignUpRequest request) {

        new Validation()
                .check(()-> null != request.getNickname(), ValidationError.of("nickname", "SignUpRequest.loginId must not be null."))

                .ifThrow();

//
//        Validation.validate()
//                .check(()-> null != request.getNickname(), ValidationError.of("nickname", "SignUpRequest.loginId must not be null."))
//                .check(()-> null != request.getProfileImage())
//                .check(()-> null != request.getStateMessage())
//                .ifThrow(ValidationException::new);
        // 입력 받은 값 검사
        // 아이디 생성
        // 저장
        // 아이디 반환
        return "";
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SignUpRequest {
        private String nickname;
        private String profileImage;
        private String stateMessage;
    }
}
