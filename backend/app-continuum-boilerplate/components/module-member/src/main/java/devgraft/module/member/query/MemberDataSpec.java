package devgraft.module.member.query;

import org.springframework.data.jpa.domain.Specification;

public class MemberDataSpec {
    public static Specification<MemberData> idIdEquals(final String id) {
        return (root, query, cb) -> cb.equal(root.get(MemberData_.id), id);
    }

    public static Specification<MemberData> nicknameLike(final String keyword) {
        return (root, query, cb) -> cb.like(root.get(MemberData_.nickname), keyword + "%");
    }
}
