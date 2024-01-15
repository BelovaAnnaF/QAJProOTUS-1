package factory;

import exeptions.DriverNotSupportedException;
import factory.impl.ChromeDriverOptions;
import factory.impl.FirefoxDriverOptions;
import factory.impl.OperaDriverOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class WebDriverFactory implements IFactory<EventFiringWebDriver> {

  private String brouserName = System.getProperty("browser", "chrome");

  @Override
  public EventFiringWebDriver newDriver() {
    if (brouserName == null) {
      throw new DriverNotSupportedException(brouserName);
    }

    switch (brouserName) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeDriverOptions().getOptions());
      }
      case "firefox": {
        return new EventFiringWebDriver(new FirefoxDriverOptions().getOptions());
      }
      case "opera": {
        return new EventFiringWebDriver(new OperaDriverOptions().getOptions());
      }
    }
    return null;
  }
}
