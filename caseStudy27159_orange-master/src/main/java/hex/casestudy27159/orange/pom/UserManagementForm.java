package hex.casestudy27159.orange.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import hex.casestudy27159.orange.utilities.FindElement;
import hex.casestudy27159.orange.utilities.LocatorType;

public class UserManagementForm {

	
	public static WebElement AddButton(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "btnAdd");
		return element;
	}
	
	public static WebElement DeleteButton(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "btnDelete");
		return element;
	}
	
	public static WebElement UsersTable(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "resultTable");
		return element;
	}

	public static WebElement UserCheckboxByUserName(WebDriver driver, String username) {
		WebElement element = null;
		String xpath ="//table[@id='resultTable']/tbody/tr/td/a[contains(.,'" + username + "')]//parent::td/parent::tr/td[1]/input";
		element = FindElement.getFindElement(driver, LocatorType.XPath, xpath);
		return element;
	}
	
	public static WebElement UserNameLinkText(WebDriver driver, String username) {
		WebElement element = null;
		String xpath ="//table[@id='resultTable']/tbody/tr/td/a[contains(.,'" + username + "')]";
		element = FindElement.getFindElement(driver, LocatorType.XPath, xpath);
		return element;
	}
	
	// Successfully deleted successBodyDelete
	public static WebElement messagesLabel(WebDriver driver) {
		WebElement element = null;
		element = FindElement.getFindElement(driver, LocatorType.Id, "successBodyDelete");
		return element;
	}
	
	public static class AddUser {
		public static WebElement UserRoleSelect(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_userType");
			return element;
		}
		
		public static WebElement EmployeeNameBox(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_employeeName_empName");
			return element;
		}
		
		public static WebElement UsernameBox(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_userName");
			return element;
		}
		
		public static WebElement StatusSelect(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_status");
			return element;
		}
		
		public static WebElement PasswordBox(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_password");
			return element;
		}
		
		public static WebElement ConfirmPasswordBox(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "systemUser_confirmPassword");
			return element;
		}
		
		public static WebElement SaveButton(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Name, "btnSave");
			return element;
		}
		
		public static WebElement CancelButton(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Name, "btnCancel");
			return element;
		}
		
		public static WebElement EditButton(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Name, "btnSave");
			return element;
		}
		
	}
	
	public static class ConfirmationModal {
		public static WebElement OKDeleteButton(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.Id, "dialogDeleteBtn");
			return element;
		}
		
		public static WebElement CancelDeleteButton(WebDriver driver) {
			WebElement element = null;
			element = FindElement.getFindElement(driver, LocatorType.cssSelector, "input.btn.reset");
			return element;
		}
	}
}
