package PracticePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Com.ObjectRepo.DashBoardPage;
import Com.ObjectRepo.StaffLoginPage;

public class Test1 {
	
	public static void main(String[] args) throws IOException {
		
	
	
	//get the data from property file
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commondata.properties");
	Properties p = new Properties();
	p.load(fis);
	String URL = p.getProperty("url");
	String STAFFID = p.getProperty("staffID");
	String STAFFPWD = p.getProperty("staffPwd");
	
	WebDriver driver = new ChromeDriver();
	
	driver.get(URL);
	
	DashBoardPage dbp = new DashBoardPage(driver);
	dbp.clickStaffLogin();
	
	StaffLoginPage lp = new StaffLoginPage(driver);
   lp.EnterstaffLoginDetails(STAFFID, STAFFPWD);
	
	
	
	

}
}