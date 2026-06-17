package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;


//Allure Report annotations for documentation:
public class LoginPageTest extends BaseTest {

	// LoginPageTest class is child of BaseTest, so it can inherit all the methods
	// of BaseTest

	@Test
	public void LoginPageTitleTest(){
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login page title "+ actualTitle);// adding ChainTestListener log method to get better report. 
		// now to validate the test, Assert class is used
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		// Test methods are calling page Class methods
	}

	@Test
	public void LoginPageURLTest(){
		String actualURL = loginPage.getLoginPageURL();
		// now to validate the test, Assert class is used
		// Assert.assertEquals(actualURL,
		// "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// or
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));

		// Note: we never print anything in the test classes. Everything should be
		// printed in Page class
		// number of @Test annotations equals to number of test cases written in the
		// Test class
		// one @Test annotation should be responsible for one test/validation we want to
		// achieve.
	}

	@Test
	public void isForgotPasswordLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
	}

	@Test
	public void isloginPageHeaderExistsTest() {
		Assert.assertTrue(loginPage.isHeaderExists());
	}

	@Test()
	public void loginTest(){
		// accPage = loginPage.doLogin("er.anshul20@gmail.com", "Aye91221"); instead of
		// it using prop reference from BaseTest class

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
// username and password from config.properties 
		Assert.assertTrue(accPage.isLogoutLinkvisible());
	}

}
