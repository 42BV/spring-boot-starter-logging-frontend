package nl._42.boot.logging.frontend;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/log")
@AllArgsConstructor
public class FrontendLoggerController {
    
    private final FrontendLogger logger;

    @PostMapping("/error")
    @ResponseStatus(NO_CONTENT)
    public void error(@Valid @RequestBody FrontendError error) {
        logger.error(error);
    }

}
