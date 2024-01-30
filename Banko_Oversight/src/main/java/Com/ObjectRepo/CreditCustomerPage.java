package Com.ObjectRepo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.genericUtility.ExcelUtils;
import Com.genericUtility.IpathConstants;
import Com.genericUtility.JavaUtils;

public class CreditCustomerPage {


//declaration
	@FindBy(name="credit_btn")
	private WebElement creditBtn;

	
	
	//initialization
	public CreditCustomerPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void enterCustAccNumandAmmount() {
		
	}
	
	public void clickCredutBtn() {
		creditBtn.click();
		
	}
	
	public void enterDetailsinAppliction(WebDriver driver, ExcelUtils eObj) throws EncryptedDocumentException, IOException {
		int ROWCOUNT_CREDITCUSTOMER = eObj.getLastRowNo("CreditCustomer");
		 FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
	       Workbook wb = WorkbookFactory.create(fis);
	        int LAST_CELLNUM = wb.getSheet("CreditCustomer").getRow(0).getLastCellNum();
			HashMap<String, String> map1 = new HashMap<String, String>();
			for(int i=0;i<=ROWCOUNT_CREDITCUSTOMER;i++) {

				String key = wb.getSheet("CreditCustomer").getRow(i).getCell(0).getStringCellValue();
				String value = wb.getSheet("CreditCustomer").getRow(i).getCell(LAST_CELLNUM-1).getStringCellValue();
				map1.put(key, value);
			}

			for(Entry<String, String> set1:map1.entrySet()) {

				driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());
				
				}
		
	}
	
	public void enterAccountNumber(WebDriver driver, ExcelUtils eObj) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb =WorkbookFactory.create(fi);
		int ROWCOUNT_ViewCustomerByAcc = eObj.getLastRowNo("ViewCustomerByAccNum");
	    int LAST_CELLNUM_ViewCustByAcc = wb.getSheet("ViewCustomerByAccNum").getRow(0).getLastCellNum();
		HashMap<String, String> map2 = new HashMap<String, String>();
		for(int i=0;i<=ROWCOUNT_ViewCustomerByAcc;i++) {

			String key = wb.getSheet("ViewCustomerByAccNum").getRow(0).getCell(0).getStringCellValue();
			String value = wb.getSheet("ViewCustomerByAccNum").getRow(i).getCell(LAST_CELLNUM_ViewCustByAcc-1).getStringCellValue();
			map2.put(key, value);
		}

		for(Entry<String, String> set1:map2.entrySet()) {

			driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());
			
			}
		
	}
	
	

	
}