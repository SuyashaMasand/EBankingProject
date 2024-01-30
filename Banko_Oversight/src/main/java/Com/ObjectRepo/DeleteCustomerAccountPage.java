package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteCustomerAccountPage {
	//declaration
	@FindBy(name="delete")
	private WebElement deleteBtn;

	
	//initialization
	public DeleteCustomerAccountPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	//business Logic
	
	public void enterDeleteDetails() {
		
	}
	
	public void clickDeleteBtn() {
		deleteBtn.click();
		
	}
	
	
	
	

}
