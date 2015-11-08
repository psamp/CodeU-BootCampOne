package com.jits.routing;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Chalkboard {

	public static void main(String[] args) {
		
		Workbook workbook = null;
		
		try {
			
			workbook = Workbook.getWorkbook(new File("zip.xls"));
			Sheet sheet = workbook.getSheet(0);
			int zipcodeRow = sheet.findCell("30326").getRow();
			Cell stateFromZipcodeRow = sheet.getCell(1, zipcodeRow);
			System.out.println(stateFromZipcodeRow.getContents());
					
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}

	}

}