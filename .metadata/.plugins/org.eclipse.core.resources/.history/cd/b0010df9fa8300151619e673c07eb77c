package com.jits.library;

import com.jits.shipping.JitsException;

public class Validator {
	
	public static boolean checkNumericalString(String str, int allowedLength) throws JitsException {
		
		boolean check = false;
		
		if( (str != null ) & (str.length() == allowedLength) && (str.matches("[0-9]+")) ) {
			check = true;
		} else {
			throw new JitsException("String must be " + allowedLength + " digit(s). ", new IllegalArgumentException());
		}
		
		return check;
	}
	
	public static int getInt(String num) throws JitsException {
		
		try {
			Double dbl = Math.ceil(Double.parseDouble(num));
			return dbl.intValue();
			
		} catch(NumberFormatException e) {
			throw new JitsException("String must be digits only. ", new NumberFormatException());
		
		}
	}

}
