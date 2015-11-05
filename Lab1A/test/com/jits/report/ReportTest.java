package com.jits.report;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

public class ReportTest {
	
	private ArrayList<Parcel> pkg = new ArrayList<Parcel>();
	
	@Before
	public void setUp() throws Exception {
		Parcel a = new Parcel();
		a.setId("8978564598");
		a.setShippingType("AIR");
		pkg.add(a);
		
		Parcel b = new Parcel();
		b.setId("984534096");
		a.setShippingType("GRD");
		pkg.add(b);
		
		Parcel c = new Parcel();
		c.setId("8978564598");
		c.setShippingType("RAL");
		pkg.add(c);
		
		Parcel d = new Parcel();
		d.setId("1287782302");
		d.setShippingType("AIR");
		pkg.add(d);
		
		Parcel e = new Parcel();
		e.setId("9756342346");
		e.setShippingType("GRD");
		pkg.add(e);
		
		Parcel f = new Parcel();
		f.setId("1000000000");
		f.setShippingType("RAL");
		pkg.add(f);
		
		Parcel g = new Parcel();
		g.setId("2222222222");
		g.setShippingType("AIR");
		pkg.add(g);
		
		Parcel h = new Parcel();
		h.setId("1234567890");
		h.setShippingType("GRD");
		pkg.add(h);
		
		Parcel i = new Parcel();
		i.setId("1081243478");
		i.setShippingType("RAL");
		pkg.add(i);
		
	}

	@Test
	public void testGetParcels() throws JitsException {
		System.out.println(Report.generateParcelReport(pkg));
	}

}