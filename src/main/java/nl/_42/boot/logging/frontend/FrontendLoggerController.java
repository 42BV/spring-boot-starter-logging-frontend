package nl._42.boot.logging.frontend;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/log")
@AllArgsConstructor
public class FrontendLoggerController {
    
    private final FrontendLogger logger;

    @PostMapping("/error")
    public void error(@Valid @RequestBody FrontendError error) {
        logger.error(error);
    }

}
