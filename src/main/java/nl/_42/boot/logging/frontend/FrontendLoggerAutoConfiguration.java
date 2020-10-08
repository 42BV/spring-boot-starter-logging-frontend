package nl._42.boot.logging.frontend;

import lombok.extern.slf4j.Slf4j;
import nl._42.boot.logging.frontend.limiter.Always;
import nl._42.boot.logging.frontend.limiter.Limiter;
import nl._42.boot.logging.frontend.limiter.Timed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "logging.frontend.enabled", havingValue = "true", matchIfMissing = true)
public class FrontendLoggerAutoConfiguration {

  @Bean
  public FrontendLogger frontendLogger(Limiter limiter) {
    return new FrontendLogger(limiter);
  }

  @Bean
  @ConditionalOnMissingBean
  public Limiter frontendLimiter(@Value("${logging.frontend.limit:0}") int limit) {
    Limiter limiter = Always.INSTANCE;
    if (limit > 0) {
      log.info("Frontend logging is limited to one message per '{}' millisecond.", limit);
      limiter = new Timed(limit);
    }
    return limiter;
  }

  @Bean
  public FrontendLoggerController frontendLoggerController(FrontendLogger frontendLogger) {
    return new FrontendLoggerController(frontendLogger);
  }

}
