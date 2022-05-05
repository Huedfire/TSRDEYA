package hex.casestudy27159.orange.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeHandler implements DriverHandlerI{
	
	String driverPath;
	public void setPath(String path) {
		if(path.trim().length()==0)
			this.driverPath= "C:\\Users\\27159\\Downloads\\chromedriver_win32\\chromedriver.exe";
		else
			this.driverPath = path;
	}
	
	
	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", this.driverPath);
		WebDriver driver = null;
				
		try {
			driver = new ChromeDriver();
			
		} catch(Exception e) {
			System.out.println("ERROR: ChromeHandler.getDriverHandler" + e.getStackTrace());
		}
			
		return driver;
		
		
	}
	
	private String getPath() {
		return driverPath;
	}
	
}
