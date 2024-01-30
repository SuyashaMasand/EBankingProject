package com.BankoOversight.InternetBanking.TestScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class InternetBanking_Smoke {
	
	@Test
	public void internetBanking() throws InterruptedException, IOException {
		
	       	//open browser maximize and implicit wait

				WebDriver driver=new ChromeDriver();

				driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

				

				//entering the url

				FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\commondata.properties");

				Properties p=new Properties();

				p.load(fi);

				String URL = p.getProperty("url");

				driver.get(URL);

				

				//Mouse hover on Internet banking and click on login

				WebElement ibanking = driver.findElement(By.xpath("//a[contains(text(),'Internet Banking')]"));

				Actions a=new Actions(driver);

				a.moveToElement(ibanking).perform();

				Thread.sleep(2000);

				

				WebElement login = driver.findElement(By.xpath("//li[text()='Login ']"));

				a.click(login).perform();

				

				//login using customer id and password
				

				driver.findElement(By.name("customer_id")).sendKeys("1011513");

				driver.findElement(By.name("password")).sendKeys("password");

				driver.findElement(By.name("login-btn")).click();
		
		
		
		
		
	}

}
