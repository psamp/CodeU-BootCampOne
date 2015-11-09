package com.jits.library;

import java.util.Comparator;

import com.jits.parcel.Parcel;

public class IdComparator implements Comparator<Parcel> {

	@Override
	public int compare(Parcel first, Parcel second){
		return Long.compare(first.getId(), second.getId());
	}

}
