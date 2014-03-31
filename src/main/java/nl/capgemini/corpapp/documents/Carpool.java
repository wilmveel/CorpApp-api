package nl.capgemini.corpapp.documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carpool")
public class Carpool {
	
	private String corpkey;

	private CarpoolLocation from;
	private CarpoolLocation to;
	private Date date;

	public Carpool() {
		// TODO Auto-generated constructor stub
	}

	public Carpool(CarpoolLocation from, CarpoolLocation to, String corpkey) {
		super();
		this.from = from;
		this.to = to;
		this.corpkey = corpkey;
		this.date = new Date();
	}

	public CarpoolLocation getFrom() {
		return from;
	}

	public void setFrom(CarpoolLocation from) {
		this.from = from;
	}

	public CarpoolLocation getTo() {
		return to;
	}

	public void setTo(CarpoolLocation to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCorpkey() {
		return corpkey;
	}

	public void setCorpkey(String corpkey) {
		this.corpkey = corpkey;
	}

}
