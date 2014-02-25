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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Rob Winch
 * 
 */
@Configuration
@Order(3)
public class OAuth2ServerConfig extends WebSecurityConfigurerAdapter {

	private static final String SPARKLR_RESOURCE_ID = "sparklr";

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void configure(WebSecurity builder) throws Exception {
		IgnoredRequestConfigurer ignoring = builder.ignoring();
		ignoring.antMatchers("/oauth/uncache_approvals", "/oauth/cache_approvals");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
            .authorizeRequests()
                .expressionHandler(new OAuth2WebSecurityExpressionHandler())
                .antMatchers("/rest/**").access("#oauth2.clientHasRole('ROLE_CLIENT') or hasRole('ROLE_USER')")
                .and()
            .requestMatchers()
                .antMatchers("/rest/**")
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
            .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
            // CSRF protection is awkward for machine clients
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/**")).disable()
            .apply(new OAuth2ResourceServerConfigurer()).tokenStore(tokenStore)
                .resourceId(SPARKLR_RESOURCE_ID);
    	// @formatter:on
	}
	
}
