package com.qa.DataProviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.product;

public class ProductDataProvider {
	
	@DataProvider(name="productData")
	public Object[][] getProductTestData() {
		return new Object[][] {
			{new product("MacBook","MacBook Pro",4)},
			{new product("iMac","iMac",3)},
			{new product("Samsung" , "Samsung SyncMaster 941BW", 1)},
			{new product("Samsung" , "Samsung Galaxy Tab 10.1" ,7)}
		};	
		
	}
	
	
	@DataProvider
	public Object[][] getProductNameData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Samsung"}	
		};	
		
	}
	
	
	@DataProvider
	public Object[][] getProductTestData1() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iMac","iMac"},
			{"Samsung" , "Samsung SyncMaster 941BW"},
			{"Samsung" , "Samsung Galaxy Tab 10.1"}
		};	
		
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
	
	
	
	
	
	

}
