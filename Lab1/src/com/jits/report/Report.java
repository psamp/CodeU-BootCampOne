package com.jits.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;

public class Report {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
	private static Date date = new Date();
	private static final String reportHeader = "JITS Shipping Package Report";
	private static final Logger logger = LogManager.getLogger(Report.class.getSimpleName());

	public String generateParcelReport(ArrayList<Parcel> pkg) {
		
		String report = "";

		try {
			
			System.out.println(Report.reportHeader);

			for (Parcel parcel : pkg) {
				report += parcel.getId() + "\t" + parcel.getShippingType() + "\n";
			}
			
			System.out.println(report);
			System.out.println("Printed on " + Report.sdf.format(Report.date));

		} catch(NullPointerException e) {
			Report.logger.error(new JitsException(e.getMessage(), new IllegalArgumentException()));
		}
		
		return report;
		
	}

}
