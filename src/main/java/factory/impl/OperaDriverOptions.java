package factory.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaDriverOptions implements IBrouserOptions{
    private String brouserVersion = System.getProperty("brouser.version");
    @Override
    public WebDriver getOptions() {
        OperaOptions operaOptions = new OperaOptions();

        operaOptions.addArguments("--homepage=about:blank");
        operaOptions.addArguments("--start-maximized");

        WebDriverManager.operadriver().browserVersion(brouserVersion).setup();
        return new OperaDriver(operaOptions);
    }
}
