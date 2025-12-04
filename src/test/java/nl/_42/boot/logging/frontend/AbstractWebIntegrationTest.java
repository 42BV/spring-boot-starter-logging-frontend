package nl._42.boot.logging.frontend;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import tools.jackson.databind.json.JsonMapper;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public abstract class AbstractWebIntegrationTest {

    @Autowired
    protected WebApplicationContext applicationContext;

    @Autowired
    protected JsonMapper objectMapper;

    protected MockMvc webClient;

    @BeforeEach
    public void setUpMockMvc() {
        webClient = webAppContextSetup(applicationContext)
            .defaultRequest(get("/").contentType(APPLICATION_JSON))
            .build();
    }

}
