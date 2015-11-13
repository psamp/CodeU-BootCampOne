package com.jits.shipping.report;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class ReportTest {
	private Warehouse warehouse;
	@SuppressWarnings("unused")
	private Parcel a;
	private Parcel b;
	@SuppressWarnings("unused")
	private Parcel c;
	private Parcel d;
	@SuppressWarnings("unused")
	private Parcel e;
	@SuppressWarnings("unused")
	private Parcel f;
	@SuppressWarnings("unused")
	private Parcel g;
	@SuppressWarnings("unused")
	private Parcel h;
	@SuppressWarnings("unused")
	private Parcel i;
	
	@Before
	public void setUp() throws Exception {
		
		warehouse = new Warehouse();
		
		a = warehouse.newParcel("8978564598|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		b = warehouse.newParcel("9845340960|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|1138 Star Way");
		c = warehouse.newParcel("8978564598|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		d = warehouse.newParcel("1287782302|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		e = warehouse.newParcel("9756342346|GRD|30326|86038|5.09|5|4.45|7.86|&*^89786|XYZ|56 Main St.");		
		f = warehouse.newParcel("1000000000|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		g = warehouse.newParcel("2222222222|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		h = warehouse.newParcel("1234567890|GRD|30326|06468|5.09|5|4.45|7.86|&*^89786|XYZ|93 Hurricane Circle");
		i = warehouse.newParcel("1081243478|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		
	}

	@Test
	public void testGetParcels() throws JitsException {
		String actual = warehouse.getReport();
		
		assertEquals(true, actual.contains("JITS Shipping Package Report"));
		assertEquals(true, actual.contains("8978564598\tAIR"));
		assertEquals(true, actual.contains("9845340960\tGRD"));
		assertEquals(true, actual.contains(b.getEntireRoute()));
		assertEquals(true, actual.contains(d.getEntireRoute()));
		assertEquals(true, actual.contains("8978564598\tRAL"));
		assertEquals(true, actual.contains("Printed on "));
	}

}
