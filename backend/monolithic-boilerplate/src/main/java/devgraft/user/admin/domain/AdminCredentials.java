package devgraft.user.admin.domain;

import devgraft.credential.domain.Credentials;
import lombok.Getter;

@Getter
public class AdminCredentials extends Credentials {
    public AdminCredentials(final String grant, final String id, final String secret) {
        super(grant, id, secret);
    }
}
