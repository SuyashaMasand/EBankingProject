package com.BankoOversite.OpenAccount.TestScript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

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

import Com.ObjectRepo.ApprovePendingAccountPage;
import Com.ObjectRepo.DashBoardPage;
import Com.ObjectRepo.HomePage;
import Com.ObjectRepo.OpenAccountConfirmPage;
import Com.ObjectRepo.OpenAccountPage;
import Com.ObjectRepo.StaffHomePage;
import Com.ObjectRepo.StaffLoginPage;
import Com.genericUtility.ExcelUtils;
import Com.genericUtility.FileUtility;
import Com.genericUtility.JavaUtils;
import Com.genericUtility.WebDriverUtils;

public class OpenAccount_EndToEndTest{

	@Test
	public void openAccount() throws IOException, InterruptedException, AWTException {


		JavaUtils jObj = new JavaUtils();
		FileUtility fObj = new FileUtility();
		ExcelUtils eObj = new ExcelUtils();
		WebDriverUtils wObj = new WebDriverUtils();
        
	

		int random = jObj.getRandomNo();

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
		int ROWCOUNT = eObj.getLastRowNo("OpenAccount");
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
		wObj.pageLoadtimeOut(driver, 20);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


		//click on open account button
		HomePage hp = new HomePage(driver);
		hp.clickOpenAccount();
		//driver.findElement(By.xpath("//li[text()='Open Account']")).click();

		//enter the details 
		HashMap<String, String> map = eObj.hashMapData("OpenAccount", 0);

		//		HashMap<String, String> map = new HashMap<String, String>();
		//
		//		for(int i=0;i<ROWCOUNT;i++) {
		//
		//			String key = wb.getSheet("OpenAccount").getRow(i).getCell(0).getStringCellValue();
		//			String value = wb.getSheet("OpenAccount").getRow(i).getCell(1).getStringCellValue();
		//			map.put(key, value);			
		//	}
		OpenAccountPage oap = new OpenAccountPage(driver);
		oap.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");
//		String AccHolderName ="";
//		for(Entry<String, String> set:map.entrySet()) {
//
//			if(set.getKey().equals("name")) {
//
//				AccHolderName = set.getValue()+random;  
//				//driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+random);
//				driver.findElement(By.name(set.getKey())).sendKeys(AccHolderName);
//
//
//			}
//			else
//			{
//				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
//			}
//		}
//
//		System.out.println(AccHolderName);
//
//
//
//		//	Thread.sleep(2000);
//
//		WebElement gender = driver.findElement(By.name("gender"));
//		wObj.selectText("Female", gender);
//		//		Select s = new Select(gender);
//		//		s.selectByVisibleText("Female");
//
//		WebElement state = driver.findElement(By.name("state"));
//		wObj.selectText("Texas", state);
//		//		Select s1= new Select(state);
//		//		s1.selectByVisibleText("Texas");
//
//		WebElement city = driver.findElement(By.name("city"));
//		wObj.selectText("Los Angeles", city);
//		//		Select s2 = new Select(city);
//		//		s2.selectByVisibleText("Los Angeles");
//
//
//
//
//
//		WebElement acctype = driver.findElement(By.name("acctype"));
//		wObj.selectText("Saving", acctype);
//		//		Select s3 = new Select(acctype);
//		//		s3.selectByVisibleText("Saving");
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
		oap.clickSubmitBtn();
	//	driver.findElement(By.name("submit")).click();

		//click on confirm button 
		OpenAccountConfirmPage oacp = new OpenAccountConfirmPage(driver);
		oacp.clickConfirmBtn();
		//driver.findElement(By.name("cnfrm-submit")).click();

		//handle the alert popup 
	   
		Alert alert = wObj.switchToAlert(driver);
		//Alert alert = driver.switchTo().alert();
          
		String alertText = alert.getText();
		System.out.println(alertText);
         
		String appnumref ="";
		
		String appnum = oap.getApplicationNum(alertText);
		//String appnum ="";
		//char[] ch = alertText.toCharArray();  

		//for(int i=0;i<ch.length;i++) {

			//if(Character.isDigit(ch[i])) {
				//appnum = appnum+ ch[i];
			//}

		//}
	   System.out.println("ApplicationNumberIs:" +appnum);

		wObj.alertAccept(driver);
		//alert.accept();


		//click on staff login link 
		DashBoardPage dp = new DashBoardPage(driver);
		dp.clickStaffLogin();
		StaffLoginPage slp = new StaffLoginPage(driver);
		slp.EnterstaffLoginDetails(STAFFID, STAFFPWD);
		
        
//		driver.findElement(By.linkText("Staff Login")).click();
//
//		driver.findElement(By.name("staff_id")).sendKeys(STAFFID);
//		driver.findElement(By.name("password")).sendKeys(STAFFPWD);


		//click on login button 
//		driver.findElement(By.name("staff_login-btn")).click();

		//-------------------------APPROVE ACCOUNT NUMBER -------------------------------------------------//
		StaffHomePage shp = new StaffHomePage(driver);
		shp.clickApprovePendingAccount();
		
		//driver.findElement(By.name("apprvac")).click();
		
		 
		ApprovePendingAccountPage apc = new ApprovePendingAccountPage(driver);
		apc.enterApplicationNo(driver, appnum);
//       driver.findElement(By.name("application_no")).sendKeys(appnum);
//        
//		driver.findElement(By.name("search_application")).click();
//		String applicationNo=null;
//
//		WebElement application = driver.findElement(By.xpath("//div[@class='pending_customers_container']/descendant::tbody/tr[2]/td[2]"));
//
//		if(application!=null) {
//
//			driver.findElement(By.name("approve_cust")).click();
//			System.out.println("---ACCOUNT APPROVED-------");
//
//		}
//		else {
//			wObj.alertAccept(driver);
//			//alert.accept();
//			System.out.println("-----ACCOUNT NOT APPROVED-----");
//		}

		String Atext = wObj.alertGetText(driver);
		//Alert alert1 = driver.switchTo().alert();
		//String alertText1 = alert1.getText();
		System.out.println(Atext);
		
		
		String accnum = apc.getAccountNum(Atext);

		//String accnum ="";
		
		
//		char[] ch1 = Atext.toCharArray();  
//
//		for(int i=0;i<ch.length;i++) {
//
//			if(Character.isDigit(ch[i])) {
//				accnum = accnum+ ch[i];
//			}
//
//		}
//		
//		System.out.println("AccountNumberIs:" +accnum);

		wObj.alertAccept(driver);


		//click on logout button
		
		shp.clickStaffLogout();

		//driver.findElement(By.name("logout_btn")).click();


	}

	

	
}




















