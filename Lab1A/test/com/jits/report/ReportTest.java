package com.jits.report;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

public class ReportTest {
	
	private ArrayList<Parcel> pkg = new ArrayList<Parcel>();
	Parcel a;
	Parcel b;
	Parcel c;
	Parcel d;
	Parcel e;
	Parcel f;
	Parcel g;
	Parcel h;
	Parcel i;
	
	@Before
	public void setUp() throws Exception {
		a = new Parcel("8978564598", "AIR");
		pkg.add(a);
		
		b = new Parcel("984534096", "GRD");
		pkg.add(b);
		
		c = new Parcel("8978564598", "RAL");
		pkg.add(c);
		
		d = new Parcel("1287782302", "AIR");
		pkg.add(d);
		
		e = new Parcel("9756342346", "GRD");
		pkg.add(e);
		
		f = new Parcel("1000000000", "RAL");
		pkg.add(f);
		
		g = new Parcel("2222222222", "AIR");
		pkg.add(g);
		
		h = new Parcel("1234567890", "GRD");
		pkg.add(h);
		
		i = new Parcel("1081243478", "RAL");
		pkg.add(i);
		
	}

	@Test
	public void testGetParcels() throws JitsException {
		System.out.println(Report.generateParcelReport(pkg));
	}

}
