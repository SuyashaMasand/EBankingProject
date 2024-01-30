package com.BankoOversite.OpenAccount.TestScript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Com.ObjectRepo.OpenAccountConfirmPage;
import Com.ObjectRepo.OpenAccountPage;
import Com.genericUtility.ExcelUtils;
import Com.genericUtility.FileUtility;
import Com.genericUtility.JavaUtils;
import Com.genericUtility.WebDriverUtils;

public class OpenAccount_Smoke_Test {
      @Test
	public void openAccountSmoke() throws IOException, InterruptedException, AWTException {

    	  

  		JavaUtils jObj = new JavaUtils();
  		FileUtility fObj = new FileUtility();
  		ExcelUtils eObj = new ExcelUtils();
  		WebDriverUtils wObj = new WebDriverUtils();
  		//OpenAccountPage OAObj = new OpenAccountPage();

    	  
  		int random = jObj.getRandomNo();  
		//Random ran = new Random();
		//int random = ran.nextInt(100);


		//get the data from property file
  		String URL = fObj.readDataFromProperty("url");
  		String STAFFID = fObj.readDataFromProperty("staffID");
  		String STAFFPWD = fObj.readDataFromProperty("staffPwd");
//		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.properties");
//		Properties p = new Properties();
//		p.load(fis);
//		String URL = p.getProperty("url");
//		String STAFFID = p.getProperty("staffID");
//		String STAFFPWD = p.getProperty("staffPwd");
		
		
  		
  		//get data from excel file 
  		eObj.getLastRowNo("OpenAccount");
//		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
//		Workbook wb = WorkbookFactory.create(fi);
//		int ROWCOUNT = wb.getSheet("OpenAccount").getLastRowNum();

        

		//launch the browser
		WebDriver driver = new ChromeDriver();

		//Maximize the browser
		wObj.maximizeWindow(driver);
		//driver.manage().window().maximize();

		//enter the URL
		driver.get(URL);

		//wait for page load
		wObj.implicitWit(driver, 20);
	   //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


		//click on open account button 
		driver.findElement(By.xpath("//li[text()='Open Account']")).click();

		//enter the details 
		HashMap<String, String> map = eObj.hashMapData("OpenAccount", 0);
//		HashMap<String, String> map = new HashMap<String, String>();
//
//		for(int i=0;i<ROWCOUNT;i++) {
//
//			String key = wb.getSheet("OpenAccount").getRow(i).getCell(0).getStringCellValue();
//			String value = wb.getSheet("OpenAccount").getRow(i).getCell(1).getStringCellValue();
//			map.put(key, value);			
//		}
		
		
        
//		String AccHolderName = "";
//		for(Entry<String, String> set:map.entrySet()) {
//
//			if(set.getKey().equals("name")) {
//                 
//				AccHolderName = set.getValue()+random;
//				driver.findElement(By.name(set.getKey())).sendKeys(AccHolderName);
//
//			}
//			else
//			{
//				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
//			}
//		}

		OpenAccountPage OAObj = new OpenAccountPage(driver);
		OAObj.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");
		
	//	WebElement gender = driver.findElement(By.name("gender"));
	//	wObj.selectText("Female", gender);
//		Select s = new Select(gender);
//		s.selectByVisibleText("Female");
//
//		WebElement state = driver.findElement(By.name("state"));
//		wObj.selectText("Texas", state);
////		Select s1= new Select(state);
////		s1.selectByVisibleText("Texas");
//
//		WebElement city = driver.findElement(By.name("city"));
//		wObj.selectText("Los Angeles", city);
////		Select s2 = new Select(city);
////		s2.selectByVisibleText("Los Angeles");
//
//   
//		WebElement acctype = driver.findElement(By.name("acctype"));
//		wObj.selectText("Saving", acctype);
////		Select s3 = new Select(acctype);
////		s3.selectByVisibleText("Saving");
//
//		driver.findElement(By.name("dob")).click();
//		Robot r = new Robot();
//		r.keyPress(KeyEvent.VK_8);
//		Thread.sleep(500);
//		r.keyPress(KeyEvent.VK_8);
//		Thread.sleep(500);
//		r.keyPress(KeyEvent.VK_1);
//		Thread.sleep(500);
//		r.keyPress(KeyEvent.VK_9);
//		Thread.sleep(500);
//		r.keyPress(KeyEvent.VK_9);
//		Thread.sleep(500);
//		r.keyPress(KeyEvent.VK_5);  

		//click on submit button
		OAObj.clickSubmitBtn();
		//driver.findElement(By.name("submit")).click();
         Thread.sleep(2000);
		//click on confirm button 
		OpenAccountConfirmPage OAcnfm = new OpenAccountConfirmPage(driver);
		OAcnfm.clickConfirmBtn();
	//	driver.findElement(By.name("cnfrm-submit")).click();

		//handle the alert popup 
        Thread.sleep(2000);
        String alertText = wObj.alertGetText(driver);
        
		//Alert alert = driver.switchTo().alert();
        //String alertText = alert.getText();
		System.out.println(alertText);
        wObj.alertAccept(driver);
		//alert.accept();
		Thread.sleep(2000);
		
	}

}
