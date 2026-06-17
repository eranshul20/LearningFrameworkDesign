package com.qa.opencart.utility;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {// driver value of line 11 is given to line 9
		this.driver = driver;// this.global variable = local variable
	}

	// generic method for send keys

	public void doSendKeys(By locator, String value) {
		if (value == null) {
			throw new ElementException("======Value can not be null=====");
		}
		getElement(locator).sendKeys(value);
	}

	// CharSequence with 3 dots means we can add multiple values ...
	public void doMultipleSendKeys(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
	}
	
	

	// generic method for getElement
	public WebElement getElement(By locator) {
		return driver.findElement(locator);// now the problem is- how will you give driver here at line 23
		// so we'll make a driver variable here, private in nature so that no one can
		// access it publicly
// now driver at line 9 and driver at line 23 are not linked. this will give null pointer exception due to driver at line 9
		// one can access these methods by using an object, & hence a const.. is created
		// with a parameter.
		// using a constructor of the class to give access to local
		// driver to the global driver using "this" keyword
		// Encapsulation concept is used here, we have encapsulated private driver from
		// line 9 to public driver at line 23.

		// ************ we have removed static from the getElement and doSendKeys method
		// so that, it will not be consuming CMA- commom memory allocation space in java
		// memory. if we execute 3 parallel execution of code, then this will not
		// support without non-static method

		// ****** we need to create an object of ElementUtil class to call non-static
		// methods , const.. will be called on creation of an object
		// doSendKeys, getElement methods are wrapper function

	}

	// utility for click method
	public void doClick(By locator) {// here static is removed so that we can use it with an object of ElementUtil
										// class in main method(anywhere we want to use it)
		getElement(locator).click();
	}

	// create a utility for gettext method
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	// utility for is element displayed method exception handling

	public boolean isElementDisplayed(By locator) {// here in this method lets handle the locator exception
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element not displayed on the page: " + locator);
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebElement element) {// overload method for a WebElement return type 
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element not displayed on the page: " + element);
			return false;
		}
	}
	
	
	
	// utility for is element enabled

	public boolean isElementEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			System.out.println("Element not displayed on the page: " + locator);
			return false;
		}

	}

	// utility for wait for title to be visible on the page
	public String waitForTitleIs(String expectedTitleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(expectedTitleValue));
		return driver.getTitle();
	}

	// utility for checking url contains the fractional value to validate
	public String waitForURLContains(String fractionalValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(fractionalValue));
		return driver.getCurrentUrl();

	}

	// utility for wait for element visible on the page to enter a value afterwards

	public WebElement waitElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	
	
	//utility for wait for elements presence on the screen 
	
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		
	}
	
	

}
