package com.TestNG;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Com.genericUtility.IpathConstants;

public class DataProviderTest {


	@Test
	public void getData(String src, String dst) {
		System.out.println("from ---->"+src+" to ----> "+dst);


	}


	@DataProvider
	public Object[][] data()
	{

		Object[][] obj = new Object[3][2];

		obj[0][0] = "Bangalore";
		obj[0][1] ="Mtsore";
		return obj;

	}

	@DataProvider
	public Object[][] excelData() throws EncryptedDocumentException, IOException{

		FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("OpenAccount");
		int lastrow = sh.getLastRowNum();
		int lastcell = sh.getRow(0).getLastCellNum();

		Object[][] obj = new Object[lastrow][lastcell]; //store data into object array

		for (int i = 0; i <  lastrow; i++) { //row
             
			
			for(int j =0; j<lastcell;j++) { //column

				obj[i][j]	= sh.getRow(i).getCell(j).getStringCellValue();
			}

		}
       return obj;
	}
	
	
	@DataProvider
	public Object[][] excelData1() throws EncryptedDocumentException, IOException{

		FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DebitCard1");
		int lastrow = sh.getLastRowNum();
		int lastcell = sh.getRow(0).getLastCellNum();

		Object[][] obj = new Object[lastcell][lastrow]; //store data into object array

		for (int i = 0; i <  lastcell; i++) { //column
       
			
			for(int j =0; j<lastrow;j++) { //row
			

				obj[i][j]	= sh.getRow(j).getCell(i).getStringCellValue();
			}

		}
       return obj;
	}
	
	
	
	
       //1 way - to execute the data provider method in the same class
       @Test(dataProvider = "excelData")
       public void executeExcelData(String name, String value) {
    	   
    	   System.out.println("name ---->"+name+ "value ---->"+value);
    	   
    	   
       }









	}

