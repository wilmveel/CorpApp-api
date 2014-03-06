package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@ComponentScan({ "nl.capgemini.corpapp.service", "nl.capgemini.corpapp.jobs", "nl.capgemini.corpapp.rest" })
@Import({ MongoConfig.class })
public class TestApplicatoinConfig {

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
}
