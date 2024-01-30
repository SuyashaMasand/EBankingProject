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
import Com.genericUtility.BaseClass;
import Com.genericUtility.ExcelUtils;
	import Com.genericUtility.FileUtility;
	import Com.genericUtility.JavaUtils;
	import Com.genericUtility.WebDriverUtils;

	public class OpenAccount_ETE_BC_Test extends BaseClass {

		@Test
		public void openAccount() throws IOException, InterruptedException, AWTException {

		

			String STAFFID = fObj.readDataFromProperty("staffID");
			String STAFFPWD = fObj.readDataFromProperty("staffPwd");

			//get data from excel file 
			int ROWCOUNT = eObj.getLastRowNo("OpenAccount");

			
			//create object of all pom pages 
			HomePage hp = new HomePage(driver);
			OpenAccountPage oap = new OpenAccountPage(driver);
			OpenAccountConfirmPage oacp = new OpenAccountConfirmPage(driver);
			DashBoardPage dp = new DashBoardPage(driver);
			StaffLoginPage slp = new StaffLoginPage(driver);
			StaffHomePage shp = new StaffHomePage(driver);
			ApprovePendingAccountPage apc = new ApprovePendingAccountPage(driver);
			
			
			
			//click on open account button
			hp.clickOpenAccount();

			//enter the details 
			HashMap<String, String> map = eObj.hashMapData("OpenAccount", 1);
			oap.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");

			//click on submit button
			oap.clickSubmitBtn();

			//click on confirm button 
			oacp.clickConfirmBtn();

			//handle the alert popup 
			Alert alert = wObj.switchToAlert(driver);
			String alertText = alert.getText();
			//System.out.println(alertText);
			String appnumref ="";
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
			
			//System.out.println(appnum);

			Thread.sleep(5000);
			
			
	        //handle alert popup
			String Atext = wObj.alertGetText(driver);
			//System.out.println(Atext);
			String accnum = apc.getAccountNum(Atext);
			wObj.alertAccept(driver);


			//click on logout button
			shp.clickStaffLogout();
			
			System.out.println("=====OEPN ACCOUNT END TO END TEST CASE SUCESSFULL======");
		}

	}
























	
	
	
	


