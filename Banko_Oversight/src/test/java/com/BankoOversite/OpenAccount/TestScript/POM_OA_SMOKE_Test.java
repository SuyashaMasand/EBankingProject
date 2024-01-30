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

	public class POM_OA_SMOKE_Test {
	      @Test
		public void openAccountSmoke() throws IOException, InterruptedException, AWTException {

	    	  
            //create object of all the util classes
	  		JavaUtils jObj = new JavaUtils();
	  		FileUtility fObj = new FileUtility();
	  		ExcelUtils eObj = new ExcelUtils();
	  		WebDriverUtils wObj = new WebDriverUtils();

	    	//append random number for name  
	  		int random = jObj.getRandomNo();  
		
			//get the data from property file
	  		String URL = fObj.readDataFromProperty("url");
	  		String STAFFID = fObj.readDataFromProperty("staffID");
	  		String STAFFPWD = fObj.readDataFromProperty("staffPwd");
	  		
	  		//get data from excel file 
 
			//launch the browser
			WebDriver driver = new ChromeDriver();

			//Maximize the browser
			wObj.maximizeWindow(driver);

			//enter the URL
			driver.get(URL);

			//wait for page load
			wObj.implicitWit(driver, 20);
			
			//create object of pom pages 
			OpenAccountPage OAObj = new OpenAccountPage(driver);
			OpenAccountConfirmPage OAcnfm = new OpenAccountConfirmPage(driver);
			

			//click on open account button 
			driver.findElement(By.xpath("//li[text()='Open Account']")).click();

			//enter the details 
			HashMap<String, String> map = eObj.hashMapData("OpenAccount", 0);
			OAObj.EnterAllDataInOpenAccountPage(map, driver, "Female", "Texas", "Los Angeles", "Saving");

			//click on submit button
			OAObj.clickSubmitBtn();
	       Thread.sleep(2000);
	       
			//click on confirm button 
			
			OAcnfm.clickConfirmBtn();

			//handle the alert popup 
	        Thread.sleep(2000);
	        String alertText = wObj.alertGetText(driver);
	        System.out.println(alertText);
	        wObj.alertAccept(driver);
	        
	        System.out.println("=====OEPN ACCOUNT SMOKE TEST CASE SUCESSFULL======");
			
		}

	}

	
	


