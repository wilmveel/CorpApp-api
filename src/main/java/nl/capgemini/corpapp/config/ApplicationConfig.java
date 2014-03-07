package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("nl.capgemini.corpapp")
@Import({ MongoConfig.class, SecurityConfiguration.class, OAuth2AuthorizationServerConfiguration.class, OAuth2ResourceServerConfiguration.class, MethodSecurityConfig.class, SchedulConfig.class })
public class ApplicationConfig {

}
