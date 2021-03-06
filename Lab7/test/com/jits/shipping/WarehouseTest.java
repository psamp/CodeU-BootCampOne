package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class WarehouseTest {
	
	private Warehouse warehouse;
	private Parcel parcelGRD;
	private Parcel parcelAIR;
	private Parcel parcelRAL;
	
	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
		parcelGRD = warehouse.newParcel("89687676564|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		parcelAIR = warehouse.newParcel("45890934563|AIR|72573|14839|8.09|10|6.45|12.86|&*^89786|XYZ|18 Sulfur Circle");
		parcelRAL = warehouse.newParcel("97878655498|RAL|89447|08043|8.09|10|6.45|12.86|&*^89786|XYZ|67 Double Hockey Sticks Trl");
	}
	
	@Rule
	public ExpectedException excp = ExpectedException.none();
	
	@Test
	public void testNewParcelFromIncomingBarcode() throws JitsException {
		
		assertEquals(89687676564L, parcelGRD.getId());
		assertEquals("GRD", parcelGRD.getShippingType().toString());
		assertEquals("30326", parcelGRD.getFromZip());
		assertEquals("30331", parcelGRD.getToZip());
		assertEquals(6, parcelGRD.getWeight(), .001);
		assertEquals(5, parcelGRD.getHeight(), .001);
		assertEquals(5, parcelGRD.getWidth(), .001);
		assertEquals(8, parcelGRD.getDepth(), .001);
		assertEquals("12 Main St.", parcelGRD.getAddress());
		assertEquals("Whse ", parcelGRD.getLocation().location());
		
		assertEquals(45890934563L, parcelAIR.getId());
		assertEquals("AIR", parcelAIR.getShippingType().toString());
		assertEquals("72573", parcelAIR.getFromZip());
		assertEquals("14839", parcelAIR.getToZip());
		assertEquals(9, parcelAIR.getWeight(), .001);
		assertEquals(10, parcelAIR.getHeight(), .001);
		assertEquals(7, parcelAIR.getWidth(), .001);
		assertEquals(13, parcelAIR.getDepth(), .001);
		assertEquals("18 Sulfur Circle", parcelAIR.getAddress());
		assertEquals("Whse ", parcelAIR.getLocation().location());
		
		assertEquals(97878655498L, parcelRAL.getId());
		assertEquals("RAL", parcelRAL.getShippingType().toString());
		assertEquals("89447", parcelRAL.getFromZip());
		assertEquals("08043", parcelRAL.getToZip());
		assertEquals(9, parcelRAL.getWeight(), .001);
		assertEquals(10, parcelRAL.getHeight(), .001);
		assertEquals(7, parcelRAL.getWidth(), .001);
		assertEquals(13, parcelRAL.getDepth(), .001);
		assertEquals("67 Double Hockey Sticks Trl", parcelRAL.getAddress());
		assertEquals("Whse ", parcelRAL.getLocation().location());
		
	}
	
	@Test
	public void testGetReport() {

		String actual = warehouse.getReport();
		
		assertEquals(true, actual.contains("89687676564"));
		assertEquals(true, actual.contains("45890934563"));
		assertEquals(true, actual.contains("AIR"));
		assertEquals(true, actual.contains("GRD"));
		assertEquals(true, actual.contains("DC1 Raleigh"));
		assertEquals(true, actual.contains("Arprt: DFW Dallas/Fort Worth Intl"));
		assertEquals(true, actual.contains("Arprt: JFK John F. Kennedy Intl"));
		assertEquals(true, actual.contains("12 Main St. 30331"));
		assertEquals(true, actual.contains("Dest: 18 Sulfur Circle 14839"));
		
	}
	
	@Test
	public void testInvalidShippingType() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("We only ship by ground (GRD), air (AIR) or railway (RAL.)");

		parcelGRD = warehouse.newParcel("89687676564|LOL|12365|99888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testFromZip() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("String must be 5 digit(s).");

		parcelGRD = warehouse.newParcel("89687676564|GRD|123X5|88888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testToZip() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("String must be 5 digit(s).");
		parcelGRD = warehouse.newParcel("89687676564|GRD|12345|88xx888|5.0|5|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testId() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("Digits only.");

		parcelGRD = warehouse.newParcel("896y87x676564|GRD|12309|88888|5.0|5|6|8|&*^89786|XYZ");
		
	}

}
