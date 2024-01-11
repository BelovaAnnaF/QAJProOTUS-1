package factory.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverOptions implements IBrouserOptions{

    private String brouserVersion = System.getProperty("brouser.version");
    @Override
    public WebDriver getOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--start-maximized");

        WebDriverManager.chromedriver().browserVersion(brouserVersion).setup();
        return new ChromeDriver(chromeOptions);
    }
}
