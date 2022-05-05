package hex.casestudy27159.orange.users;

import java.io.IOException;
import java.util.Map;

import hex.casestudy27159.orange.pom.LogInForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hex.casestudy27159.orange.pom.DashboardForm;
import hex.casestudy27159.orange.pom.UserManagementForm;
import hex.casestudy27159.orange.utilities.DriverHandler;
import hex.casestudy27159.orange.utilities.DriversEnum;
import hex.casestudy27159.orange.utilities.FindElement;
import hex.casestudy27159.orange.utilities.ReadDatasheet;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class NewUserScenarios {
	public WebDriver driver;
	private UserManagementForm user;
	private DashboardForm dashboard;
	private DriverHandler driverHandler;
	ReadDatasheet ds;
	Map<String, Map<String, String>> dataMapLogin;
	Map<String, Map<String, String>> dataMap;

	@BeforeClass
	public void setUp() throws IOException {
		ds = new ReadDatasheet();
		dataMap = ds.readExcelCsvToMap("Users.csv");
		dataMapLogin = ds.readExcelCsvToMap("Login.csv");

		driverHandler = new DriverHandler(DriversEnum.CHROME);
		driver = driverHandler.getDriver();
		String url = "https://opensource-demo.orangehrmlive.com/";
		driver.manage().window().maximize();
		FindElement.Navigate(driver, url);
		loginWithValidCredentials();
	}

	public void loginWithValidCredentials() {
		Map<String, String> valuesMap = dataMapLogin.get("Valid");
		String defaultValue = "";
		try {
			WebElement username = LogInForm.UserNameText(driver);
			FindElement.SendKeys(driver, username, valuesMap.getOrDefault("DT_USERNAME", ""));

			WebElement password = LogInForm.PasswordText(driver);
			FindElement.SendKeys(driver, password, valuesMap.getOrDefault("DT_PASSWORD", defaultValue));

			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);

			FindElement.Wait(3);
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	@AfterClass
	public void close() {
		driver.quit();
	}
	
	@Test(priority = 1) 
	public void validateUsersPage() {
		Assert.assertTrue(DashboardForm.AdminMenu(driver).isDisplayed());
	}
	
	@Test(priority = 2)
	public void addValidUsername() {
		try {
		WebElement adminMenu = DashboardForm.AdminMenu(driver);
		FindElement.mouseOver(driver, adminMenu);
		
		WebElement um = DashboardForm.AdminMenu_UserManagementSubMenu(driver);
		FindElement.mouseOver(driver, um);
		
		WebElement users = DashboardForm.AdminMenu_UserManagementSubMenu_UsersOption(driver);
		FindElement.Click(driver, users);
		
		Assert.assertTrue(UserManagementForm.AddButton(driver).isEnabled());
		
		WebElement addButton = UserManagementForm.AddButton(driver);
		FindElement.Click(driver,addButton);
		
		WebElement userRole = UserManagementForm.AddUser.UserRoleSelect(driver);
		Assert.assertTrue(userRole.isDisplayed());
		FindElement.SelectByText(driver,userRole,"ESS");
		
		WebElement empName = UserManagementForm.AddUser.EmployeeNameBox(driver);
		FindElement.SendKeys(driver, empName, "Orange Test");
		
		WebElement username = UserManagementForm.AddUser.UsernameBox(driver);
		FindElement.SendKeys(driver, username, "tester123");
		
		WebElement status = UserManagementForm.AddUser.StatusSelect(driver);
		FindElement.SelectByText(driver,status,"Enabled");
		
		WebElement pass = UserManagementForm.AddUser.PasswordBox(driver);
		FindElement.SendKeys(driver, pass, "tester123");
		
		WebElement confPass = UserManagementForm.AddUser.ConfirmPasswordBox(driver);
		FindElement.SendKeys(driver, confPass, "tester123");

		WebElement btnSave = UserManagementForm.AddUser.SaveButton(driver);
		FindElement.Click(driver, btnSave);
		
		WebElement userCreated = UserManagementForm.UserNameLinkText(driver, "tester");
		Assert.assertTrue(userCreated.isDisplayed());
		
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	
}
