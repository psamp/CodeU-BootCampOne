package com.codeu.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UseLogger {
	
	private long oneMil;
	private static final Logger logger = LogManager.getLogger(UseLogger.class.getSimpleName());
	
	public UseLogger(long oneMil) {
		
		if(oneMil == 1_000_000) {
			this.oneMil = oneMil;
		} else {
			UseLogger.logger.error("You can only give me a millon dollars or more.");
		}
		
	}
	
	public static void main(String[] args) {
		
		UseLogger md = new UseLogger(1_0000);
		
	}
	
}