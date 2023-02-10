package constants;

import java.io.File;

public class FilePaths {
	/*
	 * This class contains the location of all the property files
	 */
	static String sp = File.separator;
	static String resourcesPath = System.getProperty("user.dir")+ sp + "src"+ sp + "test" + sp + "resources";
	public static final String configFile = resourcesPath + sp + "propertyfiles" + sp + "configuration.properties";
	public static final String capabilityFile = resourcesPath + sp + "propertyfiles" + sp + "capabilities.properties";
	public static final String searchEngineTestsFile = resourcesPath + sp + "propertyfiles" + sp + "searchEngineTest.properties";
}
