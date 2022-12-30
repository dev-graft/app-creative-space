package devgraft.user.member.infra;

import devgraft.credential.domain.CredentialsProvider;
import devgraft.user.member.domain.MemberCredentials;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class MemberCredentialsProvider implements CredentialsProvider<MemberCredentials> {
    @Override
    public MemberCredentials generated(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        return null;
    }
}
