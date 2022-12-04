package devgraft.support.event;

import java.time.LocalDateTime;

public interface Event {
    String getTag();
    String getCode();

    default LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}
