package exeptions;

public class DriverNotSupportedException extends RuntimeException {
  public DriverNotSupportedException(String browser) {
    super(String.format("Browser %s not supported", browser));
  }
}
