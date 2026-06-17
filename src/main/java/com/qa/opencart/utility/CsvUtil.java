package com.qa.opencart.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvUtil {
	
	
	
	public static final String CSV_PATH = "./scr/test/resources/testdata";
	
	public static List<String[]> rows;
	
	public static Object [][] csvData(String csvName) {
		String csvFile = CSV_PATH + csvName + ".csv";
		
		
		CSVReader reader = null; // CSVReader class 
		
		try {
			reader = new CSVReader(new FileReader(csvFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			rows = reader.readAll();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		// Note: There is no concept of columns in csv file. Only rows are there to fetch. 
		
		
		Object [][] data = new Object[rows.size()][];// column part is blank. Only rows size is given.
		
		for (int i = 0; i < rows.size(); i++) {
			data[i] = rows.get(i);
		}
		
		return data;
		
		
	}
}
