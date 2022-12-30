package devgraft.user.member.infra;

import devgraft.credential.domain.CredentialsResolver;
import devgraft.user.member.domain.MemberCredentials;
import org.springframework.stereotype.Component;

@Component
public class MemberCredentialsResolver extends CredentialsResolver<MemberCredentials> {
    public MemberCredentialsResolver(MemberCredentialsProvider memberCredentialsProvider) {
        super(MemberCredentials.class, memberCredentialsProvider);
    }
}
