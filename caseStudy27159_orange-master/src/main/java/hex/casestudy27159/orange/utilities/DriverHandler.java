package hex.casestudy27159.orange.utilities;

import org.openqa.selenium.WebDriver;

public class DriverHandler {
	
	// Driver Name
	private DriversEnum driverName;
	
	// Constructor
	public DriverHandler(DriversEnum driverName)
	{
		this.driverName = driverName;
	}
	// Get the driver
	public WebDriver getDriver(){
		WebDriver driver = null;
		//Select the driver by driverName
		switch(driverName){
			
		case CHROME:
			DriverHandlerI handler = new ChromeHandler();
			handler.setPath("C:\\Temp\\JavaCABuild\\drivers\\chromedriver.exe");
			driver = handler.getDriver();
			break;
			
		default:
			System.out.println("No driver found.");
			break;
			
		}
		return driver;
	}
}

