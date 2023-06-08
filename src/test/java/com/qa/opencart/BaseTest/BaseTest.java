package com.qa.opencart.BaseTest;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Login;
import com.qa.opencart.pages.Register;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.pages.productInfoPage;

public class BaseTest {
	

	WebDriver driver;
	protected Login login;
    protected AccountsPage accPage;
    protected ResultsPage resPage;
    protected productInfoPage productPage;
    protected Register registerPage;
    DriverFactory df;
    protected Properties prop;
    protected SoftAssert softAssert;
	
    @Parameters({"browser"})
	@BeforeTest
    public void setup(String browserName) {
		
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
		     prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		login = new Login(driver);
		softAssert = new SoftAssert();

	}
	
	@AfterTest
	   
	   public void TearDown() {
		   
		   driver.quit();
	}

}
