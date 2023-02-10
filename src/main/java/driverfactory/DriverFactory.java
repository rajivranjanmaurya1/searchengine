package driverfactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import constants.Constants;
import constants.FilePaths;
import utilities.PropertyFileReader;

public class DriverFactory {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/*
	 * This function instantiates driver and sets it up
	 */
	public static void driverSetup() {
		try{
			String browser = PropertyFileReader.getProperty(FilePaths.capabilityFile, "browser");
			switch (browser){
			case "chrome":
				System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER_PATH);
				driver.set(new ChromeDriver());
				driver.get().manage().deleteAllCookies();
				driver.get().manage().window().maximize();
				break;			
			case "firefox": 
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				System.setProperty("webdriver.gecko.driver",Constants.GECKO_DRIVER_PATH);
				firefoxOptions.setBinary(PropertyFileReader.getProperty(FilePaths.capabilityFile, "firefoxBinaryPath"));
				driver.set(new FirefoxDriver(firefoxOptions));
				System.out.println("----------Test Step: Clear browser cookies----------");
				driver.get().manage().deleteAllCookies();
				driver.get().manage().window().maximize();
				break;			
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	/*
	 * This function closes the all the instances of driver
	 */
	public static void closeBrowser() {
		driver.get().quit();
	}	
}
