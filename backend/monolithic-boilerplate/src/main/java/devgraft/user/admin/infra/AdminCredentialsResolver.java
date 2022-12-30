package devgraft.user.admin.infra;

import devgraft.credential.domain.CredentialsResolver;
import devgraft.user.admin.domain.AdminCredentials;
import org.springframework.stereotype.Component;

@Component
public class AdminCredentialsResolver extends CredentialsResolver<AdminCredentials> {
    protected AdminCredentialsResolver(AdminCredentialsProvider adminCredentialsProvider) {
        super(AdminCredentials.class, adminCredentialsProvider);
    }
}
