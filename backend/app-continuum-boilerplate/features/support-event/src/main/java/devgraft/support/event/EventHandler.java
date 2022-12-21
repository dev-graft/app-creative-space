package devgraft.support.event;

public interface EventHandler<E extends Event> {
    void handle(final E event);
}
