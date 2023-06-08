package com.qa.opencart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.pages.Login;
import com.qa.opencart.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 : Login page Design")
@Story("US 101: design login page for open cart app with title, url, forgot pwd links, user is able to login")

public class LoginTest extends BaseTest {
	
	@Severity(SeverityLevel.MINOR)
	@Description("checking login page title test...")
	@Feature("title test")
	@Test
	public void LoginPageTitleTest() {
		
		
		String actltitle = login.getLoginPageTitle();
		Assert.assertEquals(actltitle,AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("login page url test")
	@Feature("ur test")
	@Test
	public void LoginPageURLTest() {
		
		String URL = login.getLoginPageURL();
		Assert.assertTrue(URL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("checking forgot pwd link test...")
	@Feature("forgot pwd test")
	@Test
	public void ForgotPwdLinkExistTest() {
		
		Assert.assertTrue(login.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("checking user is able to login with correct username/password test...")
	@Feature("login test")
	@Test
	public void LoginTest()  {
		
		accPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Assert.assertTrue(login.isAccountLinkExist());
		
	}
		
	

}
