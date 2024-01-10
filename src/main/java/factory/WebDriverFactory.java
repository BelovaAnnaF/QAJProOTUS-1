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


public class WebDriverFactory {

    private final String BROUSER_NAME = System.getProperty("browser", "chrome");

    public WebDriver newDriver(){

        if(BROUSER_NAME == null){
            throw new DriverNotSupportedException(BROUSER_NAME);
        }

        switch (BROUSER_NAME) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                IBrouserOptions options = new ChromeDriverOptions();
                return new ChromeDriver((ChromeOptions) options.getOptions());
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                IBrouserOptions options = new FirefoxDriverOptions();
                return new FirefoxDriver((FirefoxOptions) options.getOptions());
            }
            case "opera": {
                WebDriverManager.operadriver().setup();
                IBrouserOptions options = new OperaDriverOptions();
                return new OperaDriver((OperaOptions) options.getOptions());

        }
    }
        return null;
}
