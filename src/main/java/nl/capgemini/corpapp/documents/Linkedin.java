package nl.capgemini.corpapp.documents;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linkedin")
public class Linkedin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String corpkey;
	private String accesToken;

	private String lastName;
	private String firstName;
	private String headline;
	private String pictureUrl;

	private Collection<String> skills;

	private Date sync;

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

	public Collection<String> getSkills() {
		return skills;
	}

	public void setSkills(Collection<String> skills) {
		this.skills = skills;
	}

	public Date getSync() {
		return sync;
	}

	public void setSync(Date sync) {
		this.sync = sync;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

}
