package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
		public Object[][] inCorrectLoginTestData(){
		      return new Object[][] {
		    	  
		    	  {"veera@gmail.com" , "fffff"},
		    	  {"chitra@gmail.com" , "dddd"},
		    	  {"vvvv@gmai.com" , "eeee"},
		    	  { " " , " "}
		    	  
		      };
		    		  
		
	}
	
	@Test(dataProvider = "inCorrectLoginTestData")
	public void doLoginWithWrongCredentials(String username , String password) {
		
		Assert.assertTrue(login.doLoginWithWrongCredentials(username,password));
	}

}
