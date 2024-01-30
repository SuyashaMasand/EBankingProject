package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundTransferPage {
	
	//initialization
	@FindBy(name="login-btn")
	private WebElement loginbtnFundTrasfer;

	
	
	//declaration
	public FundTransferPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//business logic method 
	public void enterFundTransferDetails() {
		
	}
	
	public void clickOnLoginBtn() {
		loginbtnFundTrasfer.click();
		
	}
	

}
