package PracticePackage;

	
	
	
	import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import Com.ObjectRepo.StaffLoginPage;
import Com.genericUtility.WebDriverUtils;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		openAccountSmoke();
	}
		public static void openAccountSmoke() throws IOException, InterruptedException, AWTException {

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



			//launch the browser
			WebDriver driver = new ChromeDriver();
            StaffLoginPage slp = new StaffLoginPage(driver);
            
			
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
			for(Entry<String, String> set:map.entrySet()) {

				if(set.getKey().equals("name")) {
	                 
					AccHolderName = set.getValue()+random;
					driver.findElement(By.name(set.getKey())).sendKeys(AccHolderName);

				}
				else
				{
					driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
				}
			}
			
			//------------********--------FETCH NAME -------//
			//FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx.");
			//Workbook wb = WorkbookFactory.create(fis);
			
			Sheet sh = wb.getSheet("Data");
			for(int i=1;true;i++) {
			sh.getRow(0).createCell(i).setCellValue(AccHolderName);
			
			
			FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\testdata.xlsx");
			wb.write(fos);
			wb.close();
			break;
			}
			
			String pan = wb.getSheet("OpenAccount").getRow(4).getCell(1).getStringCellValue();
			Thread.sleep(2000);
			wb.getSheet("DebitCard1").getRow(1).getCell(1).setCellValue(pan);
			
			String mob = wb.getSheet("OpenAccount").getRow(1).getCell(1).getStringCellValue();
			Thread.sleep(2000);
			wb.getSheet("DebitCard1").getRow(2).getCell(1).setCellValue(mob);
			
			
			//------------------------------------------------------------------------------------------------//


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

			//click on confirm button 
			driver.findElement(By.name("cnfrm-submit")).click();

			//handle the alert popup 
	        Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();

			String alertText = alert.getText();
			System.out.println(alertText);
	//
//			String appnum =" ";
//			char[] ch = alertText.toCharArray();  
	//
//			for(int i=0;i<ch.length;i++) {
	//
//				if(Character.isDigit(ch[i])) {
//					appnum = appnum+ ch[i];
//				}
	//
//			}
//			System.out.println("ApplicationNumberIs:" +appnum);
	//
//	        Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			
		//click on logout 
			//driver.findElement(By.name("logout_btn")).click();
			

		}

	}



