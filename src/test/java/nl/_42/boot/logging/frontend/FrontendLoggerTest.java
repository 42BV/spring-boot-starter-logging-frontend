package nl._42.boot.logging.frontend;

import nl._42.boot.logging.frontend.limiter.Limiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class FrontendLoggerTest {

    private FrontendLogger logger;

    private Logger delegate;

    private Limiter limiter;

    private FrontendError error;

    @BeforeEach
    public void setUp() {
        delegate = Mockito.mock(Logger.class);
        limiter = Mockito.mock(Limiter.class);
        logger = new FrontendLogger(delegate, limiter);

        error = new FrontendError();
        error.setMessage("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\t Aenean commodo ligula eget dolor. Aenean massa.");
    }

    @Test
    public void log_shouldSkipControlCharacters() {
        Mockito.when(delegate.isWarnEnabled()).thenReturn(true);
        Mockito.when(limiter.obtain()).thenReturn(true);

        logger.error(error);

        Mockito.verify(delegate).warn(
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa." +
            ", URL: null" +
            ", UserAgent: null" +
            ", Stack: null"
        );
    }

    @Test
    public void log_shouldSkipWhenWarnDisabled() {
        Mockito.when(delegate.isWarnEnabled()).thenReturn(false);

        logger.error(error);

        Mockito.verifyNoMoreInteractions(limiter);
    }

    @Test
    public void log_shouldSkipWhenLimited() {
        Mockito.when(delegate.isWarnEnabled()).thenReturn(true);
        Mockito.when(limiter.obtain()).thenReturn(false);

        logger.error(error);
    }

}
