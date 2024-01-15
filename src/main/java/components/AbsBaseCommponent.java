package components;

import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public abstract class AbsBaseCommponent extends AbsPageObject {
  public AbsBaseCommponent(WebDriver driver) {
    super(driver);
  }
}
