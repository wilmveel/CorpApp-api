package nl.capgemini.corpapp.documents;

import java.util.Collection;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class Token {

	private Collection<OAuth2AccessToken> tokens;
	private String username;
	
	public Token() {
		// TODO Auto-generated constructor stub
	}
	
	public Collection<OAuth2AccessToken> getToken() {
		return tokens;
	}
	public void setToken(Collection<OAuth2AccessToken> tokens) {
		this.tokens = tokens;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
