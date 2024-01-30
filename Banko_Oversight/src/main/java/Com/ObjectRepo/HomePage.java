package Com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.genericUtility.WebDriverUtils;

public class HomePage {
    
	//Declaration
	
	@FindBy(xpath="//li[text()='Open Account']")
	private WebElement openAccount;
	
	@FindBy(xpath="//li[text()='Apply Debit Card']")
	private WebElement applyDebitCard;
	
	@FindBy(xpath="//a[contains(text(),'Internet Banking')]")
	private WebElement internetBanking;
	
	@FindBy(xpath="//li[text()='Login ']")
	private WebElement IBLogin;
	
	@FindBy(xpath="//li[text()='Register']")
	private WebElement IBRegister;
	
	@FindBy(xpath="//li[text()='Fund Transfer']")
	private WebElement FundTransfer;

	
	//initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
	
	
	
    //Business Logic
	public void clickOpenAccount() {
		openAccount.click();;
	}


	public void clickApplyDebitCard() {
		applyDebitCard.click();
	}


    public void MouseHoverToInternetBankingAndClickLogin(WebDriverUtils wdu, WebDriver driver) {
    	wdu.mouseHover(driver, internetBanking);
    	IBLogin.click();
    	
	}
    
    public void MouseHoverToInternetBankingAndClickRegister(WebDriverUtils wdu, WebDriver driver) {
    	wdu.mouseHover(driver, internetBanking);
    	IBRegister.click();
    	
	}


	public void clickFundTransfer() {
		FundTransfer.click();
	}

}
