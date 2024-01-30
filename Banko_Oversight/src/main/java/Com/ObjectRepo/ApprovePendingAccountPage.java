package Com.ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApprovePendingAccountPage {
	
	//initialization
	@FindBy(name="search_application")
	private WebElement searchBtn;
	
	@FindBy(name="approve_cust")
	private WebElement approveCustBtn;

	
	//@FindBy(xpath="//div[@class='pending_customers_container']/descendant::tbody/tr[2]/td[2]")
	//declaration
	public ApprovePendingAccountPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	
	



	//business logic
	public void enterApplicationNo( WebDriver driver, String appnum) {
		

        driver.findElement(By.name("application_no")).sendKeys(appnum);

		driver.findElement(By.name("search_application")).click();
		WebElement applicationNo=null;

		 applicationNo = driver.findElement(By.xpath("//div[@class='pending_customers_container']/descendant::tbody/tr[2]/td[2]"));

		if(applicationNo!=null) {

			driver.findElement(By.name("approve_cust")).click();
			System.out.println("---ACCOUNT APPROVED-------");

		}
		else {
			driver.switchTo().alert().accept();
			//alert.accept();
			System.out.println("-----ACCOUNT NOT APPROVED-----");
		}
		
		
	}
	
	public void clickSeachBtn() {
		searchBtn.click();
		
	}
	
	public void clickApproveBtn() {
		approveCustBtn.click();
		
	}
	
	public String getAccountNum(String alertTextref) {
		
          String accnum ="";
		
		
		char[] ch1 = alertTextref.toCharArray();  

		for(int i=0;i<ch1.length;i++) {

			if(Character.isDigit(ch1[i])) {
				accnum = accnum+ ch1[i];
			}

		}
		
	return accnum;

		
	}
	
	
	
	

}
