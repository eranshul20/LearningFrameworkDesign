package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// things we can validate on this page , use by locator and methods to be
	// validated on test class

	private final By headers = By.cssSelector("div#content h2");// headers of the page
	private final By logoutLink = By.linkText("Logout");// logout link
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");

	public List<String> getAccountPageHeaders() {
		// updating based on ElementUtil methods

		List<WebElement> headerList = eleUtil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT); // using
																													// eleUtil
																													// methods

		// List <WebElement> headerList = driver.findElements(headers);
		System.out.println("total count of headers :" + headerList.size());
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String headertext = e.getText();
			headersValueList.add(headertext);
		}
		return headersValueList;

	}

	public boolean isLogoutLinkvisible() {
		WebElement element = eleUtil.waitElementVisible(logoutLink, AppConstants.DEFAULT_LARGE_WAIT); // using eleUtil
																										// methods
		// this method is waiting for an element visible on the screen
		return eleUtil.isElementDisplayed(element);// this method is returning boolean

		// return driver.findElement(logoutLink).isDisplayed();
	}

	public SearchResultsPage doSearch(String searchkey) {
		System.out.println("Entered search key is: " + searchkey);
		WebElement searchEle = eleUtil.waitElementVisible(search, AppConstants.DEFAULT_MEDIUM_WAIT);
		searchEle.clear();// this step clears the entered searched input for another test running in
							// parallel to execute doSearch method. this is the reason we stored above
							// eleUtil in a webelement so that we can perform clear and enter searchkey
							// again
		searchEle.sendKeys(searchkey);
		eleUtil.doClick(searchIcon);
		// driver.findElement(search).sendKeys(searchkey);
		// driver.findElement(searchIcon).click();
		return new SearchResultsPage(driver);
	}

}
