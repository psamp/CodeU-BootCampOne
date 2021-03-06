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
	public void testRouteHistory() throws JitsException {
		String expectedOne = "Whse ";
		String actualOne = parcel.getLocation().location();
		assertEquals(expectedOne, actualOne);
		
		parcel.shipParcel();
		String expectedTwo = "DistCtr: DC1 Raleigh ";
		String actualTwo = parcel.getLocation().location();
		assertEquals(expectedTwo, actualTwo);
		
		parcel.shipParcel();
		String expectedThree = "Dest: 426 Marietta St. 30313";
		String actualThree = parcel.getLocation().location();
		assertEquals(expectedThree, actualThree);
	}
	
	@Test
	public void testTrackingStrings() throws JitsException {
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
