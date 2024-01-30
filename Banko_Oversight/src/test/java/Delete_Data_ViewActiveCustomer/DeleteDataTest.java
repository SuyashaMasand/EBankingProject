package Delete_Data_ViewActiveCustomer;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DeleteDataTest {

	@Test
	public void deleteData() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		driver.get("http://rmgtestingserver/domain/Online_Banking_System/index.php");

		driver.findElement(By.xpath("//a[text()='Staff Login']")).click();

		driver.findElement(By.name("staff_id")).sendKeys("210001");

		driver.findElement(By.name("password")).sendKeys("password");
		
		driver.findElement(By.name("staff_login-btn")).click();


		for(int i=500;i>=1;i--) {

			driver.findElement(By.name("viewdet")).click();


			String custID = driver.findElement(By.xpath("//div[@class='active_customers_container']/descendant::tr["+i+"]/td[3]")).getText();
			String custAccNum = driver.findElement(By.xpath("//div[@class='active_customers_container']/descendant::tr["+i+"]/td[4]")).getText();
			
			driver.findElement(By.name("home")).click();
			
			driver.findElement(By.name("del_cust")).click();
			
			driver.findElement(By.name("Cust_ac_no")).sendKeys(custAccNum);
			driver.findElement(By.name("Cust_ac_Id")).sendKeys(custID);
			driver.findElement(By.name("reason")).sendKeys("NOT NEEDED");
			
			
			driver.findElement(By.name("delete")).click();
			
			try {
				
				Alert ALERT = driver.switchTo().alert();
				String AlertText = ALERT.getText();
				System.out.println(AlertText);
				ALERT.accept();
				
			} catch (Exception e) {
				
				//System.out.println( "ALERT NOT PRESRNT");
			}
			
			driver.findElement(By.name("home")).click();
		}

	}

}
