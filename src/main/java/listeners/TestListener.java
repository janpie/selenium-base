package listeners;

import com.relevantcodes.extentreports.LogStatus;
import extent_reports.ExtentReportsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test_base.TestBase;

public class TestListener implements ITestListener{

  @Override
  public void onTestStart(ITestResult result) {
    ExtentReportsManager.startTest(result.getTestContext().getName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentReportsManager.logPass("Test Passed");
    ExtentReportsManager.endTest();
  }

  @Override
  public void onTestFailure(ITestResult result) {
    Object baseClass = result.getInstance();
    WebDriver webDriver = ((TestBase) baseClass).getDriver();
    ExtentReportsManager.logFail("Test Failed", result.getThrowable());
    ExtentReportsManager.addScreenshot(webDriver, LogStatus.FAIL, "Failure snapshot: ");
    ExtentReportsManager.endTest();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ExtentReportsManager.logSkip("Test Skipped", result.getThrowable());
    ExtentReportsManager.endTest();
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

  @Override
  public void onStart(ITestContext context) {}

  @Override
  public void onFinish(ITestContext context) {
    ExtentReportsManager.flushReporter();
  }
}
