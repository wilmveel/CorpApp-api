package nl.capgemini.corpapp.documents;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "linkedin")
public class Linkedin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String corpKey;
	private String pictureUrl;

	private Collection<LinkedinSkill> skils;

	public String getCorpKey() {
		return corpKey;
	}

	public void setCorpKey(String corpKey) {
		this.corpKey = corpKey;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Collection<LinkedinSkill> getSkils() {
		return skils;
	}

	public void setSkils(Collection<LinkedinSkill> skils) {
		this.skils = skils;
	}

}
