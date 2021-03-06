package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParcelTest {

	Warehouse warehouse;
	Parcel parcel;

	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcel = warehouse.newParcel("89687676564|GRD|12345|30313|5.09|5|4.45|7.86|&*^89786|XYZ|426 Marietta St.");
	}

	@Test
	public void testShipParcel() throws JitsException {

		parcel.shipParcel();
		String expectedOne = "Whse DistCtr: DC1 Raleigh ";
		String actualOne = parcel.getLocation();
		assertEquals(expectedOne, actualOne);

		parcel.shipParcel();
		String expectedTwo = "Whse DistCtr: DC1 Raleigh Dest: 426 Marietta St. 30313";
		String actualTwo = parcel.getLocation();
		assertEquals(expectedTwo, actualTwo);
	}

}
