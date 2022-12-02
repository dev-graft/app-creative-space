package devgraft.module.auth.app;

import devgraft.module.auth.common.OAuth;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class OAuthServiceFactory {
    private static final Map<OAuth.Kind, OAuthService> SERVICE_MAP = new EnumMap<>(OAuth.Kind.class);

    public OAuthServiceFactory(final OAuthService googleOAuthService) {
        SERVICE_MAP.put(OAuth.Kind.GOOGLE, googleOAuthService);
    }

    public OAuthService getOAuthService(final OAuth.Kind provider) {
        return SERVICE_MAP.get(provider);
    }
}
