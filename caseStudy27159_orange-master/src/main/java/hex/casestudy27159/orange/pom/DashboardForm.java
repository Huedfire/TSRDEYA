package hex.casestudy27159.orange.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hex.casestudy27159.orange.utilities.FindElement;
import hex.casestudy27159.orange.utilities.LocatorType;

public class DashboardForm {

	
	public static WebElement AdminMenu(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "menu_admin_viewAdminModule");
		return element;
	}
	
	public static WebElement AdminMenu_UserManagementSubMenu(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "menu_admin_UserManagement");
		return element;
	}
	
	public static WebElement AdminMenu_UserManagementSubMenu_UsersOption(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "menu_admin_viewSystemUsers");
		return element;
	}

//welcome
	public static WebElement WelcomeUserMenu(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "welcome");
		return element;
	}
	
	public static WebElement Logout(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.LinkText, "Logout");
		return element;
	}
}
