package enums;

public enum DriverType {
  CHROME("chrome"),
  FIREFOX("firefox");

  private final String text;

  DriverType(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
