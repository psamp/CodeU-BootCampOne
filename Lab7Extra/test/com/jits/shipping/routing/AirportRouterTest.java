package com.jits.shipping.routing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class AirportRouterTest {
	Router router;
	Parcel parcelGRD;
	Parcel parcelAIR;
	Parcel parcelRAL;
	Warehouse warehouse;
	
	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcelGRD = warehouse.newParcel("89687676564|GRD|30326|30313|5.09|5|4.45|7.86|&*^89786|XYZ|426 Marietta St.");
		parcelAIR = warehouse.newParcel("97978675325|AIR|28364|43333|8.09|10|6.45|12.86|&*^89786|XYZ|8500 Barnes Ln.");
		parcelRAL = warehouse.newParcel("97978675325|RAL|81631|06785|8.09|10|6.45|12.86|&*^89786|XYZ|8500 Barnes Ln.");
		router = new AirportRouter();
	}

	@Test
	public void testLocateByZip() throws JitsException {
		String expected = "Arprt: SEA Seattle-Tacoma Intl ";
		String actual = router.locateByZip("98001").location();
		assertEquals(expected, actual);
	}

	@Test
	public void testDetermineRouteHistory() throws JitsException {
		String expectedOne = "Whse ";
		String actualOne = parcelGRD.getLocation().location();
		assertEquals(expectedOne, actualOne);

		parcelGRD.shipParcel();
		String expectedTwo = "DistCtr: DC1 Raleigh ";
		String actualTwo = parcelGRD.getLocation().location();
		assertEquals(expectedTwo, actualTwo);

		parcelGRD.shipParcel();
		String expectedThree = "Dest: 426 Marietta St. 30313";
		String actualThree = parcelGRD.getLocation().location();
		assertEquals(expectedThree, actualThree);

		String expectedOneAir = "Whse ";
		String actualOneAir = parcelAIR.getLocation().location();
		assertEquals(expectedOneAir, actualOneAir);

		parcelAIR.shipParcel();
		String expectedTwoAir = "Arprt: ATL Hartsfield-Jackson Intl ";
		String actualTwoAir = parcelAIR.getLocation().location();
		assertEquals(expectedTwoAir, actualTwoAir);

		parcelAIR.shipParcel();
		String expectedThreeAir = "Arprt: ORD O'Hare International ";
		String actualThreeAir = parcelAIR.getLocation().location();
		assertEquals(expectedThreeAir, actualThreeAir);

		parcelAIR.shipParcel();
		String expectedFourAir = "Dest: 8500 Barnes Ln. 43333";
		String actualFourAir = parcelAIR.getLocation().location();
		assertEquals(expectedFourAir, actualFourAir);
	}

}
