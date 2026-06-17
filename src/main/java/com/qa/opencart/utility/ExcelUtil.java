package com.qa.opencart.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	//path to the file in static final mehtod
	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/Signup Data.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	public static Object [][] getTestData(String sheetName) {
		Object data [][] = null;
		
		//try catch block is added to avoid any exception during making a connection between data file and code 
		//FileInputStream will make a connection between data file and code here
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			
			try {
				book = WorkbookFactory.create(ip);// this will create an excel like data in the memory 
			//WorkbookFactory is a class from apache.poi api for reading excel data 
				// create is a method of that class 
				sheet =  book.getSheet(sheetName);
				
				// now to retrieve data we need 2 for loops, (1 for rows, 1 for column) and a 2d array 
				
				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				// here first square bracket gets row count and second column count
				
				for (int i = 0; i< sheet.getLastRowNum() ; i++) {
					for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
						data [i][j]= sheet.getRow(i+1).getCell(j).toString();// i+1 because actual data starts from 2nd row of excel
						// 1st row is column names
						//toString method converts data to a String value 
					}
				}
				
				
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
					}
		return data;
		
		
		
		
	}
	
	
	

}
