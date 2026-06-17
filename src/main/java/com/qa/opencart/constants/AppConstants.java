package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	// creating constants for the expected values to be replaced in our Pages

	// here we are making these variables as static so no need to create an object
	// to call them, simply using classname.variable is enough to call them wherever
	// needed.
	// Making them final, as no one could alter them.

	public static final int DEFAULT_SHORT_WAIT = 5;
	public static final int DEFAULT_MEDIUM_WAIT = 10;
	public static final int DEFAULT_LARGE_WAIT = 20;

	public static final int DEFAULT_FOOTER_LINKS_COUNT = 15;

	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";

	public static final String ACC_PAGE_TITLE = "My Account";
	public static final String ACC_PAGE_FRACTION_URL = "route=account/account";

	public static final int ACC_PAGE_HEADER_COUNT = 4;
	public static final String USER_REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String INVALID_USER_CREDS_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
	public static final String BLANK_USER_CREDS_ERROR_MESSAGE = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";

	
	public static List<String> expectedAccPageHeadersList = Arrays.asList("My Account", "My Orders", "My Affiliate Account",
			"Newsletter");
	
	

}
