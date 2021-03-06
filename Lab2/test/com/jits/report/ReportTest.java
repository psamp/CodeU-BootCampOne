package com.jits.report;

import static org.junit.Assert.*;

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
		
		a = warehouse.newParcel("8978564598|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		b = warehouse.newParcel("9845340960|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		c = warehouse.newParcel("8978564598|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		d = warehouse.newParcel("1287782302|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		e = warehouse.newParcel("9756342346|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");		
		f = warehouse.newParcel("1000000000|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		g = warehouse.newParcel("2222222222|AIR|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		h = warehouse.newParcel("1234567890|GRD|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		i = warehouse.newParcel("1081243478|RAL|30326|30331|5.09|5|4.45|7.86|&*^89786|XYZ");
		
	}

	@Test
	public void testGetParcels() throws JitsException {
		
		String expected = "1287782302\tAIR" 
							+ "\n" + 
						  "2222222222\tAIR" 
							+ "\n" + 
						  "8978564598\tAIR" 
							+ "\n" + 
						  "1234567890\tGRD" 
							+ "\n" + 
						  "9756342346\tGRD" 
							+ "\n" + 
						  "9845340960\tGRD" 
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
