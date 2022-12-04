package devgraft.module.member.api;

import devgraft.module.member.app.SignUpService;
import devgraft.module.member.app.SignUpService.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignUpApi {
private final SignUpService signUpService;
    /* 회원가입 */

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("members/v1/sign-up")
    public String signUp(@RequestBody SignUpRequest request) {
        return signUpService.signUp(request);
    }
}
