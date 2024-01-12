package factory;

import exeptions.DriverNotSupportedException;
import factory.impl.ChromeDriverOptions;
import factory.impl.FirefoxDriverOptions;
import factory.impl.IBrouserOptions;
import factory.impl.OperaDriverOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class WebDriverFactory implements IFactory<EventFiringWebDriver>{

    private final String BROUSER_NAME = System.getProperty("browser", "chrome");

    @Override
    public EventFiringWebDriver newDriver() {
        if (BROUSER_NAME == null) {
            throw new DriverNotSupportedException(BROUSER_NAME);
        }

        switch (BROUSER_NAME) {
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
