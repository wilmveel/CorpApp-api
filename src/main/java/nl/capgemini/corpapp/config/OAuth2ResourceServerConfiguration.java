/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.capgemini.corpapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @author Rob Winch
 * 
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String SPARKLR_RESOURCE_ID = "CorpApi";
	
	@Override
	public void configure(OAuth2ResourceServerConfigurer resources) {
		resources.resourceId(SPARKLR_RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.expressionHandler(new OAuth2WebSecurityExpressionHandler())
				.antMatchers("/rest/**").access("#oauth2.clientHasRole('ROLE_CLIENT') or hasRole('ROLE_USER')")
			.and()
				.requestMatchers().antMatchers("/rest/**", "/oauth/users/**", "/oauth/clients/**");
		// @formatter:on
	}

	
}
