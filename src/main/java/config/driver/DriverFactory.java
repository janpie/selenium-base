package config.driver;

import enums.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import logger.TestLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

  private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();

  private static Supplier<WebDriver> chromeSupplier =
      () -> {
        WebDriverManager.chromedriver().arch64().setup();
        return new ChromeDriver(CapabilitiesProvider.getChromeCapabilites());
      };

  private static Supplier<WebDriver> firefoxSupplier =
      () -> {
        WebDriverManager.firefoxdriver().arch64().setup();
        return new FirefoxDriver(CapabilitiesProvider.getFirefoxCapabilities());
      };

  static {
    driverMap.put(DriverType.CHROME, chromeSupplier);
    driverMap.put(DriverType.FIREFOX, firefoxSupplier);
  }

  public static WebDriver startDriver(DriverType driverType) {
    Optional<String> browser = Optional.ofNullable(System.getProperty("browser"));
    if (browser.isPresent()) {
      TestLogger.log.info("---------Initializing " + browser.get().toUpperCase() + " browser -------");
      return driverMap.get(DriverType.valueOf(browser.get().toUpperCase())).get();
    } else {
      TestLogger.log.info("---------Initializing " + driverType.toString().toUpperCase() + " browser-------");
      return driverMap.get(driverType).get();
    }
  }
}
