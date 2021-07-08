package address;

public class Address {
	private String street;
	private String city;
	private String stateAbbreviation;
	private String zip;
	
	// Constructors
	public Address() {}
	
	public Address(String street, String city, String stateAbbreviation, String zip) {
		this.street = street;
		this.city = city;
		this.stateAbbreviation = stateAbbreviation;
		this.zip = zip;
	}

	// Getters & Setters
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateAbbreviation() {
		return this.stateAbbreviation;
	}

	public void setState(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address: " + street + ", " + city + ", " + stateAbbreviation + " " + zip;
	}
	
}
