package com.jits.shipping.routing;

public class Destination extends Location {
	private String address;
	private String zipcode;
	

	public Destination(String address, String zipcode) {
		this.setAddress(address);
		this.setZipcode(zipcode);
	}
	
	private int encodeDestination() {
		String concatDestinationInfo = this.getAddress() + this.getZipcode();
		String removeAllWhiteSpace = concatDestinationInfo.replaceAll("\\s+", "");
		char[] array = removeAllWhiteSpace.trim().toCharArray();
		
		int sum = 0;
		
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		
		return sum;
	}
	
	@Override
	public String scanParcel(long id) {
		return this.encodeDestination() + "|" + id + "|" + super.date();
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
