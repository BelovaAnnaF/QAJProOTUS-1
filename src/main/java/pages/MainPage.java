package pages;

import listeners.WebDriverListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainPage extends AbsBasePage<MainPage> {

  private WebDriverListener dispatcher;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  //ожидаем окончания загрузки главной страницы
  public void mainPageWaitDownload() {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Войти')]")));
  }
  public void buttonClick() {
    WebElement buttonMoreCourses = driver.findElement(By.xpath("//button[contains(text(),'Больше курсов')]"));
    //dispatcher.beforeClickOn(buttonMoreCourses, driver);
    buttonMoreCourses.click();
  }
  public LocalDate getListCoursesDate() {
    List<WebElement> datesCourses = driver
            .findElements(By.xpath("//a[contains(@href, 'https://otus.ru/lessons/')]//span[contains(., 'С')]"));
    LocalDate currentDate = java.time.LocalDate.now();
    LocalDate coursDate = null;
    for (WebElement element : datesCourses) {
      coursDate = LocalDate.parse(element.getText() + " "
              + currentDate.getYear(), DateTimeFormatter.ofPattern("yyyy MM d"));
    }
    return coursDate;
  }
}
