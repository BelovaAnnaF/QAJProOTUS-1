package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaOptions;

public class OperaDriverOptions implements IBrouserOptions{
    @Override
    public MutableCapabilities getOptions() {
        OperaOptions operaOptions = new OperaOptions();

        operaOptions.addArguments("--homepage=about:blank");
        operaOptions.addArguments("--start-maximized");

        return operaOptions;
    }
}
