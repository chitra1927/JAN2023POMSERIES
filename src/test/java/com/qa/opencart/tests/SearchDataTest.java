package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.DataProviders.ProductDataProvider;
import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.pojo.product;

public class SearchDataTest extends BaseTest {
	
	@BeforeClass
	public void searchSetup() {
		
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	
	
	@Test(dataProvider = "productData" , dataProviderClass = ProductDataProvider.class)
	public void getProductResultsCountTest(product prod) {
		
		resPage = accPage.doSearch(prod.getSearchKey());
		Assert.assertTrue(resPage.getProductResultsCount() > 0);	
	}
	
	
	@Test(dataProvider = "productData" , dataProviderClass = ProductDataProvider.class)
	public void selectProductTest(product prod) {
		
		resPage = accPage.doSearch(prod.getSearchKey());
		productPage = resPage.selectProduct(prod.getProductName());
		String ActualHeader = productPage.getProductHeader();
		System.out.println("Actual header is" +ActualHeader);
		Assert.assertEquals(ActualHeader , prod.getProductName());
	}
	
	
	@Test(dataProvider = "productData" , dataProviderClass = ProductDataProvider.class)
	public void getProductImageCountTest(product prod ) {
		resPage = accPage.doSearch(prod.getSearchKey());
		productPage = resPage.selectProduct(prod.getProductName());
		int actualProductCount =  productPage.getProductImageCount();
		System.out.println("Actual Product count" +actualProductCount);
		Assert.assertEquals(actualProductCount , prod.getProductImages());
	}

}
