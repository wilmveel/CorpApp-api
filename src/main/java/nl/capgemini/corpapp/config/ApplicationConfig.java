package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("nl.capgemini.corpapp")
@Import({ MongoConfig.class, SecurityConfig.class, AuthorizationServerConfig.class, OAuth2ServerConfig.class })
public class ApplicationConfig {

	

}
