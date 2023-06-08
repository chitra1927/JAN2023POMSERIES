package com.qa.opencart.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Login;
import com.qa.opencart.utils.AppConstants;

public class AccountsTest extends BaseTest {
	
	@BeforeClass
	
	public void acctPageSetup(){
		
		accPage = login.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void getAccountsPageTitleTest() {
		
		String actltitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actltitle,AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
    public void isSearchExistTest() {
		
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Test
    public void isLogoutExistTest() {
		
		Assert.assertTrue(accPage.isLogoutExist());
	}
	
	@Test
	public void getHeadersListTest() {
		
		List<String> actualList = accPage.getHeadersList();
		List<String> expecList = Arrays.asList("My Account" , "My Orders" , "My Affiliate Account" , "Newsletter");
		
		Assert.assertEquals(actualList,AppConstants.ACCOUNTS_PAGE_EXPECTED_ARRAYLIST);
	}
		
	}
	
	
	
	
	
	

