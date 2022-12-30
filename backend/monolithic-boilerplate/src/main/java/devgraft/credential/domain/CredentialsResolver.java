package devgraft.credential.domain;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public abstract class CredentialsResolver<T extends Credentials> implements HandlerMethodArgumentResolver {
    private final Class<T> credentialsClass;
    private final CredentialsProvider<T> credentialsProvider;

    protected CredentialsResolver(final Class<T> credentialsClass, final CredentialsProvider<T> credentialsProvider) {
        this.credentialsClass = credentialsClass;
        this.credentialsProvider = credentialsProvider;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return credentialsClass.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        return credentialsProvider.generated(parameter, mavContainer, webRequest, binderFactory);
    }
}
