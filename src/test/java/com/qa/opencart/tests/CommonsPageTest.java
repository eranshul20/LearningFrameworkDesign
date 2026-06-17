package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class CommonsPageTest extends BaseTest {

	@Test
	public void checkCommonElementsOnLoginPagetest() {// commonsPage object reference is coming from BaseTest object
		// we can create object reference here as well

		SoftAssert softAssert = new SoftAssert();// using SoftAssert if any of the first assertion is getting failed,
													// then next steps will be executed.
		softAssert.assertTrue(commonsPage.isLogoExists());
		softAssert.assertTrue(commonsPage.isSearchFieldExists());

		List<String> footerList = commonsPage.isFooterLinksExists();
		softAssert.assertEquals(footerList.size(), AppConstants.DEFAULT_FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}

	//testing the common elements on another pages of application 
	//accounts page
	// for account page we need to add a step to login to the application 
	@Test
	public void checkCommonElementsOnAccountsPagetest() {// commonsPage object reference is coming from BaseTest object
		// we can create object reference here as well

		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		SoftAssert softAssert = new SoftAssert();// using SoftAssert if any of the first assertion is getting failed,
													// then next steps will be executed.
		softAssert.assertTrue(commonsPage.isLogoExists());
		softAssert.assertTrue(commonsPage.isSearchFieldExists());

		List<String> footerList = commonsPage.isFooterLinksExists();
		softAssert.assertEquals(footerList.size(), AppConstants.DEFAULT_FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
	
	
	
}
