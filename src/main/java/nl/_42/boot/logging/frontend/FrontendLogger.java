package nl._42.boot.logging.frontend;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Slf4j
public class FrontendLogger {

    public void error(FrontendError error) {
        log.error(
          format(
            "%s\nURL: %s\nuserAgent: %s\nStack: %s",
            error.getMessage(),
            error.getUrl(),
            error.getUserAgent(),
            error.getStack()
          )
        );
    }

}