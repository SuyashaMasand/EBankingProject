package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCustomerByAccNo {

	//declaration
	@FindBy(name="submit_view")
	private WebElement submitBtn;



	//initialization
	public ViewCustomerByAccNo(WebDriver driver) {
		PageFactory.initElements(driver, this);

		
	}
	
	//business logic
	public void enterCustomerAccNum() {
		
	}
	
	public void clickSubmitBtn() {
		submitBtn.click();
		
	}





}
