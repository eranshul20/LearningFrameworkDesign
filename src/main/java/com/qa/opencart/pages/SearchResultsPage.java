package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class SearchResultsPage {// every page will have its own private Webdriver and ElementUtil reference

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {// a const.. of the page class 
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	//things we can validate on this page , use by locator and methods to be validated on test class 

	
	private final By searchResults = By.cssSelector("div.product-thumb"); // css selector for 3 macbook searched results
	private final By resultHeader = By.cssSelector("div#content h1"); // css selector for resultHeader keyword coming on
																		// the top of the result page

	
	
	public int getSearchRsultsCount() {
		int resultCount = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		// it will give the size count of the search results
		System.out.println("The result count is :" + resultCount);
		return resultCount; // every page class method "return" something so that we can validate that in
							// the Test method.

	}

	public String getResultsHeaderValue() {
		String header = eleUtil.doElementGetText(resultHeader);
		System.out.println("The result header is :" + header);
		return header;

	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("The product name is: " + productName);
		// By product = By.linkText(productName);// here we are supplying product name
		// of the product to be clicking using
		// By locator approach
		// this is a dynamic locator, not final!

		// eleUtil.doClick(product);
		// or
		// it can be written as
		eleUtil.doClick(By.linkText(productName));// instead of giving By locator reference, complete By locator path is
													// pasted
		// as soon as we are clicking on it, we are redirecting to a new page, hence it will return a new page class
		return new ProductInfoPage(driver);//Page chaining model 
	}

}
