package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.utility.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;// every page class will have its own eleUtil

	// in POM, we will have a separate class of each page
// functionality of the login page is to perform an action/method and return that
	// it is not the responsibility of the page to validate something, that will be
	// done by test class
	// it is a page behavior we want to perform

	// elements of a page we need following things on each page
	// this page class has no idea to initialize the driver, browser. It will be
	// done by DriverFactory class

	// 1. private By locators: called as page objects
// all the locators are private so that no one can access them outside of this class 

	// things we can validate on this page , use by locator and methods to be
	// validated on test class

	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By forgotPasswordlink = By.linkText("Forgotten Password");
	private final By loginButton = By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/input");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	private final By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	private static final Logger log = LogManager.getLogger(LoginPage.class);// code to support log4j logging

	// 2. public constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);// a const.. for ElementUtil class

	}

	// 3. public page methods/actions
	// methods are public because it can be exposed to another class and methods to
	// validate the results
	public String getLoginPageTitle() {
		// String title = driver.getTitle();

		// ********** updating code base on ElementUtil class

		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_WAIT); // using
																												// eleUtil
																												// methods
		// used AppConstants to add final variables in code to hide plain text
		// System.out.println("Page title is " + title);
		// replacing system.out.println with log
		log.info("Page title is :" + title);
		return title;

	}

	public String getLoginPageURL() {

		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_MEDIUM_WAIT); // using
																															// eleUtil
																															// methods
		// String url = driver.getCurrentUrl();
		// System.out.println("Page url is " + url);
		// replacing system.out.println with log
		log.info("Page url is :" + url);
		return url;

	}

	public boolean isForgotPasswordLinkExists() {
//		return driver.findElement(forgotPassword).isDisplayed();
		return eleUtil.isElementDisplayed(forgotPasswordlink);// using eleUtil methods

	} // here forgorPassword is used from the locators to validate an action, example
		// of Encapsulation: a private variable is used by another method in the class.
		// page class is a classic example of Encapsulation

	public boolean isHeaderExists() {
		// return driver.findElement(header).isDisplayed();
		return eleUtil.isElementDisplayed(header); // using eleUtil methods
	}

	public AccountsPage doLogin(String appUsername, String appPwd) {
		// System.out.println("Application credentials: " + appUsername + ":" + appPwd);
		// replacing system.out.println with log
		log.info("Application credentials:" + appUsername + ":" + appPwd);

		// driver.findElement(emailID).sendKeys(appUsername);

		eleUtil.waitElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appUsername); // entering value
																										// for email id
																										// using eleUtil methods
		// driver.findElement(password).sendKeys(appPwd);
		eleUtil.doSendKeys(password, appPwd);// here no need to wait for password field, as for
												// email we have already waited
		// driver.findElement(loginButton).click();
		eleUtil.doClick(loginButton);
		// return driver.getTitle();
		// return type is removed from String to class name AccountsPage
		return new AccountsPage(driver);
	}
	

	// Note: Whenever we are clicking on the some button that results in redirection
	// to the new page, then we must return the object of the next page as per POM.
	// This is called Page Chaining Model ******* This is method responsibility to
	// give the next page class Object in return.
	// So we are removing return driver.getTitle(); to add next page class object.
	
	//doLogin method with negative test creadentials 
	
	public boolean doLoginWithInvalidCredentials(String invalidUsername, String invalidPwd) {
		log.info("Invalid Application credentials:" + invalidUsername + ":" + invalidPwd);
		eleUtil.waitElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).clear();
		eleUtil.waitElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(invalidUsername);
		eleUtil.doSendKeys(password, invalidPwd);
		eleUtil.doClick(loginButton);
		//on clicking login button, error message will appear for incorrect creds
		String errorMessage = eleUtil.doElementGetText(loginErrorMessage);
		log.info("Invalid user creadetials message: " +errorMessage);
		
		if(errorMessage.contains(AppConstants.BLANK_USER_CREDS_ERROR_MESSAGE)){
			return true;// in case blank credentials are given for login attempt
		}
		
		else if(errorMessage.contains(AppConstants.INVALID_USER_CREDS_ERROR_MESSAGE)){
			return true;
		}
		else return false;	
	}
	

	// registration page chaining model
	public RegisterPage navigateToRegisterPage() {
		log.info("Navigating to the registration page");
		eleUtil.waitElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegisterPage(driver);

	}

}
