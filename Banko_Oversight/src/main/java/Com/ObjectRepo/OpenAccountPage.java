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

import Com.genericUtility.JavaUtils;
import Com.genericUtility.WebDriverUtils;

public class OpenAccountPage extends JavaUtils{

	//declaration
	@FindBy(name="submit")
	private WebElement submitBtn;

	@FindBy(name="gender")
	private WebElement genderLocator;

	@FindBy(name="state")
	private WebElement stateLocator;

	@FindBy(name="city")
	private WebElement cityLocator;

	@FindBy(name="acctype")
	private WebElement acctypeLocator;

	@FindBy(name="dob")
	private WebElement dobLocator;

	//initialization 
	public OpenAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//business logic
	public String[] EnterAllDataInOpenAccountPage(HashMap<String, String> map, WebDriver driver, String enterGender, String enterState, String enterCity, String enterAcctype) throws AWTException, InterruptedException {

		String AccHolderName ="";
        String creditAmt ="500";
        String PanNum="";
        String MobNo="";
        
		for(Entry<String, String> set:map.entrySet()) {

			if(set.getKey().equals("name")) {
				JavaUtils ju= new JavaUtils();
				int random = ju.getRandomNo();
				AccHolderName = set.getValue()+random; 
				//driver.findElement(By.name(set.getKey())).sendKeys(set.getValue()+random);
				driver.findElement(By.name(set.getKey())).sendKeys(AccHolderName);
             
			}
//			else if (set.getKey().contains("credit")) {
//				
//				creditAmt = set.getValue();
//				driver.findElement(By.name(set.getKey())).sendKeys(creditAmt);
//				}
              else if (set.getKey().contains("pan")) {
				
				PanNum = set.getValue();
				driver.findElement(By.name(set.getKey())).sendKeys(PanNum);
				}
              else if (set.getKey().contains("mob")) {
  				
            	  MobNo = set.getValue();
  				driver.findElement(By.name(set.getKey())).sendKeys(MobNo);
  				}
			
			
			else
			{
				driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
			}
		}
		
		System.out.println(AccHolderName);

		System.out.println("Acc HOLDER NAME IS:"+AccHolderName);
		System.out.println("CREDIT AMT:" +creditAmt);


		WebDriverUtils wObj = new WebDriverUtils();
		wObj.selectText(enterGender, genderLocator);

		//WebElement gender = driver.findElement(By.name("gender"));
		//wObj.selectText("Female", gender);
		//		Select s = new Select(gender);
		//		s.selectByVisibleText("Female");

		wObj.selectText(enterState, stateLocator);
		//WebElement state = driver.findElement(By.name("state"));
		//wObj.selectText("Texas", state);
		//		Select s1= new Select(state);
		//		s1.selectByVisibleText("Texas");

		wObj.selectText(enterCity, cityLocator);
		//WebElement city = driver.findElement(By.name("city"));
		//wObj.selectText("Los Angeles", city);
		//		Select s2 = new Select(city);
		//		s2.selectByVisibleText("Los Angeles");

		wObj.selectText(enterAcctype ,acctypeLocator );
		//WebElement acctype = driver.findElement(By.name("acctype"));
		//wObj.selectText("Saving", acctype);
		//		Select s3 = new Select(acctype);
		//		s3.selectByVisibleText("Saving");

		dobLocator.click();
	//	driver.findElement(By.name("dob")).click();
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
		
		String[] s  = new String[4];
		s[0]= AccHolderName;
		s[1]= creditAmt;
		s[2]= PanNum;
	    s[3]=MobNo;
		return s;
	}
	


	public void clickSubmitBtn() {
		submitBtn.click();

	}
	
	public String getApplicationNum(String getAlertTextRef) {
		
	   String appnum="";
		char[] ch = getAlertTextRef.toCharArray();  

		for(int i=0;i<ch.length;i++) {

			if(Character.isDigit(ch[i])) {
				appnum = appnum+ ch[i];
			}

		}
	return appnum;

		
	}

}


