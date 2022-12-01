package devgraft.support.sentry;

import io.sentry.Sentry;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SentryMessenger {

    public static void send(final Throwable e) {
        try {
            Sentry.captureException(e);
        } finally {
            Sentry.clearBreadcrumbs();
        }
    }
}
