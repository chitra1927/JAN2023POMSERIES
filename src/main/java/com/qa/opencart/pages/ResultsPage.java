package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1.constructor of the page class
	
    public ResultsPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
    
    //private BY locators 
    
    private By ResultsProduct = By.cssSelector("div.product-layout.product-grid");
    
    
    //page actions
    
    public String getResultsPageTitle(String searchKey) {
    	
    	return eleUtil.waitForTitleAndCapture(searchKey, 5);
    	
    }
    
    public int getProductResultsCount() {
    	
    	int resultCount = eleUtil.waitForAllElementVisibility(ResultsProduct, 10).size();
    	return resultCount;
    	
    }
    
    
    
    public productInfoPage selectProduct(String productName) {
    	
    	By productLocator = By.linkText(productName);
    	eleUtil.doClick(productLocator);
    	return new productInfoPage(driver);
    }
    
    
    

}
