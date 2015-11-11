package com.jits.shipping.routing;

import java.util.Date;

public abstract class Location {
	
	public abstract String location();
	
	public String date() {
		return new Date().toString();
	}
	
	public String scanParcel(long id) {
		return this.location().trim() + "|" + id + "|" + this.date();
	}

}
