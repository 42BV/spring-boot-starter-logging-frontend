package nl._42.boot.logging.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FrontendLoggerControllerTest extends AbstractWebIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void log_error() throws Exception {
        FrontendError error = new FrontendError();
        error.setMessage("Something went wrong");
        error.setStack("abc");
        error.setUrl("http://localhost");
        error.setUserAgent("Chrome");

        webClient.perform(post("/log/error").content(objectMapper.writeValueAsString(error)))
          .andExpect(status().isOk());
    }

}
