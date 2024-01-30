package Com.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtils {
	/**
	 * This method is to read data from excel file
	 * @param sheetName
	 * @param row
	 * @param cellNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String sheetName, int row, int cellNo) throws EncryptedDocumentException, IOException {

		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		String value =sh.getRow(row).getCell(cellNo).getStringCellValue();
		return value;
	}
	/**
	 * this method is to get row number
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastRowNo(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;

	}
	
	/**
	 * The methiod is used to get last cell number
	 * @param sheetName
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public int getLatCellNo(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		int cellCount = sh.getRow(0).getLastCellNum();
		return cellCount;
		
	}
	/**
	 * this method is used to enter multiple data into excel using 	HashMap
	 * @param SheetName
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public HashMap<String, String> hashMapData(String SheetName, int cell) throws EncryptedDocumentException, IOException {

		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(SheetName);
		int lastrow=sh.getLastRowNum();

		HashMap<String, String> map = new HashMap<String, String>(); //empty

		for(int i=0; i<=lastrow;i++) {
			String key = sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(cell).getStringCellValue();
			map.put(key, value);
		}



		return map;
	}

	/**
	 * This method is used to write the data into excel file
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetName, int row, int cell, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet(sheetName);
		sh.createRow(row).createCell(cell).setCellValue(data);		
		FileOutputStream fout = new FileOutputStream(IpathConstants.ExcelPath);
		wb.write(fout);
		wb.close();
	}

	public void setExcelData(String sheetname,String value,int rownum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getLastRowNum();
		Sheet sh = wb.getSheet(sheetname);
		for(int i=1;true;i++) {
			try {
				Row row = sh.getRow(rownum);
				Cell cell = row.getCell(i);
				if(cell==null) {
					row.createCell(i).setCellValue(value);
					break;
				}
			}catch (Exception e) {

			}

		}
		FileOutputStream fos=new FileOutputStream(IpathConstants.ExcelPath);
		wb.write(fos);
		wb.close();
	}
}
