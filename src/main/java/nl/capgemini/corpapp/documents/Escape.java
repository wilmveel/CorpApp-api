package nl.capgemini.corpapp.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Escape {

	@Id
	private String id;
	
	private Date fromDate;
	private Date toDate;
	
	private String image;
	private String name;
	private String description;
	
	
	
	public Escape(Date fromDate, String image, String name, String description) {
		super();
		this.fromDate = fromDate;
		this.image = image;
		this.name = name;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	
}
