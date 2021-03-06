package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WarehouseTest {
	
	private Warehouse warehouse;
	private Parcel parcel;
	
	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcel = warehouse.newParcel("89687676564|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
	}
	
	@Rule
	public ExpectedException excp = ExpectedException.none();
	
	@Test
	public void testNewParcelFromIncomingBarcode() throws JitsException {

		assertEquals(89687676564L, parcel.getId());
		assertEquals("AIR", parcel.getShippingType());
		assertEquals("30326", parcel.getFromZip());
		assertEquals("30331", parcel.getToZip());
		assertEquals(6, parcel.getWeight(), .001);
		assertEquals(5, parcel.getHeight(), .001);
		assertEquals(5, parcel.getWidth(), .001);
		assertEquals(8, parcel.getDepth(), .001);
		assertEquals("12 Main St.", parcel.getAddress());
		assertEquals("Whse ", parcel.getLocation());
		assertEquals(1, parcel.getRouteSequenceTracker());
	}
	
	@Test
	public void testInvalidShippingType() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("We only ship by ground (GRD), air (AIR) or railway (RAL.)");

		parcel = warehouse.newParcel("89687676564|LOL|12365|99888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testFromZip() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("String must be 5 digit(s).");

		parcel = warehouse.newParcel("89687676564|GRD|123X5|88888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testToZip() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("String must be 5 digit(s).");
		parcel = warehouse.newParcel("89687676564|GRD|12345|88xx888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testId() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("Digits only.");

		parcel = warehouse.newParcel("896y87x676564|GRD|12309|88888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void getReport() {

		String expected = "89687676564\tAIR" + "\n";
		String actual = warehouse.getReport();
		
		assertEquals(expected, actual);
		
	}

}
