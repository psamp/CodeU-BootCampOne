package com.jits.routing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jits.shipping.JitsException;

import jxl.*;
import jxl.read.biff.BiffException;

public class Router {
	
	private static Map<String, DistributionCenter> statesToDistributionCenters;
	private static final Logger logger = LogManager.getLogger(Router.class.getSimpleName());
	
	static {
		Router.statesToDistributionCenters = new HashMap<String, DistributionCenter>();
		
		DistributionCenter dc1 = new DistributionCenter("Raleigh", "dc1");
		DistributionCenter dc2 = new DistributionCenter("Kansas City", "dc2");
		DistributionCenter dc3 = new DistributionCenter("Denver", "dc3");
		DistributionCenter na = new DistributionCenter("N/A", "na");
		
		statesToDistributionCenters.put("AL", dc2);
		statesToDistributionCenters.put("AK", dc3);
		statesToDistributionCenters.put("AZ", dc3); 
		statesToDistributionCenters.put("AR", dc2);
		statesToDistributionCenters.put("CA", dc3);
		statesToDistributionCenters.put( "CO", dc3);
		statesToDistributionCenters.put("CT", dc1);
		statesToDistributionCenters.put("DE", dc1);
		statesToDistributionCenters.put("DC", dc1);
		statesToDistributionCenters.put("FL", dc1);
		statesToDistributionCenters.put("GA", dc1);
		statesToDistributionCenters.put("HI", na);
		statesToDistributionCenters.put("ID", dc3);
		statesToDistributionCenters.put("IL", dc2);
		statesToDistributionCenters.put("IN", dc2);
		statesToDistributionCenters.put("IA", dc2);
		statesToDistributionCenters.put("KS", dc3);
		statesToDistributionCenters.put("KY", dc2);
		statesToDistributionCenters.put("LA", dc2);
		statesToDistributionCenters.put("ME", dc1);
		statesToDistributionCenters.put("MD", dc1);
		statesToDistributionCenters.put("MA", dc1);
		statesToDistributionCenters.put("MI", dc2);
		statesToDistributionCenters.put("MN", dc2);
		statesToDistributionCenters.put("MS", dc2);
		statesToDistributionCenters.put("MO", dc2);
		statesToDistributionCenters.put("MT", dc3);
		statesToDistributionCenters.put("NE", dc3);
		statesToDistributionCenters.put("NV", dc3);
		statesToDistributionCenters.put("NH", dc1);
		statesToDistributionCenters.put("NJ", dc1);
		statesToDistributionCenters.put("NM", dc3);
		statesToDistributionCenters.put("NY", dc1);
		statesToDistributionCenters.put("NC", dc1);
		statesToDistributionCenters.put("ND", dc3);
		statesToDistributionCenters.put("OH", dc1);
		statesToDistributionCenters.put("OK", dc3);
		statesToDistributionCenters.put("OR", dc3);
		statesToDistributionCenters.put("PA", dc1);
		statesToDistributionCenters.put("RI", dc1);
		statesToDistributionCenters.put("SC", dc1);
		statesToDistributionCenters.put("SD", dc3);
		statesToDistributionCenters.put("TN", dc2);
		statesToDistributionCenters.put("TX", dc3);
		statesToDistributionCenters.put("UT", dc3);
		statesToDistributionCenters.put("VT", dc1);
		statesToDistributionCenters.put("VA", dc1);
		statesToDistributionCenters.put("WA", dc2);
		statesToDistributionCenters.put("WV", dc1);
		statesToDistributionCenters.put("WI", dc2);
		statesToDistributionCenters.put("WY", dc3);
		
	}
	
	private String stateLookup(String zipcode) {
		
		String state = "";
		Workbook workbook = null;
		WorkbookSettings wbSettings = new WorkbookSettings();
		
		wbSettings.setSuppressWarnings(true);
		
		try {
			
			workbook = Workbook.getWorkbook(new File("zip.xls"), wbSettings);
			Sheet sheet = workbook.getSheet(0);
			int zipcodeRow = sheet.findCell(zipcode).getRow();
			state = sheet.getCell(1, zipcodeRow).getContents();
					
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		
		return state;
		
	}
	
	public DistributionCenter distributionCenterLookup(String zipcode) throws JitsException {
		DistributionCenter rtn;
		
		try {
			rtn = Router.statesToDistributionCenters.get(this.stateLookup(zipcode));

			
		} catch (NullPointerException e) {
			
			JitsException d = new JitsException("Invalid US state.", e);
			e.printStackTrace();
			Router.logger.error(d.getMessage());
			throw d;
			
		}
		
		return rtn;
		
	}
}
