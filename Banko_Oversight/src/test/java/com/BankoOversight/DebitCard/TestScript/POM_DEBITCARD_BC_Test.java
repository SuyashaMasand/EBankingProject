package com.BankoOversight.DebitCard.TestScript;

	
	

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

	import Com.ObjectRepo.ApplyDebitCardPage;
	import Com.ObjectRepo.ApprovePendingAccountPage;
	import Com.ObjectRepo.DashBoardPage;
	import Com.ObjectRepo.HomePage;
	import Com.ObjectRepo.InternetBankingRegisterPage;
	import Com.ObjectRepo.OpenAccountConfirmPage;
	import Com.ObjectRepo.OpenAccountPage;
	import Com.ObjectRepo.StaffHomePage;
	import Com.ObjectRepo.StaffLoginPage;
import Com.genericUtility.BaseClass;
import Com.genericUtility.ExcelUtils;
	import Com.genericUtility.FileUtility;
	import Com.genericUtility.IpathConstants;
	import Com.genericUtility.JavaUtils;
	import Com.genericUtility.WebDriverUtils;

	public class POM_DEBITCARD_BC_Test extends BaseClass {

		@Test
		public void DebitCard() throws IOException, InterruptedException, AWTException {

			String STAFFID = fObj.readDataFromProperty("staffID");
			String STAFFPWD = fObj.readDataFromProperty("staffPwd");

			//create object of all pom pages 
			HomePage hp = new HomePage(driver);
			OpenAccountPage oap = new OpenAccountPage(driver);
			OpenAccountConfirmPage oacp = new OpenAccountConfirmPage(driver);
			DashBoardPage dp = new DashBoardPage(driver);
			StaffLoginPage slp = new StaffLoginPage(driver);
			StaffHomePage shp = new StaffHomePage(driver);
			ApprovePendingAccountPage apc = new ApprovePendingAccountPage(driver);
			InternetBankingRegisterPage ibrp = new InternetBankingRegisterPage(driver);

			//click on open account button
			hp.clickOpenAccount();

			//enter the details 
			HashMap<String, String> map = eObj.hashMapData("OpenAccount", 1);
			String[] s = oap.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");

			//click on submit button
			oap.clickSubmitBtn();

			//click on confirm button 
			oacp.clickConfirmBtn();

			//handle the alert popup 
			Alert alert = wObj.switchToAlert(driver);
			String alertText = alert.getText();
			//System.out.println(alertText);
			String appnum = oap.getApplicationNum(alertText);
			//System.out.println("ApplicationNumberIs:" +appnum);
			wObj.alertAccept(driver);

			//click on staff login link 
			dp.clickStaffLogin();

			//enter login details
			slp.EnterstaffLoginDetails(STAFFID, STAFFPWD);


			//-------------------------APPROVE ACCOUNT NUMBER -------------------------------------------------//
			//click on approve pending account button
			shp.clickApprovePendingAccount();

			//enter application number
			apc.enterApplicationNo(driver, appnum);

			//handle alert popup
			String Atext = wObj.alertGetText(driver);
			//System.out.println(Atext);
			String accnum = apc.getAccountNum(Atext);
			wObj.alertAccept(driver);


			//click on logout button
			shp.clickStaffLogout();

			//add data to DBCARD card excel sheet
			eObj.setExcelData("DBCARD", s[0] ,0);
			eObj.setExcelData("DBCARD",s[2] ,1 );
			eObj.setExcelData("DBCARD",s[3] , 2);
			eObj.setExcelData("DBCARD", accnum ,3);

			//-------DEBIT CARD ----------------------------------//

			//click on home major tab
			dp.clickOnHome();

			//click on apply Debit card 
			hp.clickApplyDebitCard();


			//entering data into debit card details page 
			int lastCell = eObj.getLatCellNo("DBCARD");
			HashMap<String, String> map1 = eObj.hashMapData("DBCARD", lastCell-1);
			ApplyDebitCardPage adcp = new ApplyDebitCardPage(driver);
			adcp.EnterDetailsOfDebitCardPage(driver, map1);


			//click on submit button
			adcp.ClicksubmitButton();

			//handle alert of debit card 
			Alert debitcardalert = wObj.switchToAlert(driver);
			String debitcard_text = wObj.alertGetText(driver);
			System.out.println(debitcard_text);
			debitcardalert.accept();
			//click on home button
			dp.clickOnHome();


			//fetching debitcard number and pin 

			ApplyDebitCardPage adc = new ApplyDebitCardPage(driver);
			String[] DB = adc.fetchingDCPinAndDCNo(debitcard_text);

			//add details in  internet banking page 

			String last_trans="0";
			String pwd = "password";
			String cnf_pwd ="password";

			eObj.setExcelData("IBREGISTER", s[0], 0);
			eObj.setExcelData("IBREGISTER",accnum , 1);
			eObj.setExcelData("IBREGISTER", DB[0], 2);
			eObj.setExcelData("IBREGISTER", DB[1], 3);
			eObj.setExcelData("IBREGISTER",s[3] , 4);
			eObj.setExcelData("IBREGISTER", s[2], 5);
			eObj.setExcelData("IBREGISTER",last_trans , 6);
			eObj.setExcelData("IBREGISTER",pwd , 7);
			eObj.setExcelData("IBREGISTER", cnf_pwd , 8);

			// mouse hover to internet banking register button 
			hp.MouseHoverToInternetBankingAndClickRegister(wObj, driver);

			//enter the details 
			int lastcell = eObj.getLatCellNo("IBREGISTER");
			HashMap<String, String> mapIbReg = eObj.hashMapData("IBREGISTER", lastcell-1);
			ibrp.enterDetailsOfInternetBankingRegister(driver, mapIbReg);

			//click on submit button
			// InternetBankingRegisterPage ibrp = new InternetBankingRegisterPage(driver);
			ibrp.clickSubmit();

			Thread.sleep(2000);


			//handle the internet banking register popup 
			//handle alert of debit card 
			Alert IBRegisteralert = wObj.switchToAlert(driver);
			String IBRegister_text = wObj.alertGetText(driver);
			System.out.println(IBRegister_text);
			IBRegisteralert.accept();

			//fetching customer ID 
			String custID = ibrp.fetchCustimerID(IBRegister_text);
			
		
		     eObj.setExcelData("IBLOGIN", custID , 0);
		     eObj.setExcelData("IBLOGIN", pwd, 1);
		
	  	}


	}





























