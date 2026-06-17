package com.qa.opencart.utility;

public class StringUtils {

public static String getRandomEmails() {
	return "uiautomation" +System.currentTimeMillis()+ "@open.com";
	
	// email type to be easily fetched later from DB 
	// select * from users where emailid like '%uiautoamtion'
}


}
