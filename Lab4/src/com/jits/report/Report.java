package com.jits.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.parcel.JitsException;
import com.jits.parcel.Parcel;
import com.jits.parcel.ShippingMethod;

public class Report {

	private static SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
	private static Date date = new Date();
	private static final String reportHeader = "JITS Shipping Package Report";
	private static final Logger logger = LogManager.getLogger(Report.class.getSimpleName());

	public String generateParcelReport(List<Parcel> pkg) {
		String report = Report.reportHeader + "\n";

		try {

			for (Parcel parcel : pkg) {

				report += parcel.report();

				if (parcel.getShippingType().equals(ShippingMethod.GRD)) {

					report += parcel.getEntireRoute() + "\n";

				}
			}

			report += "Printed on " + Report.sdf.format(Report.date);

		} catch (NullPointerException e) {

			Report.logger.error(new JitsException(e.getMessage(), new IllegalArgumentException()));

		}

		return report;

	}

}