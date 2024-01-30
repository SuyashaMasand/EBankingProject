package Com.genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplimentationClass implements ITestListener {

	
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		
		
		//configure report 
		JavaUtils jObj = new JavaUtils();
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(".\\extentReport\\report"+jObj.getSystemDateFormat()+".html");
		htmlreport.config().setDocumentTitle("EBANKIG_TYPROJECT");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("BankoOversight");
		
	   report = new ExtentReports();
	   report.attachReporter(htmlreport);
	   report.setSystemInfo("Base Platform", "OS");
	   report.setSystemInfo("Base Browser", "Chrome");
	   report.setSystemInfo("Base-URL", "http://rmgtestingserver/domain/Online_Banking_System/");
	   report.setSystemInfo("RepoerterName", "SUYASHA");
	}

	
	@Override
	public void onTestStart(ITestResult result) {
		
		
		//TestScript execution starts from here 
		String MethodName = result.getMethod().getMethodName();
		test = report.createTest(MethodName);
		Reporter.log(MethodName+" Execution Started ", true);
		
	
	   
	   }

	@Override
	public void onTestSuccess(ITestResult result) {
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		
		try {
			
			String path =Screenshot.sc(BaseClass.driver, MethodName);
			
			test.addScreenCaptureFromPath(path);
			test.log(Status.FAIL, MethodName+" Failed ");
			test.log(Status.FAIL, result.getThrowable());
			Reporter.log(MethodName+" ----> Failed");
			} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String MethodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, MethodName+" Skipped");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(MethodName+" ----> Skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		
		report.flush();
		
	}
	
	
	
	
	
	

}
