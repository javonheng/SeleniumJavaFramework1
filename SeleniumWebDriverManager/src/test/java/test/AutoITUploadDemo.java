package test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoITUploadDemo {
	
	public static void main(String[] args) throws IOException {
		test();
	}
	
	public static void test() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.tinyupload.com/");
		
		driver.findElement(By.xpath("//input[@type='file'][@name='uploaded_file']")).sendKeys(Keys.RETURN);
		
		//Import AutoIT file to upload
		Runtime.getRuntime().exec("C:\\Users\\NTU user\\Desktop\\NTU\\Java Projects\\AutoIT\\FileUploadScript.exe");
		
		//Thread.sleep(3000); // wait 3s
		//driver.close();
		
		
	}
}
