package tests;

import static org.assertj.core.api.Assertions.assertThat;

import config.driver.DriverFactory;
import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.Test;

public class SampleTest {

  @Test
  public void sampleTest(ITestContext method) throws Exception {
    WebDriver driver = DriverFactory.startDriver(DriverType.CHROME);
    assertThat(driver).isNotNull();
    System.out.println(method.getSuite().getParallel());
    driver.quit();
  }
}
