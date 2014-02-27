package nl.capgemini.corpapp.documents;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class PeopleDoc {

	private String firstName;
	private String preFix;
	private String lastName;

	private String corpKey;
	
	public PeopleDoc() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPreFix() {
		return preFix;
	}

	public void setPreFix(String preFix) {
		this.preFix = preFix;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCorpKey() {
		return corpKey;
	}

	public void setCorpKey(String corpKey) {
		this.corpKey = corpKey;
	}

}
