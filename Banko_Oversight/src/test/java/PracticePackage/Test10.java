package PracticePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test10 {


	public static void main(String[] args) {


		WebDriver driver = new ChromeDriver();
		driver.get("https://google.com/");

//		SearchContext element = driver.findElement(By.cssSelector("settings-ui")).getShadowRoot()
//	                            .findElement(By.cssSelector("settings-menu")).getShadowRoot()
//	                         .findElement(By.xpath("//title[contains(text(),'Autofill and passwords')]"));
		

//  Actions a = new Actions(driver);
//  a.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
		driver.switchTo().newWindow(WindowType.TAB);
  
  
	



	}

}

