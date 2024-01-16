package factory.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverOptions implements IBrouserOptions {
  private String brouserVersion = System.getProperty("brouser.version");

  @Override
  public WebDriver getOptions() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("-kiosk");
    firefoxOptions.addArguments("-private-window");
    firefoxOptions.addArguments("--homepage=about:blank");

    WebDriverManager.firefoxdriver().browserVersion(brouserVersion).setup();
    return new FirefoxDriver(firefoxOptions);
  }
}
