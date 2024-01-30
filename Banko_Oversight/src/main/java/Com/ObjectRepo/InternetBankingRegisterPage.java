package Com.ObjectRepo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InternetBankingRegisterPage {
	
	//initialization
	@FindBy(name="submit")
	private WebElement submitBtn;
	
	@FindBy(name="dob")
	private WebElement dobLocator;


	
	//declaration
	public InternetBankingRegisterPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
	
	}
	
	//business Logic
	
	public void enterDetailsOfInternetBankingRegister(WebDriver driver, HashMap<String, String> map ) throws AWTException, InterruptedException {
		

		for(Entry<String, String> set1:map.entrySet()) {

			driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());

		}
		
		dobLocator.click();
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

		
	}
	
	public String fetchCustimerID(String IBREgisterText) {
		
		 String custID="";
         char[] ch = IBREgisterText.toCharArray();
         
         for(int i=0;i<ch.length;i++) {
      	   
      	   if(Character.isDigit(ch[i])) {
      		   custID = custID+ch[i];
      	   }
      	   
         }
        
   // System.out.println("customer ID is :"+custID);
  return custID;
        
       
		
	}
	public void clickSubmit() {
		submitBtn.click();
		
	}
	
	
	
	
	

}
