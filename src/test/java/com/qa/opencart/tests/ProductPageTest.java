package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;

public class ProductPageTest extends BaseTest{
	
	
	@BeforeClass
	public void accPageSetup() {
		
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	
	@Test
	public void productInfoTest() {
		
		
		resPage = accPage.doSearch("MacBook");
		productPage = resPage.selectProduct("MacBook");
		Map<String, String> ProductInfoMap = productPage.getProductInfo();
		System.out.println(ProductInfoMap);
		softAssert.assertEquals(ProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(ProductInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(ProductInfoMap.get("Product Code"),"Product 16");
		softAssert.assertEquals(ProductInfoMap.get("Reward Points"),"600");
		softAssert.assertEquals(ProductInfoMap.get("productprice"),"$602.00");
		softAssert.assertEquals(ProductInfoMap.get("exTaxPrice"),"$500.00");
		softAssert.assertEquals(ProductInfoMap.get("productName"),"MacBook");
		softAssert.assertAll();
	}
	
	
	
	
	

}
