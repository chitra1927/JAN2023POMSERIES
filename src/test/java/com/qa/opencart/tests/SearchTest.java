package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;

public class SearchTest extends BaseTest{
	
	
	@BeforeClass
	public void searchSetup() {
		
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	@DataProvider
	public Object[][] getProductNameData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Samsung"}	
		};	
		
	}
	
	
	@Test(dataProvider = "getProductNameData")
	public void getProductResultsCountTest(String searchKey) {
		
		resPage = accPage.doSearch(searchKey);
		Assert.assertTrue(resPage.getProductResultsCount() > 0);	
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iMac","iMac"},
			{"Samsung" , "Samsung SyncMaster 941BW"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}
		};	
		
	}
	@Test(dataProvider = "getProductTestData")
	public void selectProductTest(String searchKey , String productName) {
		
		resPage = accPage.doSearch(searchKey);
		productPage = resPage.selectProduct(productName);
		String ActualHeader = productPage.getProductHeader();
		System.out.println("Actual header is" +ActualHeader);
		Assert.assertEquals(ActualHeader , productName);
	}
	
	@DataProvider
	public Object[][] getImageCountTestData() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Samsung" , "Samsung SyncMaster 941BW", 1},
			{"Samsung" , "Samsung Galaxy Tab 10.1" ,7}
		};	
		
	}
	
	@Test(dataProvider = "getImageCountTestData")
	public void getProductImageCountTest(String searchKey ,String productName , int productImages ) {
		resPage = accPage.doSearch(searchKey);
		productPage = resPage.selectProduct(productName);
		int actualProductCount =  productPage.getProductImageCount();
		System.out.println("Actual Product count" +actualProductCount);
		Assert.assertEquals(actualProductCount , productImages);
	}
	
	
	
	
	

}
