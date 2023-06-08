package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class Login {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.constructor of the page class
	
	public Login(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2.private By locators
	
	private By emailId = By.id("input-email");
	
	private By pwd = By.id("input-password");
	
	private By forgotpwd = By.linkText("Forgotten Password");
	
	private By loginBtn = By.xpath("//input[@value='Login']");
	
	private By footerLinkList = By.xpath("//footer//a");
	
	private By  AccountLink = By.xpath("//a[text()='Account']");
	
	private By RegisterLink = By.linkText("Register");
	
	private By loginError = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//public page actions or methods.
	@Step("getting login page title")
	public String getLoginPageTitle() {
		
		return eleUtil.waitForTitleAndCapture("Login",AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		
	 return eleUtil.waitForUrlAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	@Step("checking forgot pwd link exist on the login page")
	public Boolean isForgotPwdLinkExist() {
		
		
		return eleUtil.IsElementDisplayed(forgotpwd);
		
	}
	
    public Boolean isAccountLinkExist() {
		
		return eleUtil.IsElementDisplayed(AccountLink);
	}
	
  @Step("getting footer links")	
 public  List<String> getFooterList() {
	 
	    List<WebElement> ele = eleUtil.waitForAllElementVisibility(footerLinkList,AppConstants.MEDIUM_DEFAULT_WAIT);
		
		List<String> FooterList = new ArrayList();
		
		for(WebElement e : ele) {
			
			String text= e.getText();
			FooterList.add(text);
		}
		
		return FooterList;
	}

  @Step("login with username {0} and password {1} ")
 public AccountsPage doLogin(String username , String password) {
	 
	 
	 eleUtil.waitForElementVisibility(emailId,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
	 eleUtil.doSendKeys(pwd,password);
	 eleUtil.doClick(loginBtn);;
	 return new AccountsPage(driver);
 }

  @Step("login with wrong username {0} and password {1} ")
public boolean doLoginWithWrongCredentials(String username , String password) {
	 
	 
	 eleUtil.waitForElementVisibility(emailId,AppConstants.MEDIUM_DEFAULT_WAIT);
	 eleUtil.doSendKeys(emailId,username);
	 eleUtil.doSendKeys(pwd,password);
	 eleUtil.doClick(loginBtn);
	 String errmessage = eleUtil.doGetElementText(loginError);
	 System.out.println(errmessage);
	 if(errmessage.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
		 return true;
	 }
	 else
	 {
		 return false;
	 }
	 
	 
	 
 }
  @Step("Navigate to register page")
  public  Register navigateToRegisterPage() {
	eleUtil.doClick(RegisterLink);
	return new Register(driver);
 }
	
	
	

}
