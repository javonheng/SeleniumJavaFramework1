package utils;

public class ExcelUtilsDemo {
	public static void main(String[] args) {
		
		String projectPath = System.getProperty("user.dir");
		
		//Use constructor
		ExcelUtils excel = new ExcelUtils(projectPath+"/excel/data.xlsx", "Sheet1");
		
		excel.getRowCount();
		//Username
		excel.getCellDataString(1, 0);
		//Pw
		excel.getCellDataNumber(1, 1);
	}
}
