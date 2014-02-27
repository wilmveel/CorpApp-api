package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("nl.capgemini.corpapp.jobs")
@Import({ MongoConfig.class})
public class TestApplicatoinConfig {

}
