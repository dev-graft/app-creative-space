package devgraft.user.member.domain;

import devgraft.credential.domain.Credentials;

public class MemberCredentials extends Credentials {

    public MemberCredentials(final String id, final String secret) {
        super("MEMBER", id, secret);
    }
}
