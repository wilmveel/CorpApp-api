package nl.capgemini.corpapp.documents;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linkedin")
public class Linkedin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String corpkey;
	private String accesToken;
	private String pictureUrl;

	private Collection<String> skils;
	
	public Linkedin() {
		
	}

	public Linkedin(String corpkey, String accesToken) {
		super();
		this.corpkey = corpkey;
		this.accesToken = accesToken;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorpkey() {
		return corpkey;
	}

	public void setCorpkey(String corpkey) {
		this.corpkey = corpkey;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public Collection<String> getSkils() {
		return skils;
	}

	public void setSkils(Collection<String> skils) {
		this.skils = skils;
	}

}
