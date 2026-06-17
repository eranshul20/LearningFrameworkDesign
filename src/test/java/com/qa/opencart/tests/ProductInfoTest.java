package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	// BT(opening chrome+url) ---> BC (login to the website) ----> @Test
	// Before test, Before Class and then Current page Test annotations

	@BeforeClass
	public void productInfoSetup() {
		// accPage = loginPage.doLogin("er.anshul20@gmail.com", "Aye91221");
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); // using prop reference

	}

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "samsung", "Samsung SyncMaster 941BW" },
				{ "canon", "Canon EOS 5D" } };
	}

	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchkey, String productName) {// here it represents 2D array from above
																			// DataProvider.
		// first is input search key and then second string is desired product
		searchResultsPage = accPage.doSearch(searchkey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		// we are storing the output in the return type of the methods
		// doSearch is returning searchresultspage, selectproduct is returning
		// Productinfopage
		String actheadertext = productInfoPage.getProductHeader();
		Assert.assertEquals(actheadertext, productName);
	}// ********** using DataProvider, this Test will run 3 times based on the number
		// of rows present in the DataProvider getProducts

	// taking data as dynamic search input and its validation as well from the
	// DataProvider annotation
	/*
	 * @Test public void productHeaderTest() { searchResultsPage =
	 * accPage.doSearch("Macbook"); productInfoPage =
	 * searchResultsPage.selectProduct("MacBook Pro"); // we are storing the output
	 * in the return type of the methods // doSearch is returning searchresultspage,
	 * selectproduct is returning Productinfopage String actheadertext =
	 * productInfoPage.getProductHeader(); Assert.assertEquals(actheadertext,
	 * "MacBook Pro"); }
	 */

	@DataProvider
	public Object[][] getProductsImagesCount() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "canon", "Canon EOS 5D", 3 } };
	}

	/*
	 * @Test(dataProvider = "getProductsImagesCount") public void
	 * productImageCountTest(String searchkey, String productName, int
	 * productImageCount) { searchResultsPage = accPage.doSearch(searchkey);
	 * productInfoPage = searchResultsPage.selectProduct(productName); // we are
	 * storing the output in the return type of the methods // doSearch is returning
	 * searchresultspage, selectproduct is returning Productinfopage int
	 * actImageCount = productInfoPage.getProductImage();
	 * //Assert.assertEquals(actImageCount, productImageCount); }
	 */

	// taking data as dynamic search input and its validation as well from the
	// DataProvider annotation

	/*
	 * @Test public void productImageCountTest() { searchResultsPage =
	 * accPage.doSearch("Macbook"); productInfoPage =
	 * searchResultsPage.selectProduct("MacBook Pro"); // we are storing the output
	 * in the return type of the methods // doSearch is returning searchresultspage,
	 * selectproduct is returning Productinfopage int actImageCount =
	 * productInfoPage.getProductImage(); Assert.assertEquals(actImageCount, 4); }
	 */

	/*
	 * @Test public void productImageCountTestSamsung() { searchResultsPage =
	 * accPage.doSearch("samsung"); productInfoPage =
	 * searchResultsPage.selectProduct("Samsung SyncMaster 941BW"); // we are
	 * storing the output in the return type of the methods // doSearch is returning
	 * searchresultspage, selectproduct is returning Productinfopage int
	 * actImageCount = productInfoPage.getProductImage();
	 * //Assert.assertEquals(actImageCount, 1); }
	 */

	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDataMap = productInfoPage.getProductData();

		/*
		 * {Brand=Apple, Availability=Out Of Stock, Product Images=4, Product
		 * Price=$2,000.00, Product Name=MacBook Pro, Product Code=Product 18,
		 * ExtraPrice=$2,000.00, Reward Points=800}
		 */ // this is the output of Hashmap.
			// Note: Hashmap gives you random data. No order.
		//we use Hashmap in java because this way it is easier to store and retrieve the data. 

		//AAA Pattern: Arrange Act Assert 
		
		
		// Assertions: Hard Assertions and Soft Assertions.
		// One Assertion in 1 @Test is called Hard Assertion. It is advisable to have
		// only 1 hard assertions.
		// Issue with Hard Assertion is- if an assertion is failed, the Test is paused. Next assertion will not be executed. 
		// But one can have multiple soft assertions in his @Test annotation.
// to apply soft assertion, we need to create an object of Soft Assert class. 
		// when we have a similar condition to validate/assert, it is advisable to have Soft Assert and not
		// creating multiple methods to implement Hard Assertion everytime. 
		
		SoftAssert softAssert = new SoftAssert();
		
		//after using SoftAssert instead of Assert.assertEquals 
		softAssert.assertEquals(productDataMap.get("Product Name"), "MacBook Pro");
		softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDataMap.get("Product Price"), "$2,000.00");
		softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");
		
		softAssert.assertAll();// this line will give total reporting of failed and passed SoftAssert in console logs. 
		
		// Property of SoftAssert is, if any of the assertion is getting failed, it will move to next steps till completion. 

	}
}
