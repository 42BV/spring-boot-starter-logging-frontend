package nl._42.boot.logging.frontend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class FrontendLoggerTest {

    private FrontendLogger logger;

    @Mock
    private Logger delegate;

    @Before
    public void setUp() {
        logger = new FrontendLogger(delegate);
    }

    @Test
    public void log_shouldSkipControlCharacters() {
        Mockito.when(delegate.isErrorEnabled()).thenReturn(true);

        FrontendError error = new FrontendError();
        error.setMessage("Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\t Aenean commodo ligula eget dolor. Aenean massa.");

        logger.error(error);

        Mockito.verify(delegate).error(
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa." +
            ", URL: null" +
            ", UserAgent: null" +
            ", Stack: null"
        );
    }

}
