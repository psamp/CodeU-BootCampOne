package com.jits.parcel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jits.parcel.JitsException;
import com.jits.parcel.Parcel;
import com.jits.parcel.Warehouse;

public class ParcelTest {

	Warehouse warehouse;
	Parcel parcel;

	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcel = warehouse.newParcel("89687676564|GRD|30326|30313|5.09|5|4.45|7.86|&*^89786|XYZ|426 Marietta St.");
	}

	@Test
	public void testShipParcel() throws JitsException {
		parcel.shipParcel();
		parcel.shipParcel();
		
		String expectedOne = "Whse DistCtr: DC1 Raleigh ";
		String actualOne = parcel.getRouteHistory();
		assertEquals(expectedOne, actualOne);
		
		
		parcel.shipParcel();
		
		String expectedTwo = "Whse DistCtr: DC1 Raleigh Dest: 426 Marietta St. 30313";
		String actualTwo = parcel.getRouteHistory();
		assertEquals(expectedTwo, actualTwo);
	}
	
	@Test
	public void testTrackingStrings() throws JitsException {
		parcel.shipParcel();
		boolean expectedOne = true;
		boolean actualOne = parcel.getTrackingBarcodes().get(0).contains("Whse");
		assertEquals(expectedOne, actualOne);
		
		parcel.shipParcel();
		boolean expectedTwo = true;
		boolean actualTwo = parcel.getTrackingBarcodes().get(1).contains("DC1");
		assertEquals(expectedTwo, actualTwo);
		
		parcel.shipParcel();
		boolean expectedThree = true;
		boolean actualThree = parcel.getTrackingBarcodes().get(2).contains("1474");
		assertEquals(expectedThree, actualThree);
		
	}

}
