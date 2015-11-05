package com.jits.shipping;

public class Warehouse {
	
	Parcel newParcel(String barcode) throws JitsException {
		String[] values = barcode.split("\\|");
		
		Parcel parcel = new Parcel();
		
		parcel.setId(values[0]);
		parcel.setShippingType(values[1]);
		parcel.setFromZip(values[2]);
		parcel.setToZip(values[3]);
		parcel.setWeight(values[4]);
		parcel.setHeight(values[5]);
		parcel.setWidth(values[6]);
		parcel.setDepth(values[7]);
		parcel.setOther(values[8]);
		parcel.setHazards(values[9]);
		
		return parcel;
	}

}