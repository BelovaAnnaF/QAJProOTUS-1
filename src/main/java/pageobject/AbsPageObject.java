package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsPageObject {
  protected WebDriver driver;
  protected WebDriverWait webDriverWait;
  protected Actions actions;

  public AbsPageObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.actions = new Actions(driver);
    this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }
}
