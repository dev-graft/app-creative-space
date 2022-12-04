package devgraft.support.event.member;

import devgraft.support.event.Event;

public interface MemberEvent extends Event {
    @Override
    default String getTag() {
        return "MEMBER";
    }

    String getMemberId();
}
