package com.jits.shipping.routing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.ShippingMethod;
import com.jits.shipping.Warehouse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public abstract class Router {
	
	static Map<String, Coordinate> zipcodeMap = new HashMap<String, Coordinate>(29500);
	
	static {
		Router.loadZipcodeData();
	}
	
	abstract Location locateByZip(String zipcode) throws JitsException;
	abstract void determineRoute(Parcel parcel) throws JitsException;

	public static void route(Parcel parcel) throws JitsException {
		parcel.getRoute().add(new Warehouse());

		if (parcel.getShippingType().equals(ShippingMethod.GRD)) {
		
			Router grdRouter = new GroundRouter();
			grdRouter.determineRoute(parcel);
		
		} else if (parcel.getShippingType().equals(ShippingMethod.AIR)) {
			
			Router airRouter = new AirportRouter();
			airRouter.determineRoute(parcel);
		
		} else if (parcel.getShippingType().equals(ShippingMethod.RAL)) {
			
			Router ralRouter = new RailRouter();
			ralRouter.determineRoute(parcel);
		
		}

		parcel.getRoute().add(new Destination(parcel.getAddress(), parcel.getToZip()));

		parcel.shipParcel();

	}
	
	private static void loadZipcodeData() {
		WorkbookSettings settings = new WorkbookSettings();
		settings.setSuppressWarnings(true);

		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File("zipLatLng.xls"), settings);
			Sheet sheet = workbook.getSheet(0);
			int numRows = sheet.getRows();
			for (int i = 1; i < numRows; i++) {
				Cell[] rowCells = sheet.getRow(i);
				String zipcode = rowCells[1].getContents();
				String lat = rowCells[4].getContents();
				String lng = rowCells[5].getContents();
				zipcodeMap.put(zipcode, new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
	}

}
