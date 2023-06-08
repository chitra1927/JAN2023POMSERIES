package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.frameworkexception.FrameException;

public class ElementUtil {
	
	private WebDriver driver;
	
	private final int DEFAULT_TIMEOUT=5;
	
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);

	}
	
	
    public  void doSendKeys(By locator, String value) {
    	if(value == null) {
    		
    		System.out.println("null values are not allowed");
    		throw new FrameException("VALUECANNOTBENULL");
    	}
    	doClear(locator);
		getElement(locator).sendKeys(value);
		
	}
    
    public WebElement getElement(By locator,int timeout) {
    	return waitForElementVisibility(locator , timeout);
    }
	
	public  WebElement getElement(By locator) {
		
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		}
		catch(NoSuchElementException e){
			
			System.out.println("ENTERED ELEMENT IS NOT FOUND USING LOCATOR" +locator);
			element = waitForElementVisibility(locator , DEFAULT_TIMEOUT);
		}
		
		if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
			jsUtil.flash(element);
		}
		
		return element;
		
	}
	
	public List<WebElement> getElements(By locator){
		
		return driver.findElements(locator);
	}
	
	
	public void doClear(By locator) {
		
		getElement(locator).clear();
	}
	
	
	
	public void doClick(By locator) {
		
		getElement(locator).click();
	}
	
    public void doClick(By locator , int timeout) {
		
    	checkElementClickable(locator , timeout).click();
	}
	
	
	public String doGetElementText(By locator) {
		
		return getElement(locator).getText();
	}
	
	public boolean doIsElementDisplayed(By locator) {
		
		
		return getElement(locator).isDisplayed();	
	}
	
     public boolean IsElementDisplayed(By locator) {
		
		
		List<WebElement> ele =  getElements(locator);
		if(ele.size()>0) {
			System.out.println("Eement is present");
			return true;
		}
		else
		{
			System.out.println("Eement is not present");
			return false;
		}
	}
	
	public String dogetAttributeValue(By locator, String attrname) {
		   
		   return getElement(locator).getAttribute(attrname);
	   }
	
	public void doSearch(String searchKey, By searchLocator, By suggestions ,String suggName) throws InterruptedException {
		
		   doSendKeys(searchLocator,searchKey);
		   Thread.sleep(3000);
		   
		   List<WebElement> sugg = getElements(suggestions);
		   System.out.println("TOTAL SUGGESTIONS" +sugg.size());
		   
		  if (sugg.size()>0) {
			  
		         for(WebElement e : sugg) {
			   
			         String text = e.getText();
			         if(text.length()>0) {
				          System.out.println(text);
				          if(text.contains(suggName)) 
				          {
					           e.click();
					           break;
				          }   
			        }
			        else
			        {
				        System.out.println("BLANK VALUES");
				        break;
			        }  
			   
		        }
		  }
		  else
		  {
			  System.out.println("No search suggestions found");
		  } 
		}
	
	//*********************Drop Down utils ************************//
	
    public  void doSelectDropDownByIndex(By locator , int index){
		
	    Select select = new Select(getElement(locator));
	    
	    select.selectByIndex(index);
	}
	
   public  void doSelectDropDownByVisibleText(By locator , String text){
		
	    Select select = new Select(getElement(locator));
	    
	    select.selectByVisibleText(text);
	}
   
   public void doSelectDropDownByValue(By locator , String val) {
		
	    Select select = new Select(getElement(locator));
	    
	    select.selectByValue(val);
	}
	
   public int getAllDropDownValueCount(By locator) {
   	
   	return getAllDropDownOptions(locator).size();
   }
	
	public List<String> getAllDropDownOptions(By locator) {
		
	     Select select = new Select(getElement(locator));
	     
	     List<WebElement> OptionsList = select.getOptions();
	     
	     List<String> optionsValueList = new ArrayList<String>();
	     
	     System.out.println("SIZE OF OPTIONLIST IS" + OptionsList.size());
	     
	     for(WebElement e : OptionsList) {
	    	 
	    	 String text = e.getText();
	    	 System.out.println(text);
	    	 optionsValueList.add(text);
	     }	
	     
	     return optionsValueList;
	}
	
	public boolean doSelectDropDownValue(By locator , String dropDownvalue) {
		
		 boolean flag = false;
		 
	     Select select = new Select(getElement(locator));
	     
	     List<WebElement> OptionsList = select.getOptions();
	     
	     System.out.println("SIZE OF OPTIONLIST IS" + OptionsList.size());
	     
	     for(WebElement e : OptionsList) {
	    	 String text = e.getText();
	    	 System.out.println(text);
	    	 if(text.equals(dropDownvalue)) {
	    		 flag=true;
	    		 e.click();
	    		 break;
	    	 } 
	     }	
	     
	     if(flag= false) {
	    	 System.out.println("DROP DOWN VALUE IS NOT PRESENT");
	     }
	     
	     return flag;

	}
	
	//******************Action class utils ****************************************
	
    public void doActionsSendKeys(By locator , String value) {
		
		Actions act = new Actions(driver);
		
		act.sendKeys(getElement(locator), value).build().perform();
	}
	
	public void doActionsClick(By locator) {
		
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	
    public void doActionsClick(By locator , int timeout) {
		
		Actions act = new Actions(driver);
		act.click(checkElementClickable(locator,timeout)).build().perform();
	}
	
	
    public  void DragAndDrop(By sourceLocator , By TargetLocator) {
		
        Actions action = new Actions(driver);
		
		action.dragAndDrop(getElement(sourceLocator), getElement(TargetLocator)).build().perform();
		
	}
    
    
    public  void doContextClick(By Locator) {
		
        Actions action = new Actions(driver);
		
		action.contextClick(getElement(Locator)).build().perform();
		
	}
    
     public  void doMoveElement(By Locator) {
		
        Actions action = new Actions(driver);
		
		action.moveToElement(getElement(Locator)).build().perform();
		
	}
    
   
    public void handleTwoLevelMenu(By ParentMenu,By ChildMenu) throws InterruptedException {
		
		WebElement Menu = getElement(ParentMenu);
		
		Actions act = new Actions(driver);
		
		act.moveToElement(Menu).build().perform();	
		
		Thread.sleep(2000);
		
		doClick(ChildMenu);
		
	}
    
    
    public void handleTwoLevelMenu(By ParentMenu, String ChildMenuLinkText) throws InterruptedException {
		
		WebElement Menu = getElement(ParentMenu);
		
		Actions act = new Actions(driver);
		
		act.moveToElement(Menu).build().perform();	
		
		Thread.sleep(2000);
		
		doClick(By.linkText(ChildMenuLinkText));
		
	}
	
	
    public  void multiLevelMenuChildMenuHandle(String level1LinkText, String level2LinkText , String level3LinkText , String level4LinkText) throws InterruptedException {
    	
    	WebElement parentMenu =  getElement(By.linkText(level1LinkText));
		
		Actions act = new Actions(driver);
		
		act.moveToElement(parentMenu).build().perform();
		
		Thread.sleep(1500);
		
        WebElement level2 =  getElement(By.linkText(level2LinkText));
		
		act.moveToElement(level2).build().perform();
		
		Thread.sleep(1500);
		
        WebElement level3=  getElement(By.linkText(level3LinkText));
		
		act.moveToElement(level3).build().perform();
		
		Thread.sleep(1500);
		
        doClick(By.linkText(level4LinkText));	
    	
    	
    }
    
    //**********************wait utils************************************************
    
    /**An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on page.
	 * @param loctor
	 * @param timeout
	 * @return
	 */
	
	public WebElement waitForElementPresence(By loctor, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(loctor));
	}
	
   
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	
	
    public WebElement waitForElementVisibility(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
    
    
    /**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	
    public List<WebElement> waitForAllElementVisibility(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
    
   public List<WebElement> waitForAllElementVisibility(By locator, int timeout , int timeinterval) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout),Duration.ofSeconds(timeinterval));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
   
   
   public List<WebElement> waitForAllElementVisibilityWithFluentWait(By locator, int timeout , int timeinterval) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery((Duration.ofMillis(1000)))
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class)
				.withMessage("time out and no element found");
		
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
    
   public List<WebElement> waitForAllElementPresenceWithFluentWait(By locator, int timeout , int timeinterval) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery((Duration.ofMillis(1000)))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.withMessage("time out and no element found");
		
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
    
    
    
    /**
     * An expectation for checking that there is at least one element present on a web page.
     * @param locator
     * @param timeout
     * @return
     */
    
    
   public List<WebElement> waitForAllElementpresence(By locator, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
   
   /**
    * An expectation for checking an element is visible and enabled such that you can click it.
    * @param locator
    * @param timeout
    */
   
   public void clickElementWhenReady(By locator, int timeout) {
	
	   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	   wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
	   
   }
   
   public WebElement checkElementClickable(By locator, int timeout) {
		
	   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
	   return wait.until(ExpectedConditions.elementToBeClickable(locator));
	   
   }
   
   public Alert waitForAlertJsPopUp(int timeout , int timeinterval) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery((Duration.ofMillis(1000)))
				.ignoring(NoAlertPresentException.class)
				.withMessage("time out and no element found");
		
		return wait.until(ExpectedConditions.alertIsPresent());
	}
   
   
   public  Alert waitForAlertJsPopUp(int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));	
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
  public  String AlertJsGetText(int timeout) {
		
		return waitForAlertJsPopUp(timeout).getText();
	}
  
  public  void AlertJsAccept(int timeout) {
		
		waitForAlertJsPopUp(timeout).accept();
	}
  
  public  void AlertJsDismiss(int timeout) {
		
		waitForAlertJsPopUp(timeout).dismiss();
	}
  
  public  void AlertJsEnterValue(String value ,int timeout) {
		
		waitForAlertJsPopUp(timeout).sendKeys(value);
	}
  
  public  String waitForTitleAndCapture(String titleFragment , int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));	
		
		if(wait.until(ExpectedConditions.titleContains(titleFragment)))
			{
			String title = driver.getTitle();
			return title;
			
			}
		else {
			return null;
		}
		
		
	}
	
  
  public  String waitForFullTitleAndCapture(String titleValue , int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));	
		
		if(wait.until(ExpectedConditions.titleIs(titleValue)))
			{
			String title = driver.getTitle();
			return title;
			
			}
		else {
			return null;
		}
		
		
	}
  
  public String waitForUrlAndCapture(String urlFragment , int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));	
		
		if(wait.until(ExpectedConditions.urlContains(urlFragment)))
			{
			String URL = driver.getCurrentUrl();
			return URL;
			
			}
		else {
			return null;
		}
		
		
	}
  
 public  String waitForFullUrlAndCapture(String FullUrl , int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));	
		
		if(wait.until(ExpectedConditions.urlToBe(FullUrl)))
			{
			String URL = driver.getCurrentUrl();
			return URL;
			
			}
		else {
			return null;
		}
		
		
	}
 
 public void waitForFrameAndSwitchToItByIdOrNameWithFluentWait(String frameIdOrName ,int timeout , int timeinterval) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery((Duration.ofMillis(1000)))
				.ignoring(NoSuchFrameException.class)
				.withMessage("time out and no element found");
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
	}
 
 
 
 public  void waitForFrameAndSwitchToItByIdOrName(String frameIdOrName ,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
	}
 
 public  void waitForFrameAndSwitchToItByIndex(int index ,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
 
 public void waitForFrameAndSwitchToItByLoctor(By locator,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
 
 public void waitForFrameAndSwitchToItByLoctor(WebElement FrameElement ,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
	}
 
 
 
 
 
    
    
    
    
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
