package com.BankoOversight.DebitCard.TestScript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Com.ObjectRepo.DashBoardPage;
import Com.ObjectRepo.HomePage;
import Com.ObjectRepo.InternetBankingRegisterPage;
import Com.genericUtility.ExcelUtils;
import Com.genericUtility.WebDriverUtils;

public class DebitCard_EndtoEnd_Test { 

	@Test
	public void debitCard() throws IOException, AWTException, InterruptedException {

		WebDriverUtils wObj = new WebDriverUtils();
		ExcelUtils eObj = new ExcelUtils();

		Random ran = new Random();
		int random = ran.nextInt(100);


		//get the data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String URL = p.getProperty("url");
		String STAFFID = p.getProperty("staffID");
		String STAFFPWD = p.getProperty("staffPwd");

		//get data from excel file 
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		int ROWCOUNT = wb.getSheet("OpenAccount").getLastRowNum();
		int ROWCOUNT_DEBITCARD1= wb.getSheet("DBCARD").getLastRowNum();
		int ROWCOUNT_IBRegister = wb.getSheet("InternetBankingRegister").getLastRowNum();

		System.out.println(ROWCOUNT_DEBITCARD1);

		//launch the browser
		WebDriver driver = new ChromeDriver();

		//Maximize the browser
		driver.manage().window().maximize();

		//enter the URL
		driver.get(URL);

		//wait for page load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


		//click on open account button 
		driver.findElement(By.xpath("//li[text()='Open Account']")).click();

		//enter the details 
		HashMap<String, String> map = new HashMap<String, String>();

		for(int i=0;i<ROWCOUNT;i++) {

			String key = wb.getSheet("OpenAccount").getRow(i).getCell(0).getStringCellValue();
			String value = wb.getSheet("OpenAccount").getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);			
		}

		String AccHolderName = "";
		String PanNum = "";
		String MobNo="";
		String Pwd="password";
		String lasttrn="100";
		String confirm_pwd="password";

		for(Entry<String, String> set:map.entrySet()) {
			if(set.getKey().equals("name")) {
				AccHolderName = set.getValue()+random;
				driver.findElement(By.name(set.getKey())).sendKeys(AccHolderName);
			}
			else if(set.getKey().contains("pan")) {
				PanNum = set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(PanNum);
			}
			else if(set.getKey().contains("mob")) {
				MobNo= set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(MobNo);
			}
			else if(set.getKey().contains("last_trans")){
				lasttrn = set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(lasttrn);

			}
			else if(set.getKey().contains("password")) {
				Pwd=set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(Pwd);
			}

			else if(set.getKey().contains("cnfrm_password"))
			{
				confirm_pwd=set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(confirm_pwd);
			}


			else
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
			}
		}


		System.out.println(AccHolderName);


		WebElement gender = driver.findElement(By.name("gender"));
		Select s = new Select(gender);
		s.selectByVisibleText("Female");

		WebElement state = driver.findElement(By.name("state"));
		Select s1= new Select(state);
		s1.selectByVisibleText("Texas");

		WebElement city = driver.findElement(By.name("city"));
		Select s2 = new Select(city);
		s2.selectByVisibleText("Los Angeles");



		WebElement acctype = driver.findElement(By.name("acctype"));
		Select s3 = new Select(acctype);
		s3.selectByVisibleText("Saving");

		driver.findElement(By.name("dob")).click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_1);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_5);  

		//click on submit button
		driver.findElement(By.name("submit")).click();

		Thread.sleep(5000);
		//click on confirm button 
		driver.findElement(By.name("cnfrm-submit")).click();

		//handle the alert popup

		Alert alertApplicationNo = driver.switchTo().alert();

		String alertTextAppNo = alertApplicationNo.getText();
		System.out.println(alertTextAppNo);

		String appnum ="";
		char[] ch = alertTextAppNo.toCharArray();  

		for(int i=0;i<ch.length;i++) {

			if(Character.isDigit(ch[i])) {
				appnum = appnum+ ch[i];
			}

		}
		System.out.println("ApplicationNumberIs:" +appnum);


		alertApplicationNo.accept();

		//login to staff login 
		driver.findElement(By.xpath("//a[text()='Staff Login']")).click();
		driver.findElement(By.name("staff_id")).sendKeys(STAFFID);
		driver.findElement(By.name("password")).sendKeys(STAFFPWD);

		//click on login button 
		driver.findElement(By.name("staff_login-btn")).click();


		//------------------------APPROVE ACCOUNT NUMBER -------------------------------------------------------------------------//
		driver.findElement(By.name("apprvac")).click();

		driver.findElement(By.name("application_no")).sendKeys(appnum);

		driver.findElement(By.name("search_application")).click();
		String appNo=null;

		WebElement application = driver.findElement(By.xpath("//div[@class='pending_customers_container']/descendant::tbody/tr[2]/td[2]"));

		if(application!=null) {

			driver.findElement(By.name("approve_cust")).click();
			System.out.println("---ACCOUNT APPROVED-------");

		}
		else {
			alertApplicationNo.accept();
			System.out.println("-----ACCOUNT NOT APPROVED-----");
		}

		Alert alertAccNum = driver.switchTo().alert();
		String alertTextAccNum = alertAccNum.getText();
		System.out.println(alertTextAccNum);

		String accnum ="";
		char[] ch1 = alertTextAccNum.toCharArray();  

		for(int i=0;i<ch1.length;i++) {

			if(Character.isDigit(ch1[i])) {
				accnum = accnum+ ch1[i];
			}

		}
		System.out.println("AccountNumberIs:" +accnum);
		alertAccNum.accept();


		//click on logout button 

		driver.findElement(By.name("logout_btn")).click();

		//click on home major tab 
		driver.findElement(By.xpath("//a[text()='Home']")).click();



		//-----------------------DEBIT CARD--------------------------------------------//		


		//enter all the details 
		eObj.setExcelData("DBCARD", AccHolderName ,0);
		eObj.setExcelData("DBCARD",PanNum ,1 );
		eObj.setExcelData("DBCARD",MobNo , 2);
		eObj.setExcelData("DBCARD", accnum ,3);

		System.out.println(AccHolderName);
		System.out.println(accnum);
		System.out.println(PanNum);
		System.out.println(MobNo);


		//click on apply Debit card 
		driver.findElement(By.xpath("//li[text()='Apply Debit Card']")).click();
		short LAST_CELLNUM = wb.getSheet("DebitCard1").getRow(2).getLastCellNum();

		HashMap<String, String> map1 = new HashMap<String, String>();
		for(int i=0;i<=ROWCOUNT_DEBITCARD1;i++) {

			String key = wb.getSheet("DBCARD").getRow(i).getCell(0).getStringCellValue();
			String value = wb.getSheet("DBCARD").getRow(i).getCell(LAST_CELLNUM-1).getStringCellValue();
			map1.put(key, value);
		}

		for(Entry<String, String> set1:map1.entrySet()) {

			driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());

		}

		System.out.println("lastcellno:-"+LAST_CELLNUM);


		driver.findElement(By.name("dob")).click();
		Robot r1 = new Robot();
		r1.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r1.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r1.keyPress(KeyEvent.VK_1);
		Thread.sleep(500);
		r1.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r1.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r1.keyPress(KeyEvent.VK_5); 


		//CLICK ON SUBMIT BUTTON 
		driver.findElement(By.name("dbt_crd_submit")).click();

		//handle the popup 

		Alert alert_debitcard = driver.switchTo().alert();
		String debitcard_text = alert_debitcard.getText();
		System.out.println(debitcard_text);


		String DebitCard ="";
		char[] ch2 = debitcard_text.toCharArray();  

		for(int i=0;i<ch2.length;i++) {

			if(Character.isDigit(ch2[i])) {
				DebitCard = DebitCard+ ch2[i];
			}

		}

		System.out.println("debitcarddata"+DebitCard);

		String DebitCardNum ="";
		String DebitCardPin="";
		char[] ch3 = DebitCard.toCharArray();

		for(int i=0;i<ch3.length;i++) {

			if(i<12) {
				DebitCardNum = DebitCardNum+ch3[i];
			}
			else {
				DebitCardPin = DebitCardPin+ch3[i];
			}
		}
		Thread.sleep(5000);
		//System.out.println("debitcard data"+DebitCard);
		System.out.println("debitcardnum"+DebitCardNum);
		System.out.println("debitcardpin"+DebitCardPin);

		Thread.sleep(5000);
		alert_debitcard.accept();

		//click on dashboard page home
		DashBoardPage dbp = new DashBoardPage(driver);
		dbp.clickOnHome();

		//click on internet banking register 
		HomePage hp = new HomePage(driver);
		hp.MouseHoverToInternetBankingAndClickRegister(wObj, driver);
//
//		String Pwd="password";
//		String lasttrn="100";
//		String confirm_pwd="password";
		//add data to internetbankingregister excel sheet 
		eObj.setExcelData("InternetBankingRegister", AccHolderName, 0);
		eObj.setExcelData("InternetBankingRegister", accnum, 1);
		eObj.setExcelData("InternetBankingRegister", DebitCardNum, 2);
		eObj.setExcelData("InternetBankingRegister", DebitCardPin, 3);
		eObj.setExcelData("InternetBankingRegister", MobNo, 4);
		eObj.setExcelData("InternetBankingRegister", PanNum, 5);
		eObj.setExcelData("InternetBankingRegister",lasttrn,6 );
		eObj.setExcelData("InternetBankingRegister", Pwd, 7);
		eObj.setExcelData("InternetBankingRegister", confirm_pwd, 8);

		//enter the data into internet banking register page 


		int LAST_CELLNUM_IBRegister = wb.getSheet("InternetBankingRegister").getRow(1).getLastCellNum();
		HashMap<String, String> map2 = new HashMap<String, String>();
		for(int x=0;x<=ROWCOUNT_IBRegister;x++) {

			String key = wb.getSheet("InternetBankingRegister").getRow(x).getCell(0).getStringCellValue();
			String value = wb.getSheet("InternetBankingRegister").getRow(x).getCell(LAST_CELLNUM_IBRegister-1).getStringCellValue();
			map2.put(key, value);
		}

		for(Entry<String, String> set1:map2.entrySet()) {

//
//			if(set1.getKey().contains("last_trans")){
//				lasttrn = set1.getValue();
//				driver.findElement(By.name(set1.getKey())).sendKeys(lasttrn);
//
//			}
//			else if(set1.getKey().contains("password")) {
//				Pwd=set1.getValue();
//				driver.findElement(By.name(set1.getKey())).sendKeys(Pwd);
//			}
//
//			else if(set1.getKey().contains("cnfrm_password"))
//			{
//				confirm_pwd=set1.getValue();
//				driver.findElement(By.name(set1.getKey())).sendKeys(confirm_pwd);
//			}
//			else {
				driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());

		//	}
		}

		System.out.println("lastcellIB-"+LAST_CELLNUM_IBRegister);


		driver.findElement(By.name("dob")).click();
		Robot r2 = new Robot();
		r2.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r2.keyPress(KeyEvent.VK_8);
		Thread.sleep(500);
		r2.keyPress(KeyEvent.VK_1);
		Thread.sleep(500);
		r2.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r2.keyPress(KeyEvent.VK_9);
		Thread.sleep(500);
		r2.keyPress(KeyEvent.VK_5);  


		//click on submit button 
		InternetBankingRegisterPage ibr = new InternetBankingRegisterPage(driver);
		ibr.clickSubmit();









	}







	//click on logout button 
	//driver.findElement(By.)

}

