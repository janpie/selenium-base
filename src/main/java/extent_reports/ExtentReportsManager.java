package extent_reports;

import static constants.Constants.EXTENT_REPORT_FILE_NAME;
import static constants.Constants.EXTENT_REPORT_PATH;
import static constants.Constants.USER_DIR;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReportsManager {
  private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();
  private static ExtentReports extent = startReporter(EXTENT_REPORT_PATH, EXTENT_REPORT_FILE_NAME);

  public static ExtentTest startTest(String testName, String desc) {
    ExtentTest test = extent.startTest(testName, desc);
    extentTestMap.put(Thread.currentThread().getId(), test);
    return test;
  }

  public static ExtentTest startTest(String testName) {
    return startTest(testName, "");
  }

  public static void logInfo(String text) {
    getTest().log(LogStatus.INFO, text);
  }

  public static void logPass(String text) {
    getTest().log(LogStatus.PASS, text);
  }

  public static void logFail(String text, @Nullable Throwable t) {
    getTest().log(LogStatus.FAIL, text, t);
  }

  public static void logFail(String text) {
    logFail(text, null);
  }

  public static void logSkip(String text, @Nullable Throwable t) {
    getTest().log(LogStatus.SKIP, text, t);
  }

  public static void logSkip(String text) {
    logSkip(text, null);
  }

  public static void addScreenshot(WebDriver driver, LogStatus logStatus, String message) {
    String base64Screenshot =
        "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    getTest().log(logStatus, message, getTest().addBase64ScreenShot(base64Screenshot));
  }

  public static void endTest() {
    Optional.ofNullable(extent).ifPresent(test -> test.endTest(getTest()));
  }

  public static void flushReporter() {
    Optional.ofNullable(extent).ifPresent(ExtentReports::flush);
  }

  private static ExtentReports startReporter(String reportPath, String fileName) {
    return Optional.ofNullable(extent)
        .orElse(new ExtentReports(USER_DIR + reportPath + fileName, true, NetworkMode.OFFLINE));
  }

  private static ExtentTest getTest() {
    return Optional.ofNullable(extentTestMap.get(Thread.currentThread().getId()))
        .orElseThrow(
            () ->
                new ExtentTestInterruptedException(
                    "No started test found. Start test before logging result"));
  }
}
