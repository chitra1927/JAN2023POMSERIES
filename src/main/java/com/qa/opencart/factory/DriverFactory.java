package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameException;

public class DriverFactory {
	
	WebDriver driver;
	OptionsManager optionsManager ;
	public static String highlightElement;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);
		
		highlightElement = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		switch(browserName.toLowerCase()) {
		
		case "chrome" :
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox" :
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		    break;
		case "Edge" :
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		    break;
		case "Safari" :
			tlDriver.set(new SafariDriver());
		    break;
		default :
			System.out.println("PLEASE ENTER RIGHT BROWSER NAME" +browserName);
			throw new FrameException("NoBrowerFoundException");		
		}	
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
			
	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	public Properties initProp()  {
		
		//mvn clean install -Denv="qa"
		//mvn clean install
		
		Properties prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("RUNNING TESTCASES ON ENV" +envName);
		
		try {
			
			if(envName == null) {
				System.out.println("NO ENV IS GIVEN THEN...HENCE RUNNING IT ON QA ENV");
			   ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
			}
			else {
				System.out.println("Running test cases on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name...." + envName);
					throw new FrameException("NOVALIDENVGIVEN");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
	
			
			
		

