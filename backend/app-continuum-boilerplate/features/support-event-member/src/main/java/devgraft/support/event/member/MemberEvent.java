package devgraft.support.event.member;

import devgraft.support.event.Event;

public interface MemberEvent extends Event {
    String getMemberId();
}
