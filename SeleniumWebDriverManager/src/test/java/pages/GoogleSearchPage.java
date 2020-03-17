package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Google web store eskry
//KATALON RECORDER in webstore to track browser movements; records test
public class GoogleSearchPage {
	
	WebDriver driver = null;
	
	//Define Components/Objects
	By textbox_search = By.name("q");
	By button_search = By.name("btnK");
	
	//Constructor
	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Actions
	public void setTextInSearchBox(String text) {

		driver.findElement(textbox_search).sendKeys(text);
	}
	
	public void clickSearch() {
		driver.findElement(button_search).click();
		//driver.findElement(button_search).sendKeys(Keys.RETURN);
	}
	
}