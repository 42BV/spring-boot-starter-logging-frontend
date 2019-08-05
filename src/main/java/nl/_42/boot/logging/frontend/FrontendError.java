package nl._42.boot.logging.frontend;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrontendError {

    /**
     * The URL on which the error occurred.
     */
    @Size(max = 2_000)
    private String url;

    /**
     * The message of the error.
     */
    @Size(max = 2_000)
    private String message;

    /**
     * The browsers navigator.userAgent string, useful
     * to detect which browser on which OS triggered
     * the error.
     */
    @Size(max = 1024)
    private String userAgent;

    /**
     * The JavaScript Stackstrace of the error differs per browser
     */
    @Size(max = 10_000)
    private String stack;

}