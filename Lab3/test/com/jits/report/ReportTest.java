package com.jits.report;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class ReportTest {
	private Warehouse warehouse;
	private Parcel a;
	private Parcel b;
	private Parcel c;
	private Parcel d;
	private Parcel e;
	private Parcel f;
	private Parcel g;
	private Parcel h;
	private Parcel i;
	
	@Before
	public void setUp() throws Exception {
		
		warehouse = new Warehouse();
		
		a = warehouse.newParcel("8978564598|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		b = warehouse.newParcel("9845340960|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|1138 Star Way");
		c = warehouse.newParcel("8978564598|RAL|12345|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		d = warehouse.newParcel("1287782302|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		e = warehouse.newParcel("9756342346|GRD|30326|86038|5.09|5|4.45|7.86|&*^89786|XYZ|56 Main St.");		
		f = warehouse.newParcel("1000000000|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		g = warehouse.newParcel("2222222222|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		h = warehouse.newParcel("1234567890|GRD|30326|06468|5.09|5|4.45|7.86|&*^89786|XYZ|93 Hurricane Circle");
		i = warehouse.newParcel("1081243478|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ|12 Main St.");
		
		ArrayList<Parcel> routeGRDParcels = new ArrayList<Parcel>();
		
		routeGRDParcels.add(b);
		routeGRDParcels.add(e);
		routeGRDParcels.add(h);
		
		for (Parcel parcel : routeGRDParcels) {
			parcel.shipParcel();
			parcel.shipParcel();
		}
		
	}

	@Test
	public void testGetParcels() throws JitsException {
		
		String expected = "1287782302\tAIR" 
							+ "\n" + 
						  "2222222222\tAIR" 
							+ "\n" + 
						  "8978564598\tAIR" 
							+ "\n" + 
						  "1234567890\tGRD Whse DistCtr: DC1 Raleigh Dest: 93 Hurricane Circle 06468" 
							+ "\n" + 
						  "9756342346\tGRD Whse DistCtr: DC3 Denver Dest: 56 Main St. 86038" 
							+ "\n" + 
						  "9845340960\tGRD Whse DistCtr: DC1 Raleigh Dest: 1138 Star Way 30331" 
							+ "\n" + 
						  "1000000000\tRAL" 
							+ "\n" + 
						  "1081243478\tRAL" 
							+ "\n" + 
						  "8978564598\tRAL" 
							+ "\n";
		
		String actual = warehouse.getReport();
		assertEquals(expected, actual);
	}

}
