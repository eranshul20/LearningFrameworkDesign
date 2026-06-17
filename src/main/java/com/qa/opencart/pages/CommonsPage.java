package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class CommonsPage {
	
	// tests for all the common elements visible on all the screens of the application
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By logo = By.cssSelector("img.img-responsive");
	private final By searchField = By.name("search");
	private final By footerLinks = By.cssSelector("footer li a");
	
	
	public boolean isLogoExists() {
		return eleUtil.isElementDisplayed(logo);
	}
	
	public boolean isSearchFieldExists() {
		return eleUtil.isElementDisplayed(searchField);
	}
	
	public List <String> isFooterLinksExists() {
	List<WebElement> footerList = eleUtil.waitForElementsVisible(footerLinks, AppConstants.DEFAULT_SHORT_WAIT);
	List <String> footerListValue = new ArrayList<String>();
		for (WebElement e : footerList) {
			String text = e.getText();
			footerListValue.add(text);
		}
		return footerListValue;
	}
	
	

}
