package com.BankoOversight.InternetBanking.TestScript;


import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import Com.ObjectRepo.HomePage;
import Com.ObjectRepo.InternetBankingLoginPage;
import Com.ObjectRepo.StaffHomePage;
import Com.genericUtility.BaseClass;
import Com.genericUtility.ExcelUtils;


public class POM_IBLOGIN_Test extends BaseClass {


	@Test(groups="smoke")
	public void IbLogin() throws EncryptedDocumentException, IOException, AWTException, InterruptedException  {

        //create object of all pom pages
		HomePage hp = new HomePage(driver);
		InternetBankingLoginPage iblp = new InternetBankingLoginPage(driver);
		StaffHomePage shp = new StaffHomePage(driver);

		//create object of util  claases
		ExcelUtils eObj = new ExcelUtils();

		//mouse hover to internet banking login button
		hp.MouseHoverToInternetBankingAndClickLogin(wObj, driver);

		//Take data from excel sheet
		int lastcellIBLOGIN = eObj.getLatCellNo("IBLOGIN");
		HashMap<String, String> map = eObj.hashMapData("IBLOGIN", lastcellIBLOGIN-1);

		//enter data into internet banking login page
		iblp.enterInternetBankingLoginDetails(driver, map);

		//click on login button 
		iblp.clickLoginBtn();

		//click on logout button 
		shp.clickStaffLogout();














	}
}
