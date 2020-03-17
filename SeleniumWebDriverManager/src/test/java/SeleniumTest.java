import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		setupClass();
		setupTest();
		test();
		teardown();
		
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
    public static void test() throws InterruptedException {
        // Your test code here
    	driver.get("https://google.com");
    	
    	//Get Element in Web page
    	//WebElement textBox = driver.findElement(By.className("gLFyf gsfi"));
    	//WebElement searchBox = driver.findElement(By.xpath("//input[@type='text'][@name='q']"));
    	
    	//searchBox.sendKeys("Automation Step By Step");
    	
    	//List of Elements
    	List<WebElement>listOfInputElements = driver.findElements(By.xpath("//input"));
    	//count
    	int count = listOfInputElements.size();
    	System.out.println("Count of Input Elements: " +count);
    	
    	Thread.sleep(3000);
    }
}