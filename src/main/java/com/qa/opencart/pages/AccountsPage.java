package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.constructor of the page class
	
		public AccountsPage(WebDriver driver) {
			
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);
		}
		
	//2.private by locators
		
	
		By logout = By.linkText("Logout");
		By search = By.name("search");
		By transactions = By.linkText("Transactions");
		By acctheaders =  By.cssSelector("div#content h2");
		By searchButton = By.cssSelector("div#search button");
		
		
	//3.method
		public Boolean isLogoutExist() {
			
			return eleUtil.IsElementDisplayed(logout);
			
		}
		
        public Boolean isSearchExist() {
			
			return eleUtil.IsElementDisplayed(search);
			
		}
        
        public String getAccountsPageTitle() {
    		
        	return eleUtil.waitForTitleAndCapture(AppConstants.ACCOUNT_PAGE_TITLE_FRACTIONAL_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
    	}
        
        public List<String> getHeadersList() {
        	
        	
        	List<WebElement> ele = eleUtil.waitForAllElementVisibility(acctheaders,AppConstants.MEDIUM_DEFAULT_WAIT);
        	
            List<String> headersValList = new ArrayList<String>();
            
            for(WebElement e : ele) {
            	
            	String text = e.getText();
            	headersValList.add(text);
            }
            return headersValList;
        	
        }
        
        public ResultsPage doSearch(String searchTerm) {
        	
        	eleUtil.waitForElementVisibility(search, AppConstants.SHORT_DEFAULT_WAIT);
        	eleUtil.doSendKeys(search, searchTerm);
        	eleUtil.doClick(searchButton);
        	return new ResultsPage(driver);
        	
        }
		
		
	

}
