package com.jits.library;

import java.util.Comparator;

import com.jits.shipping.Parcel;

public class IdComparator implements Comparator<Parcel> {

	@Override
	public int compare(Parcel first, Parcel second){
		
		int rtn = -99;
		
		if(first.getId() == second.getId()) {
			rtn = 0;
			
		} else if(first.getId() > second.getId()){
			rtn = 1;
			
		} else {
			rtn = -1;
		}
		
		return rtn;
	}

}
