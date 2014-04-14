package nl.capgemini.corpapp.documents;

public class CarpoolLocation {

	private String address;
	private double[] position = new double[2];

	public CarpoolLocation() {
		super();
	}

	public CarpoolLocation(String address, double latitude, double longitude) {
		super();
		this.address = address;
		this.position[0] = latitude;
		this.position[1] = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

}
