package config.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

class CapabilitiesProvider {

  static FirefoxOptions getFirefoxCapabilities() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    FirefoxProfile profile = new FirefoxProfile();
    firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL);
    firefoxOptions.addPreference("security.insecure_field_warning.contextual.enabled", false);
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    return firefoxOptions;
  }

  static ChromeOptions getChromeCapabilites() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    chromeOptions.addArguments("--log-level=3");
    chromeOptions.addArguments("--window-size=1100,1000");
    return chromeOptions;
  }
}
