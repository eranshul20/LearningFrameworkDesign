package com.qa.opencart.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// this class is to give us the ability to run our code in incognito and
	// headless mode which is defined in config.properties.
	// in headless mode, the browser would not open but tests will execute. 
	
	//this is an independent class that we will use in DriverFactory class to use co,fo,eo object references. 
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	//for Chrome browser 
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))){//this is giving boolean true, parse it into string 
		co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){//this is giving boolean true, parse it into string 
			co.addArguments("--incognito");
			}
		
		return co;
	}

	//for Firefox browser
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))){//this is giving boolean true, parse it into string 
		fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){//this is giving boolean true, parse it into string 
			fo.addArguments("--incognito");
			}
		
		return fo;
	}
	//for Edge browser 
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))){//this is giving boolean true, parse it into string 
		eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("inPrivate"))){//this is giving boolean true, parse it into string 
			eo.addArguments("--inPrivate");
			}
		
		return eo;
	}
	
	
	
	
}
