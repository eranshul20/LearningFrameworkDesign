package com.qa.opencart.pages;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;// every page will have its own private Webdriver and ElementUtil reference
	private ElementUtil eleUtil;

	private Map<String, String> productMap;// this is a hashmap. productMap is a reference variable for Map. 
	//Map is parent Interface of hashmap.
	

	public ProductInfoPage(WebDriver driver) {// a const.. of the page class
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// things we can validate on this page

	private final By header = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetadata = By.xpath("(//div[@id ='content']//ul[@class= 'list-unstyled'])[1]/li");// gives
																												// all 4
																												// li of
																												// product
																												// metadata
	private final By productPricedata = By.xpath("(//div[@id ='content']//ul[@class= 'list-unstyled'])[2]/li");// gives
																												// all 2
																												// li of
																												// product
																												// metadata

	public String getProductHeader() {// to check header text present on the product detail page of the selected
										// product
		String headerVal = eleUtil.waitElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product header is:" + header);
		return headerVal;
	}

	public int getProductImage() {// to check images present on the product detail page of the selected product
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("Total number of images are: " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductData() {
		productMap = new HashMap<String, String>();// random order of data in ooutput 
		//productMap = new LinkedHashMap<String, String>();// in the exact insertion order of code 
		//productMap = new TreeMap<String, String>();// sorted order with keys
		
		productMap.put("Product Name", getProductHeader());// hashmap for above methods as well to get collective dataset
		productMap.put("Product Images", String.valueOf(getProductImage()));// to convert int to String as getProductImage gives int value 
		
		getProductMetaData();// we can call below methods here as well (Encapsulation). calling private method inside a public method 
		getProductPriceData();
		// private logic is protected from external user. Only public method is exposed.
		
		System.out.println("============Product full Data===============: \n" + productMap);
		return productMap;
		
	}
	
	
	
	// checking for other attributes of product searched using Hashmap concept

	
	 //4 li of productMetadata 
	 //Brand: Apple 
	 //Product Code: Product 18 
	 //Reward Points:800 
	 //Availability: Out Of Stock
	
	// In case of using LinkedHashmap, the order of data received will be in order of sequence we have written, Not in random order 
	// Case 2: productMap = new LinkedHashMap<String, String>();

	//then the result of productMap will be:
	//{Product Name=MacBook Pro, Product Images=4, Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=Out Of Stock, Product Price=$2,000.00, ExtraPrice=$2,000.00}

		// In case of using TreeMap, the order of data will be in order of Alphabetical and uppercase then lowercase letter. 
	// Case 2: productMap = new TreeMap<String, String>();

	//{Availability=Out Of Stock, Brand=Apple, ExtraPrice=$2,000.00, Product Code=Product 18, Product Images=4, Product Name=MacBook Pro, Product Price=$2,000.00, Reward Points=800}


	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productMetadata, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total meta list =" + metaList.size());

		//productMap = new HashMap<String, String>();// it is blank [] now
//initialized productMap above for the all getproductData 
		for (WebElement e : metaList) {

			String metaData = e.getText();
			String meta[] = metaData.split(":");// split function returns array, so stored it in array
			String metaKey = meta[0].trim();// based on above regex, at 0th position, Key is there
			String metaValue = meta[1].trim();// based on above regex, on 1st position, value is there
			productMap.put(metaKey, metaValue);// now the array will be filled using metaKey and metaValue 

		}
		}

		// now for product prices, 2nd li tags information 
		
		//$2,000.00
		//Ex Tax: $2,000.00
		
		
	private void getProductPriceData() {
			List<WebElement> priceList = eleUtil.waitForElementsPresence(productPricedata, AppConstants.DEFAULT_SHORT_WAIT);
			System.out.println("Total Price list =" + priceList.size());

			//productMap = new HashMap<String, String>();// it is blank [] now
// no need to initialize the productMap again as we already done above 
			String priceValue = priceList.get(0).getText();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
		// only two values are there, so we avoided using for loop
		
		productMap.put("Product Price", priceValue);// custom key "Product Price"
		productMap.put("ExtraPrice", exTaxValue);
	}

}
