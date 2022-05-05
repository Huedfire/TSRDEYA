package hex.casestudy27159.orange.users;

import java.io.IOException;
import java.util.Map;

import hex.casestudy27159.orange.pom.LogInForm;
import org.openqa.selenium.By;
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

public class UpdateScenarios {
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
	public void editExistentUser() {
		try {
		WebElement adminMenu = DashboardForm.AdminMenu(driver);
		FindElement.mouseOver(driver, adminMenu);
		
		WebElement um = DashboardForm.AdminMenu_UserManagementSubMenu(driver);
		FindElement.mouseOver(driver, um);
		
		WebElement users = DashboardForm.AdminMenu_UserManagementSubMenu_UsersOption(driver);
		FindElement.Click(driver, users);
		
		Assert.assertTrue(UserManagementForm.AddButton(driver).isEnabled());
		
		WebElement user = UserManagementForm.UserNameLinkText(driver, "Alice.Duval");
		Assert.assertTrue(user.isDisplayed());
		
		if(user.isDisplayed()) {
			FindElement.Click(driver, user);
			
			WebElement editButton = UserManagementForm.AddUser.EditButton(driver);
			FindElement.Click(driver,editButton);
			
			WebElement status = UserManagementForm.AddUser.StatusSelect(driver);
			FindElement.SelectByText(driver,status,"Disabled");
			
			WebElement btnSave = UserManagementForm.AddUser.SaveButton(driver);
			FindElement.Click(driver, btnSave);
			
			WebElement userUpdated = UserManagementForm.UserNameLinkText(driver, "Alice.Duval");
			userUpdated.findElement(By.xpath("//ancestor::tr/td[5]"));
			Assert.assertTrue(userUpdated.getText().equalsIgnoreCase("disabled"));
		} 
		
		
		
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	
}
