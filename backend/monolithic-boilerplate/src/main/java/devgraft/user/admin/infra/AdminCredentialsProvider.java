package devgraft.user.admin.infra;

import devgraft.credential.domain.CredentialsProvider;
import devgraft.user.admin.domain.AdminCredentials;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AdminCredentialsProvider implements CredentialsProvider<AdminCredentials> {
    @Override
    public AdminCredentials generated(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        return null;
    }
}
