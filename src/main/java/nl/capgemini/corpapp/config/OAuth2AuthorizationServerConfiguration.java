package nl.capgemini.corpapp.config;

import nl.capgemini.corpapp.handler.SparklrUserApprovalHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter  {

	private static final String SPARKLR_RESOURCE_ID = "CorpApi";

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
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// @formatter:off
		clients.inMemory()
				.withClient("corpapp")
				.resourceIds(SPARKLR_RESOURCE_ID)
				.authorizedGrantTypes("authorization_code", "implicit")
				.authorities("ROLE_CLIENT")
				.secret("secret");
		// @formatter:on
	}

	@Bean
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
	public void configure(OAuth2AuthorizationServerConfigurer oauthServer) throws Exception {
		oauthServer.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler()).authenticationManager(authenticationManager).realm("sparklr2/client");
	}

}