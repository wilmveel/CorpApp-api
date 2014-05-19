package nl.capgemini.corpapp.documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Training")
public class Training {

	private String name;

	private Date date;
	private String location;

	private String instructorName;
	private String instructorCorpKey;

	public Training() {
		// TODO Auto-generated constructor stub
	}

	public Training(String name, Date date, String location, String instructorName, String instructorCorpKey) {
		super();
		this.name = name;
		this.date = date;
		this.location = location;
		this.instructorName = instructorName;
		this.instructorCorpKey = instructorCorpKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getInstructorCorpKey() {
		return instructorCorpKey;
	}

	public void setInstructorCorpKey(String instructorCorpKey) {
		this.instructorCorpKey = instructorCorpKey;
	}

}
