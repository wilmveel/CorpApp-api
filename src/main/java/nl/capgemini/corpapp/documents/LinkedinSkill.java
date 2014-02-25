package nl.capgemini.corpapp.documents;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linkedin")
public class LinkedinSkill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String corpKey;
	
	public LinkedinSkill() {
		// TODO Auto-generated constructor stub
	}

	public LinkedinSkill(String username, String password, String corpKey) {
		super();
		this.username = username;
		this.password = password;
		this.corpKey = corpKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorpKey() {
		return corpKey;
	}

	public void setCorpKey(String corpKey) {
		this.corpKey = corpKey;
	}

}
