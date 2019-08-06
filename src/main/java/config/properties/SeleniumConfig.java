package config.properties;

import static constants.Constants.MAIN_RESOURCES_DIR;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.Properties;

public class SeleniumConfig {

  private static int explicitTimeout;
  private static int pageLoadTimeout;
  private static Properties properties;

  public static int getExplicitTimeout() {
    return explicitTimeout;
  }

  public static int getPageLoadTimeout() {
    return pageLoadTimeout;
  }

  public static void loadConfigFile() throws Exception {
    properties = new Properties();
    properties.load(new FileInputStream(MAIN_RESOURCES_DIR + "selenium.properties"));
    setConfigFileValues();
  }

  private static void setConfigFileValues() throws Exception {
    explicitTimeout = Integer.parseInt(getValueIfNotEmptyOrElse("explict_wait_timeout"));
    pageLoadTimeout = Integer.parseInt(getValueIfNotEmptyOrElse("page_load_timeout"));
  }


  private static String getValueIfNotEmptyOrElse(String key) throws Exception {
    return Optional.ofNullable(properties.getProperty(key))
        .filter(s -> !s.isEmpty())
        .orElseThrow(() -> new Exception(
            "Value with key " + key + " not found. Please load properties file first or check the file"));
  }

}
