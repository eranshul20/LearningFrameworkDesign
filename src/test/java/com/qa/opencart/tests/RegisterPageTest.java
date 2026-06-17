package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utility.CsvUtil;
import com.qa.opencart.utility.ExcelUtil;
import com.qa.opencart.utility.StringUtils;

public class RegisterPageTest extends BaseTest{
	
	//pre-condition for this test is to reach registration page, because BaseTest redirects user to Login screen 
	
	// BaseTest will open the browser+ Login url--> BeforeClass- Move to register page- --> @Test
	
	
	
	@BeforeClass
	
	public void goToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
		// since ResgiterPageTest extends BaseTest, and BaseTest has reference variable for LoginPage Class, I can take 
		//loginPage variable and call navigateToRegisterPage method 
		//navigateToRegisterPage returns me RegisterPage object reference
		//Now, I must store the reference of 		loginPage.navigateToRegisterPage();  to the BaseTest as other pages 
 //So now, 		registerPage = loginPage.navigateToRegisterPage();

	}
	
	@DataProvider
	public Object[][] getRegisterData() { // 2D array forming data for DataProvider. It is still static! 
	return new Object [][] {
		{"Tomy", "Hanka","9886523211","Test@123","yes"},
		{"Tom", "Dhanka","8886523211","Test@123","no"},
		//{"Joy", "King","9886523211","Jest@123","yes"},	
	};
	
	}
	
	// To read the excel file for signup data, we need Apache POI API in our POM.mxl file. 
	// we need to add dependencies for the same in POM file 
	// We must have standard Microsoft excel. No other workbook/officeLibre would work
	
	//@DataProvider
	//public Object [][] getRegisterSheetData() {
		
	//	return ExcelUtil.getTestData("RegisterData");
	//}
	
	//Note: Best practices for such tests is using static data within the code like in case of getRegisterData with 3 rows above
	// because, if registration is working for 3 users, it will work for 20 more users and we don't have to bombard data to the system.
	//Benefits of keeping static data within the code: 
	// 1. No need of maintenance of excel : Changing data in Excel again and again.
	// 2. No worry of excel data being corrupted 
	// 3. No worry of excel being deleted/ path being changed
	// 4. Microsoft license fee for Mac machines/All window machines
	// 5. Dependencies on Apache POI API. If upgrade are not compatible. 
	

	/*@DataProvider 
	public Object[][] getCSVRegisterData(){
		return CsvUtil.csvData("CsvRegisterData");
	}
	*/
	//Now, we can use CSV to @Test and it will work. 
	
	
	
	@Test (dataProvider = "getRegisterData") // using Data Provider using static object array above  
	//@Test (dataProvider = "getRegisterSheetData") // using Data Provider by Excel sheet 

	public void registerPageTest(String firstName, String lastName, String telephone, 
			String password , String subscribe ) {
	Assert.assertTrue(registerPage.userRegister( firstName, lastName, 
			StringUtils.getRandomEmails(), telephone, password, subscribe));
	
	
	
	
	
	
	}
}
	/*@Test using single data 
	public void registerPageTest() {
	Assert.assertTrue(registerPage.userRegister("Tomy", 
			"Hanka",
			StringUtils.getRandomEmails(),
			"9886523211",
			"Test@123",
			"yes"));
		
	}// user getRandomEmails method from StringUtils 
	*/
	
	
	//
	


