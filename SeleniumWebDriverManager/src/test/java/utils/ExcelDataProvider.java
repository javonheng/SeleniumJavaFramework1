package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {

	WebDriver driver = null;
	
	@BeforeTest
	public void setupTest() throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//Specific version
		//WebDriverManager.chromedriver().version("2.36").setup();
	}


	@Test(dataProvider = "test1data")
	public void test1(String username, String pw) throws InterruptedException {
		System.out.println(username+" | "+pw);
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(pw);
		
		Thread.sleep(3000);
		

	}

	@DataProvider(name="test1data")
	public Object[][] getData() {

		String projectPath = System.getProperty("user.dir");
		Object[][] data = testData(projectPath+"/excel/data.xlsx", "Sheet1" );
		return data;

	}

	static public Object[][] testData(String excelPath, String sheetName) {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		//Minus 1 because of Header
		Object data[][] = new Object[rowCount-1][colCount];

		for (int i=1; i<rowCount; i++) {
			for (int j=0; j<colCount; j++) {

				String cellUser = excel.getCellDataString(i, j);
				//System.out.print(cellUser+ " | ");
				data[i-1][j] = cellUser;
			}
			//System.out.println();
		}
		return data;

	}


}
