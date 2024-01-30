package Assignment;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Com.genericUtility.WebDriverUtils;

public class IRCTC_AssignmentTest {
	
	
	@Test
	public void IrctcAssignment() throws InterruptedException, AWTException {
		
		
		//create object of all the webdriver utils 
		WebDriverUtils wObj = new WebDriverUtils();
	
		//handle notification popup
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//open the  browser
		WebDriver driver = new ChromeDriver(options);
		
		//maximize the browser
		wObj.maximizeWindow(driver);
		
		//enter the url
		driver.get("https://www.irctc.co.in/");
		
		//give page load time out
		wObj.pageLoadtimeOut(driver, 10);
		
		//click on bus
		Thread.sleep(3000);
	     driver.findElement(By.xpath(" //a[text()=' BUSES ']")).click();
	     
	     
	     //switch to window 
	      Set<String> allwh = driver.getWindowHandles();
	       for(String wh:allwh) {
	    	  
	          
	    	 driver.switchTo().window(wh);
	    	    }
	       
	     //give depart from
	     driver.findElement(By.id("departFrom")).sendKeys("Bhopal", Keys.ENTER);
	     Thread.sleep(2000);
	     
	     driver.findElement(By.xpath("//div[text()='Bhopal']")).click();
	     
	     //enter data into going to 
	     driver.findElement(By.id("goingTo")).sendKeys("Indore", Keys.ENTER);
	     Thread.sleep(2000);
	     driver.findElement(By.xpath("//div[text()='Indore']")).click();
	     
	     //enter departure date 
	     
	     driver.findElement(By.id("departDate")).click();
	     driver.findElement(By.xpath("//a[text()='25']")).click();
	     
	     //click on search buses
	     driver.findElement(By.xpath("//button[text()='Search Bus ']")).click();
	     
	     //give wait statement 
	     Thread.sleep(5000);
	     
	     //click on after 6pm 
	     driver.findElement(By.xpath("//h4[text()='Departure Time']/ancestor::div[@class='heading-02-wrap']/descendant::label[text()='After 6 pm']")).click();
	     
	     //click on select seat 
	     driver.findElement(By.xpath("(//p[text()='Electric A/C Seater (2+2)']/../../descendant::button[text()='Select Seat'])[1]")).click();
	     
	     Thread.sleep(2000);
	     //scroll to element 
//	     WebElement ele = driver.findElement(By.xpath("//td[text()=' Sehore Bypass  ']"));
//	     Actions a = new Actions(driver);
//	     a.scrollToElement(ele);
	     //select the seat 
	     driver.findElement(By.xpath("//div[@class='lower-deck-bottomwrap']/descendant::span[@title='Seat No. : 3D | Fare : INR 447.00']")).click();
	     
	     //click on boarding time 
	     driver.findElement(By.xpath("//td[text()=' ISBT Bhopal (Verma Travels) (Pickup Van)  ']/../descendant::input[@name='bordTime']")).click();
	     
	     //click on droping point 
	     driver.findElement(By.xpath("//td[text()=' Radisson Square (C21 Biz Park) ']/../descendant::input[@name='debordTime']")).click();
	     
	     
	;     //click on proceed button 
	     driver.findElement(By.xpath("//button[text()='Proceed to Book']")).click();
	     
	     //click on login as guest 
	     driver.findElement(By.xpath("//a[text()='Guest User Login ']")).click();
	     
	     
	     Thread.sleep(2000);
	     //enter email id
	     driver.findElement(By.xpath("//input[@name='emailLogin']")).sendKeys("suyashamasand@gmail.com");
	     
	     //enter password
	     driver.findElement(By.xpath("//input[@name='phoneLogin']")).sendKeys("9876453215");
	     
	     //click on login button 
	     driver.findElement(By.xpath("(//div[@class='text-center']/button[text()='Login'])[2]")).click();
	     
	     Thread.sleep(10000);
	     
	     Robot r = new Robot();
	     r.keyPress(KeyEvent.VK_TAB);
	     r.keyPress(KeyEvent.VK_TAB);



	     
	     
	     //enter the details 
	     driver.findElement(By.xpath("//input[@name='mobileno']")).sendKeys("8085580288" , Keys.TAB);
	     driver.findElement(By.name("address")).sendKeys("E-403,fortune signature");
	    driver.findElement(By.name("pincode")).sendKeys("462049");
	    driver.findElement(By.xpath("//input[@placeholder='Traveller Name']")).sendKeys("Harsh Masand");
	     driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys("31");
	     
	   
	    
	    WebElement country = driver.findElement(By.name("country"));
	     wObj.selectText("India", country);
	  
	   WebElement state = driver.findElement(By.name("state"));
	   wObj.selectText("MADHYA PRADESH", state);
   
	   WebElement selectGender = driver.findElement(By.name("select"));
	   wObj.selectText("Male", selectGender);
	   
	   driver.findElement(By.name("agree")).click();
	   
//	   //click on continue to book 
	   driver.findElement(By.xpath("//button[text()='Continue to Book ']")).click();
		
		
	}

}
