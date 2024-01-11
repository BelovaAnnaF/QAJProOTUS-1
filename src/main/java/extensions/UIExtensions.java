package extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtensions implements BeforeEachCallback, AfterEachCallback {

    private WebDriver driver = null;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if(this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }
}
