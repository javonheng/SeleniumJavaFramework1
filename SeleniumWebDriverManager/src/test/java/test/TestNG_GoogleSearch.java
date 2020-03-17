package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import pages.GoogleSearchPage;
import config.PropertiesFile;

public class TestNG_GoogleSearch {

	static WebDriver driver = null;
	static ExtentReports extent;
	public static String browserName = null;
	static WebDriverWait wait = null;

	/*Annotations Steps: @BeforeSuite
	@BeforeTest
	@BeforeClass
	@BeforeMethod
	@Test
	@AfterMethod
	@AfterClass
	@AfterTest
	@AfterSuite
	*/
	
	@BeforeSuite
	public void beforeSuite() {
		
		// start reporters
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
        
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
	
	}
	
	@Parameters("browserName")
	@BeforeTest
    public void setupTest(String browserName) throws Exception {
		
		//Either use testng xml parameters or properties file 
		PropertiesFile.readPropertiesFile();
		
    	// this condition block sets configuration for FireFox browser
		if (browserName.equalsIgnoreCase("Firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		// this condition block sets configuration for Chrome browser
		if (browserName.equalsIgnoreCase("Chrome")) {
			
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
	        //Specific version
	        //WebDriverManager.chromedriver().version("2.36").setup();
		
		}
		// this condition block sets configuration for Headless Chrome browser
		if (browserName.equalsIgnoreCase("ChromeHeadless")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1280,800");
			
			driver = new ChromeDriver(options);
			
		}
		if (browserName.equalsIgnoreCase("IE")) {
			
			//Settings 
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.introduceFlakinessByIgnoringSecurityDomains();
			//options.withInitialBrowserUrl()
			options.ignoreZoomSettings();
			options.enablePersistentHovering();
			//options.requireWindowFocus();
			
			
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver(options);
			driver.manage().window().maximize();
		}

    }


	@AfterTest
    public void teardown() {
        if (driver != null) {
        	driver.close();
            driver.quit();
        }
      //PropertiesFile.writePropertiesFile();
      System.out.println("Test Completed Succesfully");
    }
	
    @Test
    public static void googleSearch() throws InterruptedException, IOException {
    	
    	//Create an object
    	GoogleSearchPage searchPageObj = new GoogleSearchPage(driver);
    	
    	// creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("Google Search Test 1", "This is a simple Google search automation function.");
        test.log(Status.INFO, "Starting Test Case");
        
        test.info("This step shows the usage of info(details)");
    	
        //Manage Polling time before throwing Exceptions
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //default 250ms
        
        //Explicit wait for specific elements
        wait = new WebDriverWait(driver, 20); //20secs
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("")));
        
        //Go to site
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
        
    	test.pass("Navigated to Google.com");
    	
    	//Enter text in Search Box 
    	searchPageObj.setTextInSearchBox("Automation Step by Step");
    	test.pass("Entered text in search box");
    	
    	
    	//Click on Search Button 
    	searchPageObj.clickSearch();
    	test.pass("Pressed keyboard enter key");
    	
    	driver.findElement(By.xpath("//h3[text()='- Automation Step by Step']")).click();
    	System.out.println(driver.getTitle());
    	
    	//FluentWait - https://stackoverflow.com/questions/40753321/fluent-wait-vs-webdriver-wait
    	
    	Thread.sleep(3000); //Delay before close 
    	
    	// log with snapshot
        //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
    	//test with Screenshots 
    	//test.addScreenCaptureFromPath("screenshot.png");
    	test.info("Test Completed");
    	
    }
    
    /*@Test
    public static void googleSearch2() throws InterruptedException, IOException {
    	
    	//Create an object
    	GoogleSearchPage searchPageObj = new GoogleSearchPage(driver);
    	
    	// creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.createTest("Google Search Test 2", "This is another Google search automation function.");
        test.log(Status.INFO, "Starting Test Case");
        
        test.info("This step shows the usage of info(details)");
    	
        driver.get("https://google.com");
    	test.pass("Navigated to Google.com");
    	
    	//Enter text in Search Box 
    	searchPageObj.setTextInSearchBox("TestNG Reports");
    	test.pass("Entered text in search box");
    	
    	
    	//Click on Search Button 
    	searchPageObj.clickSearch();
    	test.pass("Pressed keyboard enter key");
    	
    	Thread.sleep(5000); //Delay before close 
    	
    	// log with snapshot
        //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
    	//test with Screenshots 
    	//test.addScreenCaptureFromPath("screenshot.png");
    	test.info("Test Completed");
    
    }*/
    
    @AfterSuite 
    public void generateReport() {     	
    	// calling flush writes everything to the log file
        extent.flush();
    }
}