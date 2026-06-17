package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{

	//BT(opening chrome+url)  ---> BC (login to the website) ----> @Test 
	//Before test, Before Class and then Current page Test annotations 
	
	@BeforeClass
	public void searchPageSetup() {
		//accPage = loginPage.doLogin("er.anshul20@gmail.com", "Aye91221");
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));// using prop reference from BaseTest

	}
	
	
	@DataProvider
	public Object [][] getSearch() {
		return new Object [][] {
			{"macbook", "MacBook Pro"}
		};
	}
	
	
	
	@Test(dataProvider = "getSearch")
	public void searchTest(String searchkey, String productName) {
		searchResultsPage = accPage.doSearch(searchkey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		// we are storing the output in the return type of the methods 
		// doSearch is returning searchresultspage, selectproduct is returning Productinfopage 
		String actheadertext = productInfoPage.getProductHeader();
		Assert.assertEquals(actheadertext, productName);
	}

	
	
}
