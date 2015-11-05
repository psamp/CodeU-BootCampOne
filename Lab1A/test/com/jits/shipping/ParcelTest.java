package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParcelTest {
	
	private Warehouse warehouse;
	private Parcel parcel;
	
	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
	}
	
	@Rule
	public ExpectedException excp = ExpectedException.none();
	
	@Test
	public void testNewParcelFromIncomingBarcode() throws JitsException {
		
		parcel = warehouse.newParcel("89687676564|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		
		assertEquals(89687676564L, parcel.getId());
		assertEquals("AIR", parcel.getShippingType());
		assertEquals("30326", parcel.getFromZip());
		assertEquals("30331", parcel.getToZip());
		assertEquals(6, parcel.getWeight(), .001);
		assertEquals(5, parcel.getHeight(), .001);
		assertEquals(5, parcel.getWidth(), .001);
		assertEquals(8, parcel.getDepth(), .001);
	}
	
	@Test
	public void testInvalidShippingType() throws JitsException {
		
		excp.expect(JitsException.class);
		excp.expectMessage("We only ship by ground (GRD), air (AIR) or railway (RAL.)");

		parcel = warehouse.newParcel("89687676564|LOL|123X5|888|5.0y|5z|6l|8a|&*^89786|XYZ");
		
	}
	
	@Test
	public void testInvalidZip() throws JitsException {
		parcel = warehouse.newParcel("89687676564|GRD|123X5|88888|5.0|8.89|6|8|&*^89786|XYZ");
		
	}
	
	@Test
	public void testInvalidId() throws JitsException {
		
		parcel = warehouse.newParcel("8968767y6564|GRD|X987|yI8|5.|8|0|8|&*^89786|XYZ");
		
	}

}