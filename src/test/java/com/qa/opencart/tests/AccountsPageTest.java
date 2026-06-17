package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
//import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {
	// in TestNG BT--> BC (BeforeTest executes first then BeforeClass methods) ---> @Test 

	@BeforeClass
	public void accPageSetup() {

		//accPage = loginPage.doLogin("er.anshul20@gmail.com", "Aye91221");// doing login before executing any other pages
																			// on the
		// application
		// we used doLogin method from the LoginPage class and took loginPage obj
		// reference created in the BaseTest.
// doLogin is giving accPage in return as Page chaining model in LoginPage class, so it is stored in accPage reference. 

//		accPage = new AccountsPage(driver);// AccountsPage class object created to access all the methods created to
		// validate in the test class
		// no need of this line accPage = new AccountsPage(driver) as accPage reference
		// is already initialized above.
		
		//using prop reference from BaseTest 
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	
	@Test
	public void isLogoutLinkexistsTest() {
		Assert.assertTrue(accPage.isLogoutLinkvisible());
	}

	//@Test
	//public void accountPageHeadersTest() {
	//	List<String> actualHeadersList = accPage.getAccountPageHeaders();
	//	 Assert.assertEquals(actualHeadersList.size(), AppConstants.ACC_PAGE_HEADER_COUNT);// comparing headers count from
		// actual to expected
	//	Assert.assertEquals(actualHeadersList, AppConstants.expectedAccPageHeadersList);// here our expected headers list are constants of the page 
		
	//}

}
