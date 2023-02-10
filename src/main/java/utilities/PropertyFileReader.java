package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	
	/*
	 * purpose:
	 * It returns the value of the key specified in property file
	 * @parameters:
	 * filePath : path of the property file
	 * property : key from property file
	 * @return:
	 * It returns value of the key specified in property file
	 */
	public static String getProperty(String filePath, String property) {
		String propertyValue = null;
		try {
			FileReader reader = new FileReader(filePath);
			Properties configFile = new Properties();
			configFile.load(reader);
			propertyValue= configFile.getProperty(property);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyValue;		
	}
}
