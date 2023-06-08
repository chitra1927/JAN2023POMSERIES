package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Loginl";
	
	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	
	public static final String ACCOUNT_PAGE_TITLE_FRACTIONAL_VALUE = "Account";
	
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";
	
	public static final int SHORT_DEFAULT_WAIT = 5;
	
	public static final int MEDIUM_DEFAULT_WAIT = 5;
	
	public static final List<String> ACCOUNTS_PAGE_EXPECTED_ARRAYLIST = Arrays.asList("My Account" , "My Orders" , "My Affiliate Account" , "Newsletter");
	
	public static final String LOGIN_ERROR_MESSAGE ="Warning: No match for E-Mail Address and/or Password.";
	
	public static final String USER_REG_SUCES_MESSAGE = "Your Account Has Been Created!";

	public static final String REGISTER_SHEET_NAME = "register" ;
	

}
