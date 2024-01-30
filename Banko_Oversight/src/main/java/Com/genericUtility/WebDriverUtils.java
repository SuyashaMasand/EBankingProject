package Com.genericUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains all the WebDriver methods
 */
public class WebDriverUtils {


	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}




	/**
	 * This method is used to minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}



	/**
	 * This method is used to wait for seconds given by user to load the page
	 * @param driver
	 * @param sec
	 */
	public void pageLoadtimeOut(WebDriver driver, int sec)
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}



	/**
	 * This method is used to wait for seconds given by user to load page
	 * and can be used instead of pageLoadTimeOut
	 * @param driver
	 * @param sec
	 */
	public void implicitWit(WebDriver driver,int sec)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}



	/**
	 * This method is used to wait until the time it checks alert is present using explicit wait.
	 * @param driver
	 * @param sec
	 */
	public void alertIsPresent(WebDriver driver , int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.alertIsPresent());
	}



	/**
	 * This method is used to wait until the element is visible,
	 * @param driver
	 * @param sec
	 * @param element
	 */
	public void visibilityOfElement(WebDriver driver, int sec,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	/**
	 * This method waits until element is present to perform click action.
	 * @param driver
	 * @param element
	 * @param sec
	 */
	public void elementToBeClickable(WebDriver driver,WebElement element ,int sec)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}



	/**
	 * This method is used to select the element
	 * @param element
	 * @return
	 */
	public Select selectAnElement(WebElement element) {
		Select  s = new Select(element);
		return s;
	}
	
	
	/**
	 * This method will select the data from dropdown using Index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index)
	{

		selectAnElement(element).selectByIndex(index);
	}



	/**
	 * This method will select the element from dropdown using value.
	 * @param element
	 * @param Data
	 */
	public void select(WebElement element, String Data)
	{
		selectAnElement(element).selectByValue(Data);
	}


	/**
	 * This method will select the element from the dropdown using Text.
	 * @param text
	 * @param element
	 */
	public void selectText(String text,WebElement element)
	{
		
		selectAnElement(element).selectByVisibleText(text);
	}



	/**
	 * This method is used to deselect an element from the dropdown using index.
	 * @param element
	 * @param index
	 */
	public void deselectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.deselectByIndex(index);
	}



	/**
	 * This method is used to deSelect an element from the dropDown using value
	 * @param element
	 * @param value
	 */
	public void deselectByValue(WebElement element , String value) {
		Select s = new Select(element);
		s.deselectByValue(value);
	}




	/**
	 * This method is used to deSelect all the selected data from the dropDown.
	 * @param element
	 */
	public void deselectAll(WebElement element) {
		Select s = new Select(element);
		s.deselectAll();

	}



	/**
	 * This method is used to mouse hover action.
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	}



	/**
	 * This method is used to drag and drop action.
	 * @param driver
	 * @param element
	 * @param element1
	 */
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest)
	{
		Actions a=new Actions(driver);
		a.dragAndDrop(src, dest).perform();
	}



	/**
	 * This element is used to right click to a WebPage
	 * @param driver
	 */
	public void contextClick(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.contextClick().perform();
	}


	/**
	 * This method is used to right click on an element
	 * @param element
	 * @param driver
	 */
	public void contextClickElement(WebElement element,WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.contextClick(element).perform();
	}


	/**
	 * This method is used to double click on WebPage
	 * @param driver
	 */
	public void doubleClick(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.doubleClick().perform();
	}


	/**
	 * This method is used to double click on element
	 * @param driver
	 * @param element
	 */
	public void doubleClickElement(WebDriver driver,WebElement element)
	{
		Actions a=new Actions(driver);
		a.doubleClick(element);
	}


	/**
	 * This method is used to click on element by giving xaxis and yaxis
	 * @param driver
	 * @param element
	 * @param Xaxis
	 * @param Yaxis
	 */
	public void clikElement(WebDriver driver,WebElement element,int Xaxis,int Yaxis)
	{
		Actions a=new Actions(driver);
		a.dragAndDropBy(element,Xaxis, Yaxis).perform();
	}


	/**
	 * This method  is used to press enter key using keys class
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.sendKeys(Keys.ENTER).perform();
	}



	/**
	 * This method is used to press enter key using robot class.
	 * @throws AWTException
	 */
	public void enterKeyUsingRobot() throws AWTException
	{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}


	/**
	 * This method is used to key release
	 * @throws AWTException
	 */
	public void enterRelease() throws AWTException
	{
		Robot r=new Robot();
		r.keyRelease(KeyEvent.VK_ENTER);

	}



	/**
	 * This method is used to press mouse.
	 * @param button
	 * @throws AWTException
	 */
	public void mousePress(int button) throws AWTException {
		Robot r = new Robot();
		r.mousePress(button);
	}



	/**
	 * This method is used to release mouse
	 * @param button
	 * @throws AWTException
	 */
	public void mouseRelease(int button) throws AWTException {
		Robot r = new Robot();
		r.mouseRelease(button);
	}



	/**
	 * This method is used to switch the frame using index value
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
	    driver.switchTo().frame(index);
	}


	/**
	 * This method is used to switch the frame using element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);

	}


	/**
	 * This method is used to switch the frame using frame name or id
	 * @param driver
	 * @param name
	 */
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}



	/**
	 * This method is used to switch the frame using frame address
	 * @param address
	 * @param driver
	 */
	public void switchToFrame(String address,WebDriver driver)
	{
		driver.switchTo().frame(address);
	}

     
	/**
	 * This method is used to switch to alert
	 * @param driver
	 */
     public Alert switchToAlert(WebDriver driver) {
    	 Alert a = driver.switchTo().alert();
    	 return a;
     }
	
	/**
	 * This method is used to click on ok button on alert popup
	 * @param driver
	 */
	public void alertAccept(WebDriver driver)
	{
		Alert a = driver.switchTo().alert();
		a.accept();
	}


	/**
	 * This method is used to click on cancel button on alert popup
	 * @param driver
	 */
	public void alertDismiss(WebDriver driver)
	{
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}


	/**
	 * This method is used to enter data on  alert popup
	 * @param driver
	 * @param data
	 */
	public void alertSendKeys(WebDriver driver,String data)
	{
		Alert a = driver.switchTo().alert();
		a.sendKeys(data);
	}



	/**
	 * This method is used to get the text from  alert popup
	 * @param driver
	 */
	public String alertGetText(WebDriver driver)
	{
		Alert a = driver.switchTo().alert();
		String text = a.getText();
		return text;
	}




	/**
	 * This method switch between windows
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle)
	{
		//Use getWindowHandles to capture all the windows
		Set<String> windows = driver.getWindowHandles();

		//iterate over all the windows
		Iterator<String> it = windows.iterator();

		//check whether next window is there or not
		while(it.hasNext())
		{
			//capture current window ID
			String winId = it.next();

			//iterate over window based on that ID and capture the title
			String CurrentWinTitle = driver.switchTo().window(winId).getTitle();

			//check whether current window is as expected
			if(CurrentWinTitle.contains(partialWinTitle))
			{
				break;
			}
		}
	}



	/**
	 * This method will take  screenshot and stored it into a folder called as screenshot
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException 
	 */

	public static String getScreenShot(WebDriver driver, String screenShotName) throws Throwable
	{
		JavaUtils jObj= new JavaUtils();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/"+screenShotName+jObj.getSystemDateFormat()+".jpg";
		
		File dst = new File(path);
		FileUtils.copyFile(src, dst);
        return dst.getAbsolutePath();
	}


	/**
	 * This method is used scroll to the perticular element.
	 * @param driver
	 */
	public void scrollVertically(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	
	
	/**
	 * This method is used to scroll an element to a perticular pixel value.
	 * @param driver
	 * @param pixel
	 */
	public void scrollToElement(WebDriver driver , int pixel) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	
	/**
	 * This method will scroll to the bottom of window
	 * @param driver
	 */
	public void ScrollBottomOfWebPage(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.ScrollTo(0, document.body.scrollHeight)");
		
	}
	
	
	/**
	 * This method is used to scroll to the top of the window
	 * @param driver
	 */
	public void ScrollTopOfWebPage(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0)");
		
		
		
	}
	
	


}




