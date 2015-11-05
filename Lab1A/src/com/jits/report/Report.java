package com.jits.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.jits.library.IdComparator;
import com.jits.library.ShippingTypeComparator;
import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

public class Report {

	private static final String reportHeader = "JITS Shipping Package Report";

	public static String generateParcelReport(ArrayList<Parcel> pkg) throws JitsException {
		String report = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date printedAt = new Date();
		
		System.out.println(Report.reportHeader);

		try {

			Collections.sort(pkg, new IdComparator());
			Collections.sort(pkg, new ShippingTypeComparator());

			for (Parcel parcel : pkg) {
				report += parcel.toString() + "\n";
			}

		} catch(NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		System.out.println("Printed on " + sdf.format(printedAt));
		
		return report;
	}

}
