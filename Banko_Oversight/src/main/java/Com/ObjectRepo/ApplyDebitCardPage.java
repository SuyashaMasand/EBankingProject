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

public class ApplyDebitCardPage {
    
	//initialization
	@FindBy(name="dbt_crd_submit")
	private WebElement debitCardSubmitBtn;

	
	
	//Declaration 
	public ApplyDebitCardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//business Logic
	/**
	 * This method is used to enter the debit card details in debit card page
	 * @param driver
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public void EnterDetailsOfDebitCardPage(WebDriver driver, HashMap<String, String> map) throws AWTException, InterruptedException {
		
		
		for(Entry<String, String> set1:map.entrySet()) {

			driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());

		}



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
		
		
	}
	
	
	public String[] fetchingDCPinAndDCNo(String debitcardtext) {
		
		String DebitCard ="";
		char[] ch2 = debitcardtext.toCharArray();  

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
		System.out.println("debitcardnum"+DebitCardNum);
		System.out.println("debitcardpin"+DebitCardPin);
		String[] DB = new String[2];
		DB[0] =DebitCardNum;
		DB[1]=DebitCardPin;
		return DB;
		
		
	}
	
	public void ClicksubmitButton() {
		debitCardSubmitBtn.click();
		
	}
	
	
}
