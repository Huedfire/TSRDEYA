package hex.casestudy27159.orange.utilities;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FindElement {
	static WebElement element;
	static Actions action;
	
	public static WebElement getFindElement(WebDriver driver, LocatorType LocatorType, String LocatorValue){
		
		// Compare the locator type
		switch(LocatorType){
			case Id:
				element = driver.findElement(By.id(LocatorValue));
			break;
			
			case Name:
				element = driver.findElement(By.name(LocatorValue));
				break;
				
			case XPath:
				element = driver.findElement(By.xpath(LocatorValue));
				break;
			
			case cssSelector:
				element = driver.findElement(By.cssSelector(LocatorValue));
				break;
				
			case className:
				element = driver.findElement(By.className(LocatorValue));
				break;
				
			case Tag:
				element = driver.findElement(By.tagName(LocatorValue));
				break;
			
			case LinkText:
				element = driver.findElement(By.linkText(LocatorValue));
				break;
				
			case PartialLinkText:
				element = driver.findElement(By.partialLinkText(LocatorValue));
				break;
			
		}
		
		
		return element;
	}
	
	
	public static void Wait(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000); // this receives miliseconds
	}
	
	//public static WebElement waitForElementPresence(WebDriver driver, final By by, int waitInterval) {
		//WebDriverWait waiter = new WebDriverWait(driver, waitInterval);
		//waiter.until(ExpectedConditions.presenceOfElementLocated(by));
		
		//WebElement elementPresence = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.presenceOfElementLocated(by)));
		//return element;
	//}
	
	public static WebDriver Navigate(WebDriver driver, String url){
		String page = null;
		try{
			driver.navigate().to(url);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return driver;
	}
	
	public static void Click(WebDriver driver, WebElement element){
		
		try{
			if(element.isDisplayed()){
				element.click();
			}		
				
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	public static void SendKeys(WebDriver driver, WebElement element, String inputText){
		
		try{
			if(element.isEnabled()){
				element.sendKeys(inputText);
			}		
				
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void SelectByText(WebDriver driver, WebElement element, String text){
		boolean found=false;
		try{
			if(element.isEnabled()){
				Select select = new Select(element);
				for(WebElement el : select.getOptions()) {
					if(el.getText().contains(text)) {
						el.click();
						found = true;
						break;
					}
				}
		        //select.getOptions().parallelStream().filter(option -> option.getAttribute("textContent").toLowerCase().contains(text.toLowerCase()))
		        //        .findFirst().ifPresent(option -> select.selectByVisibleText(option.getAttribute("textContent")));
		    }	
				
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			if (!found)
				System.out.println("Element not selected with text: " + text);
		}
	}
	
	
	public static void SwitchToWindows(WebDriver driver) {
		String currWin;
		currWin = driver.getWindowHandle();
		System.out.println(currWin);
		Set<String> handles = driver.getWindowHandles();
		String windowChild;
		handles.remove(currWin);
		
		for(String winHandle :handles){

			if(winHandle != currWin){
			 windowChild = winHandle;
			driver.switchTo().window(windowChild);
			System.out.println("Current window : " + windowChild);
			}
		}
	}
	
	
	public static WebElement SwitchToFrame(WebDriver driver, WebElement element) {
		
		if(element != null) {
			driver.switchTo().frame(element);
		}
		
		return element;
	}
	
	public static WebDriver SwitchToDefaultContent(WebDriver driver) {
		try 
		{
			driver.switchTo().defaultContent();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return driver;
	}
	
	
	public static Actions mouseOver(WebDriver driver, WebElement element) {
		try {
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return action;
	}
	
	public static Actions mouseOverandClick(WebDriver driver, WebElement element, WebElement elementToClick) {
		
		action = new Actions(driver);
		action.moveToElement(element).click(elementToClick).perform();
		
		return action;
	}
	
	
	public static Actions DragandDrop(WebDriver driver, WebElement element, WebElement otherElement) {
		
		action = new Actions(driver);
		action.clickAndHold(element)
		.release(otherElement)
		.build();
		
		return action;
	}
	
	// More actions can be added
	public static Actions ActionsHanlder(WebDriver driver, WebElement element, ActionsEnum actionName) {
		
		action = new Actions(driver);
		
		switch(actionName) {
		
		case DoubleClick:
			action.moveToElement(element).doubleClick().perform();
			break;
		}
		
		
		return action;
	}
	
	
	public static void scroll(WebDriver driver,WebElement element, String value) {
		
		try 
		{
			if(element != null) {
				((JavascriptExecutor)driver).executeScript("window.scrollBy(" + value + ");", element);
			}else {
				((JavascriptExecutor)driver).executeScript("window.scrollBy(" + value + ");");
			}
		
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	
	
	public static void AcceptAlert(WebDriver driver) {
		try 
		{
			driver.switchTo().alert().accept();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void DissmissAlert(WebDriver driver) {
		
		try 
		{
			driver.switchTo().alert().dismiss();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void AcceptalertifExist(WebDriver driver) {

		try {
			if(isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String GetAlertText(WebDriver driver) {
		String result = "";
		try 
		{
			result = driver.switchTo().alert().getText();
			
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	
	public static boolean isAlertPresent(WebDriver driver) {
		try 
		{
			driver.switchTo().alert();
			return true;
			
		}catch(Exception ex) {
			return false;
		}
	}
	
	public static boolean isAttributePresent(WebElement element, String attribute) {
		
		Boolean result = false;
		
		try 
		{
			String value = element.getAttribute(attribute);
			if(value != null) {
				return true;
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return result;
	}
}
