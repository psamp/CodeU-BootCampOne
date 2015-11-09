package com.jits.shipping;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Location {
	
	public abstract String location();
	
	
	protected String date() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
		Date date = new Date();
		
		return sdf.format(date);
	}
	
	public String scanParcel(long id) {
		return this.location().trim() + "|" + id + "|" + this.date();
	}

}