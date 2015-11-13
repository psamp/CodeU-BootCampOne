package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class ParcelTest {

	Warehouse warehouse;
	Parcel parcelGRD;
	Parcel parcelAIR;
	Parcel parcelRAL;

	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcelGRD = warehouse.newParcel("89687676564|GRD|30326|30313|5.09|5|4.45|7.86|&*^89786|XYZ|426 Marietta St.");
		parcelAIR = warehouse.newParcel("97978675325|AIR|28364|43333|8.09|10|6.45|12.86|&*^89786|XYZ|8500 Barnes Ln.");
		parcelRAL = warehouse.newParcel("97978675325|RAL|81631|06785|8.09|10|6.45|12.86|&*^89786|XYZ|8500 Barnes Ln.");
	}

	@Test
	public void testTrackingStrings() throws JitsException {
		boolean expectedOne = true;
		boolean actualOne = parcelGRD.getTrackingBarcodes().get(0).contains("Whse");
		assertEquals(expectedOne, actualOne);

		parcelGRD.shipParcel();
		boolean expectedTwo = true;
		boolean actualTwo = parcelGRD.getTrackingBarcodes().get(1).contains("DC1");
		assertEquals(expectedTwo, actualTwo);

		parcelGRD.shipParcel();
		boolean expectedThree = true;
		boolean actualThree = parcelGRD.getTrackingBarcodes().get(2).contains("1474");
		assertEquals(expectedThree, actualThree);
	}

}
