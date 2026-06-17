package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// public const..(Constructor) for RegisterPage class
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//locators of the Registration page
	// private to keep them accessible only within the class
	private final By firstName = By.id("input-firstname");// used final to avoid changes in the page onjects
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");

	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private final By successMessage = By.cssSelector("div#content h1");

	private final By logoutLink = By.linkText("Logout");

	private final By registerLink = By.linkText("Register");
	private final By demoLink = By.linkText("DemoLink to check git changes");

	
	//create a public method which will use above locators to fill the register form
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone, 
			String password , String subscribe) {
		
		eleUtil.waitElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);;
	
		// to access the class variable with same name,
		//'this' keyword is used to take value of by-locators above in the userRegister parameters
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);

// an if else case for Subscription Y/N
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMesg = eleUtil.waitElementVisible(successMessage, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		
		System.out.println(successMesg);
		
		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);// to make multiple registration request,
			                            //first need to logout to repeat another user flow
			eleUtil.waitForElementsVisible(registerLink, AppConstants.DEFAULT_MEDIUM_WAIT);
			eleUtil.doClick(registerLink);
			return true;
		}else 
		{ return false;
		}
		
	}
	
	


	
	
}
