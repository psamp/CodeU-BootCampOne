package com.jits.routing;

class Destination implements Location {
	private String address;
	private String zipcode;
	

	Destination(String address, String zipcode) {
		this.setAddress(address);
		this.setZipcode(zipcode);
	}

	@Override
	public String location() {
		String mssg = "Dest: " + this.getAddress() + " " + this.getZipcode();
		return mssg;
	}

	String getAddress() {
		return address;
	}


	private void setAddress(String address) {
		this.address = address;
	}


	String getZipcode() {
		return zipcode;
	}


	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
