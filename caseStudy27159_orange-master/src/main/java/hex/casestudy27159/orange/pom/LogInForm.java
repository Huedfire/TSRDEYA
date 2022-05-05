package hex.casestudy27159.orange.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hex.casestudy27159.orange.utilities.FindElement;
import hex.casestudy27159.orange.utilities.LocatorType;

public class LogInForm {

	
	public static WebElement UserNameText(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "txtUsername");
		return element;
	}
	
	public static WebElement PasswordText(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Name, "txtPassword");
		return element;
	}
	
	public static WebElement LoginSubmit(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Name, "Submit");
		return element;
	}
	
	
	public static WebElement ForgotPasswordLink(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "forgotPasswordLink");
		return element;
	}
	
	public static WebElement ErrorLabel(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "spanMessage");
		return element;
	}
}
