package Com.ObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.genericUtility.FileUtility;


public class StaffLoginPage {
	
	//initialization
	@FindBy(name="staff_id")
	private WebElement staffID;
	
    @FindBy(name="password")
	private WebElement staffPwd;
    
    @FindBy(name="staff_login-btn")
    private WebElement loginBtn;
	
    
    //declaration
    public StaffLoginPage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
	}
    
    //business Login 
    public void EnterstaffLoginDetails(String staffid, String staffpwd) {
    	staffID.sendKeys(staffid);
    	staffPwd.sendKeys(staffpwd);
    	loginBtn.click();
     }
    
	
	

}
