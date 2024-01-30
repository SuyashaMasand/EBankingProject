package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StaffHomePage {
	
	//initialization
	@FindBy(name="home")
	private WebElement homeBtn;
	
	@FindBy(name="logout_btn")
	private WebElement logoutBtn;
	
	@FindBy(name="viewdet")
	private WebElement viewACDetails;
	
	@FindBy(name="view_cust_by_ac")
	private WebElement viewCustByAC;
	
	@FindBy(name="apprvac")
	private WebElement approveCust;
	
	@FindBy(name="del_cust")
	private WebElement delCust;
	
	@FindBy(name="credit_cust_ac")
	private WebElement creditCustAc;

	
	//declaration
	public StaffHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//Business Logic
	public void clickStaffHome() {
		homeBtn.click();
	}
	
	public void clickStaffLogout() {
		logoutBtn.click();
	}
	
	public void clickViewActiveCustomer() {
		viewACDetails.click();
		
	}
	
	public void clickViewCustomerByAcNo() {
		viewCustByAC.click();
		
	}
	
	public void clickApprovePendingAccount() {
		approveCust.click();
	}
	
	public void clickDeleteCustomerAc() {
		delCust.click();
	}
	
	public void clickCreditCustomer() {
		creditCustAc.click();
	}
	
	

}
