package Com.genericUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	
	//object creation for all the util classes
	public JavaUtils jObj = new JavaUtils();
	public WebDriverUtils wObj = new WebDriverUtils();
	public FileUtility fObj = new FileUtility();
	public ExcelUtils eObj = new ExcelUtils();
	public DatabaseUtils dObj = new DatabaseUtils();
	public static WebDriver driver;
	//public WebDriver driver;
	//public static WebDriver sdriver;
	
	
	@BeforeSuite(alwaysRun = true)
	public void configDB() throws SQLException {
	
		dObj.getConnection();
		Reporter.log("===OPEN DB CONNECTION=====");
	}
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void configBC() throws IOException {
		Reporter.log("====LAUNCHING THE BROWSER=====");
		FileUtility fObj = new FileUtility();
	    String URL = fObj.readDataFromProperty("url");
//	    sdriver = driver;
	  driver = new ChromeDriver();
//	     driver = new FirefoxDriver();
//	    if(value.equalsIgnoreCase("chrome"))
//	    {
//	    	driver = new ChromeDriver();
//	    	System.out.println("chrome is running");
//	    }
//	   else if(value.equalsIgnoreCase("firefox"))
//	    {
//	    	driver = new FirefoxDriver();
//	    	System.out.println("Firefox is running");
//	    }
//	    else {
//	    	System.out.println("===BROWSER IS NOT OPENING======");
//	    }
	    	
		wObj.maximizeWindow(driver);
		driver.get(URL);
		wObj.pageLoadtimeOut(driver, 10);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void configAC() {
		Reporter.log("=====CLOSING THE BROWSER=====");
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void configAS() throws SQLException {
		dObj.closeConnection();
		Reporter.log("=====CLOSING DB CONNECTION");
		
	}
	
	
	
	
	

}
