package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {

	//class to test negative test cases for the Login page. 
	//LoginPageTest is already working for positive flow, but for negative flow, we are doing this. 
	
	
	//to provide multiple combination of negative test credentials, use data provider 
	
	@DataProvider
	public Object [][] getNegativeLoginData() {
		return new Object [][] {
			{"ttt1@yopmail.com" , "test@123"},
			{"ttt1@yopmailcom" , "test@123"},
			{"ttt1@@yopmail.com" , "test@123"},
			{"ttt1@yopmail.com" , ""},
			{"  ", "  "}
		};
	}
	
	
	
	@Test(dataProvider = "getNegativeLoginData")
	public void negativeLoginTest(String invalidUsername, String invalidPwd) {
		
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(invalidUsername, invalidPwd));
		
	}
	
	
	
	
	
}
