package devgraft.member.api;


import devgraft.member.app.SignUpService;
import devgraft.member.app.SignUpService.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class MemberApi {
    private final SignUpService signUpService;
    // 회원가입
    /**
     * in - nickname
     * out - return jwt(access, refresh)
     * access - Header
     * refresh - Cookie
     */
    @ResponseStatus(code= HttpStatus.CREATED)
    @PostMapping("members")
    public void signUp(@RequestBody final SignUpRequest request, HttpServletResponse response) {
        final SignUpService.SignUpResponse signResult = signUpService.signUp(request);

        final Cookie cookie = new Cookie("REFRESH-TOKEN", signResult.getRefreshToken());
        response.setHeader("ACCESS-TOKEN", signResult.getAccessToken());
        response.addCookie(cookie);
    }

    /**
     * oauth -> naver, google (identityToken -> identityCode) identityCode -> auth jwt
     */

    // 로그인

}
