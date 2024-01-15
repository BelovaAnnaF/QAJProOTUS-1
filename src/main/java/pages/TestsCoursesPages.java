package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestsCoursesPages extends AbsBasePage {
  private final String testButtonLocator = "//section[@class='sc-10izyea-0 kZwiYW']//div[text() = 'Тестирование']";
  private final String namePajeLocator = "//h1/div[contains(text(),'Каталог')]";
  private final String checkNameLocator = "//label[contains(text(),'Тестирование')]/..//input[@checked]";
  private final String coursesComponentLocator = "//section[@class='sc-o4bnil-0 riKpM']/div/div/a";

  public TestsCoursesPages(WebDriver driver) {
    super(driver);
  }

  //перейти на страницу тестирование
  public void openTestPajes() {
    driver.findElement(By.xpath(testButtonLocator)).click();
  }

  //проверяем, что загрузился каталог курсов и выбрано тестирование, есть плашки курсов
  public void chekingPajeContains() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(namePajeLocator)));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkNameLocator)));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(coursesComponentLocator)));
  }

  //помещаем курсы в лист для получения их количества
  protected List<WebElement> coursesList() {
    return driver.findElements(By.xpath(coursesComponentLocator));
  }

  //сравниваем, что количество курсов = 10
  public void checkCoursesListCount() {
    Assertions.assertEquals(10, coursesList().size());
  }
}
