package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{ //ITestListener is a testng method used in post action after tests are executed

	//these are the classes used to generate report once execution is done
	public ExtentSparkReporter sparkReporter; //this is used for UI for the report
	public ExtentReports extent; //this is used for common things like Env, project name in report
	public ExtentTest test; //this is used for enteries in report which test case is passed or failed
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName = "Test-Report-"+timeStamp+".html";
		
		sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of Report
		sparkReporter.config().setReportName("Pet Store Users API"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pest Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name") );
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user", "Shubham");
		
	}
	
    public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
		}
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
   public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
   
   public void onFinish(ITestContext testContext) {
	   
	   extent.flush(); //this flush method will keep everything ready in report as per above methods
	   //If you do not call this flush method all above actions will not be created in report or not generate the report
   }
	
}
