package Com.ObjectRepo;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InternetBankingLoginPage {
	
	//initialization
	@FindBy(name="login-btn")
	private WebElement loginBtn;

	
	
	//declaration
	public InternetBankingLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	//businessLogic
		
		public void enterInternetBankingLoginDetails(WebDriver driver, HashMap<String, String> map ) throws AWTException, InterruptedException {
			

			for(Entry<String, String> set1:map.entrySet()) {

				driver.findElement(By.name(set1.getKey())).sendKeys(set1.getValue());

			}
		
		
		
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	
}
