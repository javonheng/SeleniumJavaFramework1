package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	//Create a Constructor 
	public ExcelUtils(String excelPath, String sheetName) {
		try {

			workbook = new XSSFWorkbook(excelPath);

			//For older versions <2003
			//HSSFWorkbook workbook_old = new HSSFWorkbook();

			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {

		getRowCount();
		getColCount();
		getCellDataString(1, 0); //row, col
		getCellDataNumber(1, 1); //row, col

	}*/
	
	public static int getRowCount() {
		int rowCount=0;
		
		try {

			//Actions ; sheet.
			rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows: " + rowCount);

		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public static int getColCount() {
		int colCount=0;
		try {

			//Actions ; sheet.
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No of cols: " + colCount);

		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		
		return colCount;
	}

	public static String getCellDataString(int rownum, int colnum) {
		String cellUser = null;
		try {

			//Actions ; sheet.
			cellUser = sheet.getRow(rownum).getCell(colnum).getStringCellValue(); //row0 cell1 - Username 

			//System.out.println(cellUser);


		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return cellUser;
	}

	public static double getCellDataNumber(int rownum, int colnum) {
		double cellPw=0;
		try {

			//Actions ; sheet.
			cellPw = sheet.getRow(rownum).getCell(colnum).getNumericCellValue();
			//System.out.println(cellPw);


		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return cellPw;
	}

}
