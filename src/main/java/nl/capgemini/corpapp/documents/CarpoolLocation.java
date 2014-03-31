package nl.capgemini.corpapp.documents;

public class CarpoolLocation {

	private String address;
	private double latitude;
	private double longitude;
	

	

	public CarpoolLocation() {
		super();
	}




	public CarpoolLocation(String address, double latitude, double longitude) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}




	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
}
