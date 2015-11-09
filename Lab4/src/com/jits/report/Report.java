package com.jits.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.parcel.JitsException;
import com.jits.parcel.Parcel;
import com.jits.parcel.ShippingMethod;
import com.jits.shipping.Location;

public class Report {

	private static SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
	private static Date date = new Date();
	private static final String reportHeader = "JITS Shipping Package Report";
	private static final Logger logger = LogManager.getLogger(Report.class.getSimpleName());

	private String getGroundRoute(LinkedList<Location> route) {
		String rtn = "";
		for (int i = 0; i < route.size(); i++) {
			rtn += "\t" + route.get(i).location();
		}

		return rtn;
	}

	public String generateParcelReport(ArrayList<Parcel> pkg) {
		String report = "";
//		
//		Collections.sort(pkg, new IdComparator());
//		Collections.sort(pkg, new ShippingTypeComparator());

		try {
			
			System.out.println(Report.reportHeader);

			for (Parcel parcel : pkg) {

				if (parcel.getShippingType().equals(ShippingMethod.GRD)) {
					
					report += parcel.getId() + "\t" + parcel.getShippingType() + " " + this.getGroundRoute(parcel.getRoute())
					+ "\n";


				} else {

					report += parcel.getId() + "\t" + parcel.getShippingType() + "\n";

				}
			}

			System.out.println(report);
			System.out.println("Printed on " + Report.sdf.format(Report.date));

		} catch (NullPointerException e) {

			Report.logger.error(new JitsException(e.getMessage(), new IllegalArgumentException()));

		}

		return report;

	}

}
