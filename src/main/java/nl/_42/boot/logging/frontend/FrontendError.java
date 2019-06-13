package nl._42.boot.logging.frontend;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class FrontendError {

    /**
     * The URL on which the error occurred.
     */
    @Size(max = 256)
    private String url;

    /**
     * The message of the error.
     */
    @Size(max = 256)
    private String message;

    /**
     * The browsers navigator.userAgent string, useful
     * to detect which browser on which OS triggered
     * the error.
     */
    @Size(max = 512)
    private String userAgent;

    /**
     * The JavaScript Stackstrace of the error differs per browser
     */
    @Size(max = 4096)
    private String stack;

}