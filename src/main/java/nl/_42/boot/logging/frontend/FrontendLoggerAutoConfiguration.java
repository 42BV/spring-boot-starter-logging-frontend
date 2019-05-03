package nl._42.boot.logging.frontend;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "logging.frontend.enabled", havingValue = "true", matchIfMissing = true)
public class FrontendLoggerAutoConfiguration {

  @Bean
  public FrontendLogger frontendLogger() {
    return new FrontendLogger();
  }

}
