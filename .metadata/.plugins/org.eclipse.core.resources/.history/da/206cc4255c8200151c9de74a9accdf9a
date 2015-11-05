package com.codeu.logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MillionDollars {
	private long oneMil;
	private static final Logger lgr = LogManager.getLogger(MillionDollars.class.getSimpleName());
	
	public MillionDollars(long oneMil) {
		if(oneMil == 1_000_000) {
			this.oneMil = oneMil;
		} else {
			this.lgr.error("You can only give me a millon dollars or more.");
		}
	}
	
	

}
