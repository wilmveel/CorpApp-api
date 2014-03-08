package nl.capgemini.corpapp.rest;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.capgemini.corpapp.documents.PeopleDoc;
import nl.capgemini.corpapp.documents.Token;
import nl.capgemini.corpapp.documents.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
@Path("/token")
public class TokenController {

	@Autowired
	private TokenStore tokenStore;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in username
		System.out.println("Name: " + name);

		Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByUserName(name);
		System.out.println("tokenStore: " + tokenStore);
		
		for (OAuth2AccessToken token : tokens) {
			System.out.println("Token: " + token);
		}
		
		for (OAuth2AccessToken token : tokenStore.findTokensByClientId("corpapp")) {
			System.out.println("Token: " + token);
		}

		Token token = new Token();
		token.setUsername(auth.getName());
		token.setToken(tokens);

		return token;

	}

}
