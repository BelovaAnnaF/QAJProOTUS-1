package otus.courses;

import annotations.Driver;
import extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtensions.class)
public class TestsCourses {

  @Driver
  private WebDriver driver;

  @Test
  public void openMainPage() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open("/").mainPageWaitDownload("Популярные курсы");
    mainPage.mainPageMinCoursesDate();
    mainPage.mainPageMaxCoursesDate();
    mainPage.mainPageCourseFindAndClicK("Java-разработчик");
  }
}
