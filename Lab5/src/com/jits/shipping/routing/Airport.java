package com.jits.shipping.routing;

class Airport extends Location {

	private String code;
	private String name;
	private String city;
	private double lat; // decimal degrees
	private double lng; // decimal degrees

	Airport() {
	}

	Airport(String code, String name, String city, int latDeg, int latMin, int latSec, int lngDeg, int lngMin,
			int lngSec) {
		this.setCode(code);
		this.setName(name);
		this.setCity(city);
		this.setLat(latDeg, latMin, latSec);
		this.setLng(lngDeg, lngMin, lngSec);
	}
	
	@Override
	public String location() {
		return "Arprt: " + this.getCode() + " " + this.getName() + " ";
	}
	
	@Override
	public String scanParcel(long id) {
		return this.getCode() + "|" + id + "|" + super.date();
	}

	String getCode() {
		return code;
	}

	void setCode(String code) {
		this.code = code;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getCity() {
		return city;
	}

	void setCity(String city) {
		this.city = city;
	}

	double getLat() {
		return lat;
	}

	void setLat(int latDeg, int latMin, int latSec) {
		this.lat = latDeg + latMin / 60.0 + latSec / 3600.0;
	}

	double getLng() {
		return lng;
	}

	void setLng(int lngDeg, int lngMin, int lngSec) {
		this.lng = lngDeg + lngMin / 60.0 + lngSec / 3600.0;
	}
	
}