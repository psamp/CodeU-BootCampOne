package com.jits.routing;

import java.io.Serializable;

class DistributionCenter implements Serializable, Location {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9210538976197301513L;
	private String city;
	private String destination;
	
	DistributionCenter(String city, String destination) {
		this.setCity(city);
		this.setDestination(destination);
	}

	String getDestination() {
		return destination;
	}
	
	private void setDestination(String destination) {
		this.destination = destination.toUpperCase();
	}
	
	String getCity() {
		return this.city;
	}
	
	private void setCity(String state) {
		this.city = state;
	}

	@Override
	public String location() {
		String mssg = "DistCtr: " + this.getDestination() + " " + this.getCity() + " ";
		return mssg;
	}

}
