package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import test.GoogleSearchTest;
import test.TestNG_GoogleSearch;

public class PropertiesFile {
	
	static Properties prop = new Properties();
	
	public static void main(String[] args) {
		readPropertiesFile();
		writePropertiesFile();
	}
	
	public static void readPropertiesFile() {
		
		try {
			
			InputStream input = new FileInputStream("C:\\Users\\NTU user\\Desktop\\NTU\\Java Projects\\SeleniumWebDriverManager\\src\\test\\java\\config\\config.properties");
			prop.load(input);
			//System.out.println(prop.getProperty("browser"));
			String browserName = prop.getProperty("browser");
			TestNG_GoogleSearch.browserName = browserName;
			//System.out.println(FirstSeleniumTest.browser);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public static void writePropertiesFile() {
		try {
			
			OutputStream output = new FileOutputStream("C:\\Users\\NTU user\\Desktop\\NTU\\Java Projects\\SeleniumWebDriverManager\\src\\test\\java\\config\\config.properties");
			//prop.setProperty("browser","Chrome");
			
			//Add new Property
			prop.setProperty("result", "pass");
			prop.store(output, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
}
