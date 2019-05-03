package nl._42.boot.logging.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractWebIntegrationTest {

    @Autowired
    protected WebApplicationContext applicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc webClient;

    @Before
    public void setUpMockMvc() {
        webClient = webAppContextSetup(applicationContext)
            .defaultRequest(get("/").contentType(APPLICATION_JSON))
            .build();
    }

}
