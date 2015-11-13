package com.jits.shipping.routing;

class RailYard extends Location {
	private String region;
	private String city;
	private String zip;
	private String state;
	
	
	RailYard(String region, String city, String zip, String state) {
		this.setRegion(region);
		this.setCity(city);
		this.setZip(zip);
		this.setState(state);
	}
	
	@Override
	public String location() {
		return "RY: " + this.getRegion() + " " + this.getCity() + ", " + this.getState();
	}
	
	@Override
	public String scanParcel(long id) {
		return this.getRegion() + "|" + id + "|" + super.date();
	}

	String getRegion() {
		return region;
	}
	
	private void setRegion(String reigon) {
		this.region = reigon;
	}
	
	String getCity() {
		return city;
	}
	
	private void setCity(String city) {
		this.city = city;
	}
	
	String getZip() {
		return zip;
	}
	
	private void setZip(String zip) {
		this.zip = zip;
	}
	
	String getState() {
		return state;
	}
	
	private void setState(String state) {
		this.state = state;
	}
	
}
