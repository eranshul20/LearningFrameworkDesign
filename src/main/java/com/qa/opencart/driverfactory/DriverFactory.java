package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	/**
	 * this method is initializing driver on the basis of browser
	 * 
	 * @param browserName
	 * @return it returns driver
	 */

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	// a concept of running/accessing driver across different classes which can't
	// use driver as not a static method.

	private static final Logger log = LogManager.getLogger(DriverFactory.class);// code to support log4j logging

	// using OptionsManager class reference to use incognito and headless mode for
	// browser options
	public OptionsManager optionsManager;// reference of OptionsManager Class

	public WebDriver initializeDriver(Properties prop) {
		String browserName = prop.getProperty("browser");// we are providing prop instead of a sending a String
															// browserName as initializeDriver is taking prop in
															// basetest.
		// storing it in a String variable named- browserName
		// System.out.println("Browser name is :" + browserName);
//using log reference 

		log.info("browser name is :" + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

			break;

		default:
			// System.out.println(AppError.INVALID_BROWSER_NAME + " : " + browserName);//
			// used a static variable with a
			// class name to get the error
			// message
			// using log refrence to use this default print statement
			log.error(AppError.INVALID_BROWSER_NAME + " : " + browserName);

			throw new FrameworkException("=======Invalid browser is passed=======");
		}
// if a correct browser is passed, the switch case is over, it will move further to the next steps of program file 
		getDriver().manage().deleteAllCookies();// delete all the cookies on the browser opened
		getDriver().manage().window().fullscreen();// full screen the window
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// instead we can write below to fetch config property

		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}
	// this is used to get local copy of driver any time
	// Now we don't have to create object of class DriverFactory to call driver.
	// also, diff threads(users) will have their own local copy of driver in
	// runtime, hence it will not hinder the driver availability for large set of
	// test cases.

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// writing code to read config properties to remove hard-coded dependency on URL
	// under test, login details
	/**
	 * this method is initializing prop with properties file
	 * 
	 * @return
	 * @throws Exception 
	 */

	// utilizing prop for diff environment setup in running tests through maven
	// mvn clean install -Denv = "qa/stage/dev"

	public Properties initializeProp() throws Exception {
		prop = new Properties();// Properties is a Java class initialize above using prop keyword
		FileInputStream ip = null;
		// using System class to
		String envName = System.getProperty("env");
		log.info("Environment name is :" + envName);

		
		try {
		if (envName == null) {
			log.info("No environemnt is passed hence running tests on QA env by default");
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		} else {

			switch (envName.trim().toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
				default:
					log.error("Environment value is wrong " + envName);
					throw new Exception ("=========Invalid Environment=======");


		}}} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block for config file
			e.printStackTrace();
		}
		// FileInputStream is a java class to make connection with the config file
		// we will provide config path in the FileInputStream const..
		// ./ is used to locate current project directory.
		catch (IOException e) {
			// TODO Auto-generated catch block for loading prop.load(ip);
			e.printStackTrace();
			// we can have two catch blocks with 1 try block.
		}
		
		try {
		prop.load(ip);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}

	// taking screenshot methods using Selenium methods

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// stored in temp directory
		return srcFile;
		// TakesScreenshot is an interface and getScreenshotAs is a method of it
	}

	// TakesScreenshot is an interface that has getScreenshotAs method.
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// stored in temp directory
	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// stored in temp directory

	}

}
