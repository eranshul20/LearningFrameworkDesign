package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;// reference variable of DriverFactory class
	protected LoginPage loginPage;// reference variable of LoginPage class
	// ***** access modifier protected is allowing usage of the loginPage within the
	// subclass (Only Test classes can use the BaseTest methods
	// and variables this way ) and outside the package also

	// ***** public can also be used, but it is not a right concept, because a
	// public variable can be accessed by anyone

	protected AccountsPage accPage; // ref variable for Accounts page class
	protected SearchResultsPage searchResultsPage;// ref variable for SearchResultsPage class
	protected ProductInfoPage productInfoPage; // ref variable for ProductInfoPage class

	protected RegisterPage registerPage;// ref variable for RegisterPage class
	protected CommonsPage commonsPage;// ref for CommonsPage class

	@Parameters({ "browser" }) // testng annotation to support multiple browser test run in parallel.
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {// Optional is a annotation that allows user to run the
																// test from TestClass itself without running the
																// complete test suite from xml file.

		df = new DriverFactory();// object of the DriverFactory class
		try {
			prop = df.initializeProp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// initializeProp is a method can be accessed by DriverFactory obj ref df & can
									// be stored
		// in a ref variable prop just like we are doing it for driver.

		if (browserName != null) {
			prop.setProperty("browser", browserName);// code block to update browser at run time to update which browser
														// to be opened in parallel execution of tests using .xml
		}

		driver = df.initializeDriver(prop);// object of the class to use its methods
		// now driver is given to the DriverFactory , to proceed further we need to pass
		// this to driver of LoginPage to start the login process

		loginPage = new LoginPage(driver);// this session id of driver is given to const.. of LoginPage and then will
											// proceed further

		// accPage = new AccountsPage(driver);
		// Note: We are not creating object of the accountsPage here to avoid
		// unnecessary
		// creation of AccountsPage object while only LoginPage tests needed to be
		// executed. ********** Here, in the BaseTest class, we always create only
		// loginPage object
		// as it is the starting point of execution of the application under test
		// ********

		commonsPage = new CommonsPage(driver);// this will give driver ref to const.. of CommonsPage

	}

	@AfterMethod // it will be running after each @Test method
	public void attachScreenshot(ITestResult result) {

		if (!result.isSuccess()) {// not success case !
			ChainTestListener.log("=========Screenshot is taken=========");
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		} // else
			// ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");// it
			// is for every @Test result
			// either pass or fail
			// as we don't want to have screenshot for pass Test cases.
	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}
}
