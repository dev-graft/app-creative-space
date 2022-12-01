package devgraft.support.event.member;
import lombok.Getter;

@Getter
public class SignUpEvent implements MemberEvent {
    private String memberId;
    private String eventCode;
}
