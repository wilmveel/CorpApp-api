package nl.capgemini.corpapp.config;

import nl.capgemini.corpapp.handler.SparklrUserApprovalHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.oauth2.config.annotation.authentication.configurers.InMemoryClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@Order(1)
public class AuthorizationServerConfig extends OAuth2AuthorizationServerConfigurerAdapter {

	
	private static final String SPARKLR_RESOURCE_ID = "sparklr";
	
	private TokenStore tokenStore = new InMemoryTokenStore();

	@Autowired
	private OAuth2RequestFactory requestFactory;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Value("${tonr.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
	private String tonrRedirectUri;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// @formatter:off
		 	auth.apply(new InMemoryClientDetailsServiceConfigurer())
		 	.withClient("corpapp")
	 			.resourceIds(SPARKLR_RESOURCE_ID)
	 			.authorizedGrantTypes("authorization_code", "implicit")
	 			.authorities("ROLE_CLIENT")
	 			//.scopes("people", "carpool")
	 			.secret("secret")
	 			.redirectUris("http://localhost")
	 		.and()	
		 	.withClient("tonr")
		 			.resourceIds(SPARKLR_RESOURCE_ID)
		 			.authorizedGrantTypes("authorization_code", "implicit")
		 			.authorities("ROLE_CLIENT")
		 			.scopes("read", "write")
		 			.secret("secret")
		 		.and()
		 		.withClient("tonr-with-redirect")
		 			.resourceIds(SPARKLR_RESOURCE_ID)
		 			.authorizedGrantTypes("authorization_code", "implicit")
		 			.authorities("ROLE_CLIENT")
		 			.scopes("read", "write", "trust")
		 			.secret("secret")
		 			.redirectUris(tonrRedirectUri)
		 		.and()
	 		    .withClient("my-client-with-registered-redirect")
 			        .resourceIds(SPARKLR_RESOURCE_ID)
 			        .authorizedGrantTypes("authorization_code", "client_credentials")
 			        .authorities("ROLE_CLIENT")
 			        .scopes("read", "trust")
 			        .redirectUris("http://anywhere?key=value")
	 		    .and()
 		        .withClient("my-trusted-client")
			            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			            .scopes("read", "write", "trust")
			            .accessTokenValiditySeconds(60)
	 		    .and()
 		        .withClient("my-trusted-client-with-secret")
			            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			            .scopes("read", "write", "trust")
			            .secret("somesecret")
 		        .and()
		            .withClient("my-less-trusted-client")
		            .authorizedGrantTypes("authorization_code", "implicit")
		            .authorities("ROLE_CLIENT")
		            .scopes("read", "write", "trust")
 		        .and()
	            .withClient("my-less-trusted-autoapprove-client")
	                .authorizedGrantTypes("implicit")
	                .authorities("ROLE_CLIENT")
	                .scopes("read", "write", "trust")
	                .autoApprove(true);
		// @formatter:on
	}

	@Bean
	@Override
	@Lazy
	@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SparklrUserApprovalHandler userApprovalHandler() throws Exception {
		SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
		handler.setApprovalStore(approvalStore());
		handler.setRequestFactory(requestFactory);
		handler.setClientDetailsService(clientDetailsService);
		handler.setUseApprovalStore(true);
		return handler;
	}

	@Bean
	public ApprovalStore approvalStore() throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	@Override
	protected void configure(OAuth2AuthorizationServerConfigurer oauthServer) throws Exception {
		oauthServer.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler())
				.authenticationManager(authenticationManager).realm("sparklr2/client");
	}

}