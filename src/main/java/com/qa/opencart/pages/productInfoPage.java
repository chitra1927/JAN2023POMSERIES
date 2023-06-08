package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public productInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//private By locators
	private By productHeader = By.cssSelector("div#content h1");
	private By ProductImage = By.cssSelector("ul.thumbnails li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.xpath("//input[@id='input-quantity']");
	private By addToCart = By.xpath("//button[@id='button-cart']");
	private Map<String , String> ProductInfoMap;
	
	public Map<String, String> getProductInfo() {
		
		ProductInfoMap = new TreeMap<String , String>();
		
		//treemap -sorted order
		//{Availability=In Stock, Brand=Apple, Product Code=Product 16, Reward Points=600, exTaxPrice=$500.00, productName=MacBook, productprice=$602.00}
		//Linked hashmap the order we insert the data.
		//{Brand=Apple, Product Code=Product 16, Reward Points=600, Availability=In Stock, productprice=$602.00, exTaxPrice=$500.00, productName=MacBook}
		//ProductInfoMap = new HashMap<String , String>();
		// hash map : ramdom order
		// {Brand=Apple, Availability=In Stock, Product Code=Product 16, Reward Points=600, productprice=$602.00, exTaxPrice=$500.00, productName=MacBook}
		
		
		getProductMetaData();
		getProductPriceData();
		ProductInfoMap.put("productName" , getProductHeader());
		return ProductInfoMap;
		
	}
	
	
	public String getProductHeader() {
		
		return eleUtil.doGetElementText(productHeader);
		
	}
	
	public int getProductImageCount() {
		
		return eleUtil.waitForAllElementVisibility(ProductImage, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		
	}
	
	public void getProductMetaData() {
		
	List<WebElement> metaList = eleUtil.getElements(productMetaData);
	for(WebElement e : metaList) {
		
		
		String MetaText = e.getText();
		String MetaInfo[] = MetaText.split(":");
		String key=MetaInfo[0].trim();
		String value=MetaInfo[1].trim();
		ProductInfoMap.put(key, value);	
	}	
	}
	
	public void getProductPriceData() {
		
		List<WebElement> priceList= eleUtil.getElements(productPriceData);	
		
		String priceValue = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		String exTaxPriceValue = exTaxPrice.split(":")[1].trim();
		ProductInfoMap.put("productprice" , priceValue);
		ProductInfoMap.put("exTaxPrice" , exTaxPriceValue);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
