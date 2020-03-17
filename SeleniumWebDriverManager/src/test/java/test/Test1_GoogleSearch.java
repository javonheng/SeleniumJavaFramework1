package test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1_GoogleSearch {
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		setupClass();
		setupTest();
		googleSearch();
		//teardown();
		System.out.println("Test Completed Succesfully");
	}
	
	//BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        //Specific version
        //WebDriverManager.chromedriver().version("2.36").setup();
    }

    //Before
    public static void setupTest() {
        driver = new ChromeDriver();
    }

    //After
    public static void teardown() {
        if (driver != null) {
        	driver.close();
            driver.quit();
        }
    }

    //Test
    public static void googleSearch() throws InterruptedException {
        // Your test code here
    	driver.get("https://google.com");
    	
    	//Get Element in Web page - Enter text
    	//WebElement textBox = driver.findElement(By.className("gLFyf gsfi"));
    	WebElement searchBox = driver.findElement(By.xpath("//input[@type='text'][@name='q']"));
    	
    	searchBox.sendKeys("Automation Step By Step");
    	
    	/*//List of Elements
    	List<WebElement>listOfInputElements = driver.findElements(By.xpath("//input"));
    	//count
    	int count = listOfInputElements.size();
    	System.out.println("Count of Input Elements: " +count);*/
    	
    	//Click on Search Button 
    	//driver.findElement(By.xpath("//input[@type='submit'][@name='btnK']")).sendKeys(Keys.RETURN); OR

    	driver.findElement(By.xpath("//input[@type='submit'][@name='btnK']")).click();
    	
    	Thread.sleep(3000);
    }
}