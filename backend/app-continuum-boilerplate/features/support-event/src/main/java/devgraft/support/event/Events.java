package devgraft.support.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Events {
    private final ApplicationEventPublisher publisher;

    public Events(final ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }

    public <T extends Event> void raise(T event) {
        publisher.publishEvent(event);
    }
}