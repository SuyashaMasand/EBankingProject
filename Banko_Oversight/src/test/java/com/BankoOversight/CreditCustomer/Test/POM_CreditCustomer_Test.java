package com.BankoOversight.CreditCustomer.Test;




import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Com.ObjectRepo.ApprovePendingAccountPage;
import Com.ObjectRepo.CreditCustomerPage;
import Com.ObjectRepo.DashBoardPage;
import Com.ObjectRepo.HomePage;
import Com.ObjectRepo.OpenAccountConfirmPage;
import Com.ObjectRepo.OpenAccountPage;
import Com.ObjectRepo.StaffHomePage;
import Com.ObjectRepo.StaffLoginPage;
import Com.ObjectRepo.ViewCustomerByAccNo;
import Com.genericUtility.BaseClass;
import Com.genericUtility.ExcelUtils;
import Com.genericUtility.FileUtility;
import Com.genericUtility.IpathConstants;
import Com.genericUtility.JavaUtils;
import Com.genericUtility.WebDriverUtils;


public class POM_CreditCustomer_Test  {

	@Test
	public void creditCustomerEndToEnd() throws IOException, AWTException, InterruptedException  {


		//create object of all util classes
		JavaUtils jObj = new JavaUtils();
		FileUtility fObj = new FileUtility();
		ExcelUtils eObj = new ExcelUtils();
		WebDriverUtils wObj = new WebDriverUtils();

		//take random number for apending name
		int random = jObj.getRandomNo();

		//get the data from property file
		String URL = fObj.readDataFromProperty("url");
		String STAFFID = fObj.readDataFromProperty("staffID");
		String STAFFPWD = fObj.readDataFromProperty("staffPwd");

		//get data from excel file 
		int ROWCOUNT = eObj.getLastRowNo("OpenAccount");

		//launch the browser
		WebDriver driver = new ChromeDriver();

		//creating object of pom pages 
		HomePage hp = new HomePage(driver);
		StaffLoginPage slp = new StaffLoginPage(driver);
		StaffHomePage shp = new StaffHomePage(driver);
		CreditCustomerPage ccp = new CreditCustomerPage(driver);
		OpenAccountPage oap = new OpenAccountPage(driver);
		DashBoardPage dp = new DashBoardPage(driver);
		ApprovePendingAccountPage apc = new ApprovePendingAccountPage(driver);
		OpenAccountConfirmPage oacp = new OpenAccountConfirmPage(driver);
		ViewCustomerByAccNo vcan = new ViewCustomerByAccNo(driver);


		//Maximize the browser
		wObj.maximizeWindow(driver);

		//enter the URL
		driver.get(URL);

		//wait for page load
		wObj.pageLoadtimeOut(driver, 20);



		//click on open account button
		hp.clickOpenAccount();


		//enter the details using hashmap
		HashMap<String, String> map = eObj.hashMapData("OpenAccount", 1);
		String[] s = oap.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");


		//click on submit button
		oap.clickSubmitBtn();

		//click on confirm button 
		oacp.clickConfirmBtn();

		//handle the alert popup 
		Alert alert = wObj.switchToAlert(driver);
		String alertText = alert.getText();
		System.out.println(alertText);

		//get generated application number
		String appnum = oap.getApplicationNum(alertText);

		System.out.println("ApplicationNumberIs:" +appnum);

		//accept the alert popup
		wObj.alertAccept(driver);

		//click on staff login link 
		dp.clickStaffLogin();
		slp.EnterstaffLoginDetails(STAFFID, STAFFPWD);

		//-------------------------APPROVE ACCOUNT NUMBER -------------------------------------------------//

		//click on approve pending account
		shp.clickApprovePendingAccount();


		//enter application number in approve pending account page 
		apc.enterApplicationNo(driver, appnum);

		//get alert text of generated account number
		String Atext = wObj.alertGetText(driver);
		System.out.println(Atext);
		String accnum = apc.getAccountNum(Atext);
		wObj.alertAccept(driver);

		//------------------------CREDIT CUSTOMER------------------------------------------------//

		//click on home button 
		shp.clickStaffHome();

		//click on credit customer
		shp.clickCreditCustomer();

		//enter the details into credit customer excel sheet 
		eObj.setExcelData("CreditCustomer", accnum , 0);
		eObj.setExcelData("CreditCustomer", s[1] , 1);

		//enter the details in the application 
		ccp.enterDetailsinAppliction(driver, eObj);

		//click on credit button
		ccp.clickCredutBtn();

		// fetch alert text 
		Alert creditAlert = driver.switchTo().alert();
		String creditAlertText=wObj.alertGetText(driver);
		System.out.println(creditAlertText);
		creditAlert.accept();

		//click on home button 
		shp.clickStaffHome();

		//click on view customer by account num 
		shp.clickViewCustomerByAcNo();


		//enter data into excel - viewcsutbyaccnum
		eObj.setExcelData("ViewCustomerByAccNum", accnum , 0);


		//enter account number details 
		ccp.enterAccountNumber(driver, eObj);

		//click on submit Button
		vcan.clickSubmitBtn();

		//click on logout button
		shp.clickStaffLogout();
		
		System.out.println("======CREDIT CUSTOMER END TO END SUCESSFULL=======");
}
  }
































