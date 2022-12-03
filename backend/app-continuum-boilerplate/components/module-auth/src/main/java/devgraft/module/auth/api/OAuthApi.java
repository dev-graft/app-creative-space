package devgraft.module.auth.api;

import devgraft.module.auth.app.OAuth;
import devgraft.module.auth.app.OAuthService;
import devgraft.module.auth.app.OAuthServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OAuthApi {
    private final OAuthServiceFactory oauthServiceFactory;

//    @GetMapping("/refresh")

    @GetMapping("/signIn")
    public void signIn(@RequestParam(name = "provider") OAuth.Kind provider, @RequestParam String code) {
        final OAuthService oauthService = oauthServiceFactory.getOAuthService(provider);
    }
}
