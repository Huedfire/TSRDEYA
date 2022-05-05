package hex.casestudy27159.orange;

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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class Login_Scenarios_NoNG {
	public static WebDriver driver;
	private LogInForm loginForm;
	private static DriverHandler driverHandler;
	static ReadDatasheet ds;
	static Map<String, Map<String, String>> dataMap;
	
	public static void main(String[] args) {
		try {
			
			setUp();
			validateLoginPage();
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@BeforeClass
	public static void setUp() throws IOException {
		ds = new ReadDatasheet();
		dataMap = ds.readExcelCsvToMap("Login.csv");
		Map<String, String> valuesMap = dataMap.get("Valid");
		
		String username = valuesMap.get("DT_USERNAME");
		System.out.print(username);
		
		driverHandler = new DriverHandler(DriversEnum.CHROME);
		driver = driverHandler.getDriver();
		String url = "https://opensource-demo.orangehrmlive.com/";
		driver.manage().window().maximize();
		FindElement.Navigate(driver, url);	
	}
	
	@AfterClass
	public static void close() {
		driver.quit();
	}
	
	@Test(priority = 1) 
	public static void validateLoginPage() {
		Assert.assertTrue(LogInForm.PasswordText(driver).isDisplayed());
		Assert.assertTrue(LogInForm.UserNameText(driver).isDisplayed());
		Assert.assertTrue(LogInForm.LoginSubmit(driver).isDisplayed());
	}
	
	@Test(priority = 2)
	public void loginWithValidCredentials() {
		try {
		WebElement username = LogInForm.PasswordText(driver);
		FindElement.SendKeys(driver, username, "Admin");
		
		WebElement password = LogInForm.UserNameText(driver);
		FindElement.SendKeys(driver, password, "admin123");
		
		WebElement login = LogInForm.LoginSubmit(driver);
		FindElement.Click(driver, login);
		
		WebElement welcome = DashboardForm.WelcomeUserMenu(driver);
		Assert.assertTrue(welcome.isDisplayed());
		FindElement.Click(driver, welcome);
		
		WebElement logout = DashboardForm.Logout(driver);
		FindElement.Click(driver, logout);
		
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 3)
	public void loginWithInvalidCredentials() {
		try {
		WebElement username = LogInForm.PasswordText(driver);
		FindElement.SendKeys(driver, username, "asda");
		
		WebElement password = LogInForm.UserNameText(driver);
		FindElement.SendKeys(driver, password, "admasin123");
		
		WebElement login = LogInForm.LoginSubmit(driver);
		FindElement.Click(driver, login);
		
		Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), "Invalid credentials");
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 4)
	public void loginWithoutPassword() {
		try {
		WebElement username = LogInForm.PasswordText(driver);
		FindElement.SendKeys(driver, username, "Admin");
				
		WebElement login = LogInForm.LoginSubmit(driver);
		FindElement.Click(driver, login);
		
		Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), "Password cannot be empty");
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 5)
	public void loginWithoutUsername() {
		try {
		
		WebElement password = LogInForm.UserNameText(driver);
		FindElement.SendKeys(driver, password, "admin123");
		
		WebElement login = LogInForm.LoginSubmit(driver);
		FindElement.Click(driver, login);
		
		Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), "Username cannot be empty");
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
	
	@Test(priority = 6)
	public void loginWithoutCredentials() {
		try {
		
		WebElement login = LogInForm.LoginSubmit(driver);
		FindElement.Click(driver, login);
		Assert.assertTrue(LogInForm.ErrorLabel(driver).isDisplayed(), "Username cannot be empty");
		} catch(Exception e) {
			System.out.println("Failure while trying to login." + e.getMessage());
			Assert.assertFalse(false);
		}
	}
}
