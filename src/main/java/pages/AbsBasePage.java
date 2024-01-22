package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AbsPageObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public abstract class AbsBasePage<T> extends AbsPageObject {

  private String baseUrl = System.getProperty("base.url", "https://otus.ru");
  private String path = "";
  protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String normaliseBaseUrl() {
    return baseUrl.endsWith("/")
            ? baseUrl.replaceAll("/$", "")
            : baseUrl;
  }

  public T open(String path) {
    driver.get(normaliseBaseUrl() + path);
    return (T) page(getClass());
  }

  public <T> T page(Class<T> clazz) {
    try {
      Constructor constructor = clazz.getConstructor(WebDriver.class);

      return convertInstanceOfObject(constructor.newInstance(driver), clazz);

    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }

  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }
}
