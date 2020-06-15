package nl._42.boot.logging.frontend;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FrontendLoggerControllerTest extends AbstractWebIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Logger frontEndLogger;
    private ListAppender<ILoggingEvent> frontEndLoggerAppender;

    @Before
    public void setUp() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        frontEndLogger = context.getLogger(LoggerFactory.getLogger(FrontendLogger.class).getName());

        frontEndLoggerAppender = new ListAppender<>();
        frontEndLoggerAppender.start();
        frontEndLogger.addAppender(frontEndLoggerAppender);

        frontEndLogger.setLevel(Level.ALL);
    }

    @Test
    public void log_error() throws Exception {
        FrontendError frontendError = new FrontendError();
        frontendError.setMessage("Big error in little China");
        frontendError.setUrl("https://www.42.nl");
        frontendError.setStack("Le big trace du stack");
        frontendError.setUserAgent("Chrome on mac os");

        webClient.perform(post("/log/error").content(objectMapper.writeValueAsString(frontendError)))
          .andExpect(status().isNoContent());

        assertEquals(1, frontEndLoggerAppender.list.size());

        ILoggingEvent loggedEvent = frontEndLoggerAppender.list.get(0);
        assertEquals(Level.WARN, loggedEvent.getLevel());

        String expectedMessage = "Big error in little China"
          + ", URL: https://www.42.nl"
          + ", UserAgent: Chrome on mac os"
          + ", Stack: Le big trace du stack";

        assertEquals(expectedMessage, loggedEvent.getMessage());
    }

    @Test
    public void log_error_fail() throws Exception {
        FrontendError frontendError = new FrontendError();
        frontendError.setMessage("Big error in little China");
        frontendError.setUrl("https://www.42.nl");
        frontendError.setStack("Le big trace du stack");
        frontendError.setUserAgent(StringUtils.repeat("a", 1025));

        webClient.perform(post("/log/error").content(objectMapper.writeValueAsString(frontendError)))
          .andExpect(status().is4xxClientError());

        assertEquals(0, frontEndLoggerAppender.list.size());
    }

}