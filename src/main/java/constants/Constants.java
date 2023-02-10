package constants;

import java.io.File;

import utilities.PropertyFileReader;

public class Constants {
	/*
	 * This Class stores all the timeout times values and driver executable paths
	 */
	public static final int MAX_EXPLICIT_TIMEOUT = Integer.parseInt(PropertyFileReader.getProperty(FilePaths.configFile, "explicitWaitTimeout"));
	public static final int MAX_IMPLICIT_TIMEOUT = Integer.parseInt(PropertyFileReader.getProperty(FilePaths.configFile, "implicitWaitTimeout"));
	static String sp = File.separator;
	static String resourcesPath = System.getProperty("user.dir")+ sp + "src"+ sp + "test" + sp + "resources";
	public static final String CHROME_DRIVER_PATH = resourcesPath + sp + "drivers" + sp + "chromedriver.exe";
	public static final String GECKO_DRIVER_PATH = resourcesPath + sp + "drivers" + sp + "geckodriver.exe";
}
