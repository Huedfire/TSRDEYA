package hex.casestudy27159.orange.Login;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hex.casestudy27159.orange.pom.LogInForm;
import hex.casestudy27159.orange.pom.DashboardForm;
import hex.casestudy27159.orange.utilities.DriverHandler;
import hex.casestudy27159.orange.utilities.DriversEnum;
import hex.casestudy27159.orange.utilities.FindElement;
import hex.casestudy27159.orange.utilities.ReadDatasheet;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class Login_Scenarios {
	public WebDriver driver;
	private LogInForm loginForm;
	private DriverHandler driverHandler;
	ReadDatasheet ds;
	Map<String, Map<String, String>> dataMap;
	
	@BeforeClass
	public void setUp() throws IOException {
		ds = new ReadDatasheet();
		dataMap = ds.readExcelCsvToMap("Login.csv");
		
		driverHandler = new DriverHandler(DriversEnum.CHROME);
		driver = driverHandler.getDriver();
		String url = "https://opensource-demo.orangehrmlive.com/";
		driver.manage().window().maximize();
		FindElement.Navigate(driver, url);	
	}
	
	@AfterClass
	public void close() {
		driver.quit();
	}
	
	@Test(priority = 1) 
	public void validateLoginPage() {
		try {
			FindElement.Wait(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(LogInForm.PasswordText(driver).isDisplayed());
		Assert.assertTrue(LogInForm.UserNameText(driver).isDisplayed());
		Assert.assertTrue(LogInForm.LoginSubmit(driver).isDisplayed());
	}
	
	@Test(priority = 2)
	public void loginWithValidCredentials() {
		Map<String, String> valuesMap = dataMap.get("Valid");
		String defaultValue = "";
		try {
			WebElement username = LogInForm.UserNameText(driver);
			FindElement.SendKeys(driver, username, valuesMap.getOrDefault("DT_USERNAME", ""));
			
			WebElement password = LogInForm.PasswordText(driver);
			FindElement.SendKeys(driver, password, valuesMap.getOrDefault("DT_PASSWORD", defaultValue));
			
			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);

			FindElement.Wait(3);
			WebElement welcome = DashboardForm.WelcomeUserMenu(driver);
			Assert.assertTrue(welcome.isDisplayed());
			FindElement.Click(driver, welcome);

			FindElement.Wait(2);
			WebElement logout = DashboardForm.Logout(driver);
			FindElement.Click(driver, logout);
			FindElement.Wait(3);
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 3)
	public void loginWithInvalidCredentials() {
		try {
			Map<String, String> valuesMap = dataMap.get("Invalid");
			String defaultValue = "";
			WebElement username = LogInForm.UserNameText(driver);
			username.clear();
			FindElement.SendKeys(driver, username, valuesMap.getOrDefault("DT_USERNAME", ""));
			
			WebElement password = LogInForm.PasswordText(driver);
			password.clear();
			FindElement.SendKeys(driver, password, valuesMap.getOrDefault("DT_PASSWORD", defaultValue));
			
			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);
			
			Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), valuesMap.getOrDefault("DT_ERROR", defaultValue));
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 4)
	public void loginWithoutPassword() {
		try {
			Map<String, String> valuesMap = dataMap.get("NoPassword");
			String defaultValue = "";
			WebElement username = LogInForm.UserNameText(driver);
			username.clear();
			FindElement.SendKeys(driver, username, valuesMap.getOrDefault("DT_USERNAME", ""));

			WebElement password = LogInForm.PasswordText(driver);
			password.clear();

			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);
			
			Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), valuesMap.getOrDefault("DT_ERROR", defaultValue));
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 5)
	public void loginWithoutUsername() {
		try {
			Map<String, String> valuesMap = dataMap.get("NoUsername");
			String defaultValue = "";
			WebElement username = LogInForm.UserNameText(driver);
			username.clear();

			WebElement password = LogInForm.PasswordText(driver);
			password.clear();
			FindElement.SendKeys(driver, password, valuesMap.getOrDefault("DT_PASSWORD", defaultValue));

			FindElement.Wait(2);
			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);
			
			Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), valuesMap.getOrDefault("DT_ERROR", defaultValue));
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 6)
	public void loginWithoutCredentials() {
		try {
			Map<String, String> valuesMap = dataMap.get("NoValues");
			WebElement username = LogInForm.UserNameText(driver);
			username.clear();

			WebElement password = LogInForm.PasswordText(driver);
			password.clear();

			WebElement login = LogInForm.LoginSubmit(driver);
			FindElement.Click(driver, login);
			Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), valuesMap.getOrDefault("DT_ERROR", ""));
			} catch(Exception e) {
				System.out.println("Failure while trying to login." + e.getMessage());
				Assert.assertFalse(false);
			}
	}
}
