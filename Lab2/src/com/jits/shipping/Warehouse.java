package com.jits.shipping;

import java.util.ArrayList;
import java.util.Collections;

import com.jits.library.IdComparator;
import com.jits.library.ShippingTypeComparator;
import com.jits.report.Report;

public class Warehouse {
	private ArrayList<Parcel> inventory;
	
	public Warehouse() {
		inventory = new ArrayList<Parcel>();
	}
	
	private void sortParcelArrayByIdThenShippingType() {
		Collections.sort(inventory, new IdComparator());
		Collections.sort(inventory, new ShippingTypeComparator());
	}
	
	private void updateInventory(Parcel parcel) {
		inventory.add(parcel);
		this.sortParcelArrayByIdThenShippingType();
	}
	
	public Parcel newParcel(String barcode) throws JitsException {
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
		
		this.updateInventory(parcel);
		
		return parcel;
	}

	public ArrayList<Parcel> getInventory() {
		return this.inventory;
	}
	
	public String getReport() {
		Report report = new Report();
		return report.generateParcelReport(this.getInventory());
	}

}
