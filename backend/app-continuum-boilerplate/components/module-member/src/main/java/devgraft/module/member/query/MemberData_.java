package devgraft.module.member.query;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MemberData.class)
public class MemberData_ {
    public static volatile SingularAttribute<MemberData, String> id;
    public static volatile SingularAttribute<MemberData, String> nickname;
}
