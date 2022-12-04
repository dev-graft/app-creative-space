package devgraft.support.event.member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SignUpEvent implements MemberEvent {
    private String memberId;

    @Override
    public String getCode() {
        return "SignUp";
    }

    public static SignUpEvent from(final String memberId) {
        return new SignUpEvent(memberId);
    }
}
