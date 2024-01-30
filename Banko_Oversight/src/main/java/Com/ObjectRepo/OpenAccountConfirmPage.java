package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenAccountConfirmPage {

	
	
	//declaration
	@FindBy(name="cnfrm-submit")
	private WebElement comfirmBtn;
	
	@FindBy(xpath="//input[@value='Go back']")
	private WebElement gobackBtn;
	
	//initialization
	public OpenAccountConfirmPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void clickConfirmBtn() {
		comfirmBtn.click();
		
	}
	
	public void clickGoBackBtn() {
		gobackBtn.click();
		
		
	}

}
