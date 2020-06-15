package nl._42.boot.logging.frontend;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import static java.lang.String.format;

@AllArgsConstructor
public class FrontendLogger {

    private final Logger log;

    public FrontendLogger() {
        this(LoggerFactory.getLogger(FrontendLogger.class));
    }

    public void error(FrontendError error) {
        if (log.isWarnEnabled()) {
            String message = format(
                "%s, URL: %s, UserAgent: %s, Stack: %s",
                error.getMessage(),
                error.getUrl(),
                error.getUserAgent(),
                error.getStack()
            );

            String formatted = replaceControlCharacters(message);
            log.warn(formatted);
        }
    }

    /**
     * Replaces control characters in the given text with a space. The given text may be null or empty.
     * For a more detailed description of control characters, see: {@link Character#isISOControl(char)}
     *
     *  @see Character#isISOControl(char)
     * @param text The text to clean up
     * @return The text without control characters or null or empty.
     */
    private String replaceControlCharacters(String text) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }

        StringBuilder builder = new StringBuilder(text.length());
        for (int index = 0; index < text.length(); index++) {
            char character = text.charAt(index);
            if (!Character.isISOControl(character)) {
                builder.append(character);
            }
        }
        return builder.toString();
    }

}