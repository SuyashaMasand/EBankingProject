package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage{

	//Declaration
	@FindBy(linkText="Home")
	private WebElement home;

	@FindBy(linkText="About Us")
	private WebElement aboutUs;

	@FindBy(linkText="Contact Us")
	private WebElement contactUs;

	@FindBy(linkText="Staff Login")
	private WebElement staffLogin;

	//initialization
	public DashBoardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}


	//Business logic
	public void clickOnHome() {
		home.click();
	}

	public void clickAboutUs() {
		aboutUs.click();
	}

	public void clickContactUs() {
		contactUs.click();
	}

	public void clickStaffLogin() {
		staffLogin.click();
	}
}


