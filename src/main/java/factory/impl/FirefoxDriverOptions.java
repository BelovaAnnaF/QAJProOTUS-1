package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverOptions implements IBrouserOptions{
    @Override
    public MutableCapabilities getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-private-window");

        return firefoxOptions;
        }
}
