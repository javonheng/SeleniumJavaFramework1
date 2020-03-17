package test;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import pages.GoogleSearchPage;
import config.PropertiesFile;

public class GoogleSearchTest {
	
	private static WebDriver driver = null;
	public static String browser;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		    
		PropertiesFile.readPropertiesFile();
		setupClass();
		setupTest();
		googleSearch();
		
		Thread.sleep(3000); //Delay before close 
		
		//teardown();
		//PropertiesFile.writePropertiesFile();
		System.out.println("Test Completed Succesfully");
	}
	
	//BeforeClass
    public static void setupClass() {
    	// this condition block sets configuration for FireFox browser
		if (browser.contains("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
		}
		// this condition block sets configuration for Chrome browser
		if (browser.contains("Chrome")) {
			 WebDriverManager.chromedriver().setup();
	        //Specific version
	        //WebDriverManager.chromedriver().version("2.36").setup();
		}
		if (browser.contains("IE")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			WebDriverManager.iedriver().setup();
		}
		if (browser.contains("")) {
			WebDriverManager.phantomjs().setup();
		}
       
    }

    //Before
    public static void setupTest() {
    	// this condition block sets config for firefox browser
		if (browser.contains("Firefox")) {
			driver = new FirefoxDriver();
		}
		// this condition block sets config for chrome browser
		if (browser.contains("Chrome")) {
			driver = new ChromeDriver();
		}
		if (browser.contains("IE")) {
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		if (browser.contains("")){
			PhantomJsDriverManager driver = new PhantomJsDriverManager();
			System.out.print("Phantom");
		}
    }

    //After
    public static void teardown() {
        if (driver != null) {
        	driver.close();
            driver.quit();
        }
    }

    //Test
    public static void googleSearch() throws InterruptedException, IOException {
    	
    	// start reporters
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        // create ExtentReports and attach reporter(s)
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("Google Search Test", "This is a simple Google search automation function.");
    	
        //Create an object
    	GoogleSearchPage searchPageObj = new GoogleSearchPage(driver);	
    
        
        test.log(Status.INFO, "Starting test case");
    	driver.get("https://google.com");
    	test.pass("Navigated to Google.com");
    	
    	//Enter text in Search Box 
    	searchPageObj.setTextInSearchBox("Automation Step by Step");
    	test.pass("Entered text in search box");
    	
    	//Click on Search Button 
    	searchPageObj.clickSearch();
    	test.pass("Pressed keyboard enter key");
    	
    	//SS 
    	test.addScreenCaptureFromPath("screenshot.png");
    	test.info("Test Completed");
    	
    	// calling flush writes everything to the log file
        extent.flush();
    }
}