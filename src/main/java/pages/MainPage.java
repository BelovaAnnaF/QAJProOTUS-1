package pages;

import org.apache.commons.io.function.IOStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import listeners.WebDriverListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class MainPage extends AbsBasePage<MainPage> {

  private String courseNameLocator = "//div[h5[contains(text(), '%s')]]";
  private String inPutButtonLocator = "//button[contains(text(), 'Войти')]";
  private String findNameBlockLocator = "//h2[contains(text(), '%s')]";
  private String coursePageCheck = "//h1[contains(text(), '%s')]";
  @FindBy(xpath = "//a[contains(@href, 'https://otus.ru/lessons/')]//span[contains(., 'С')]")
  List<WebElement> dateItem;
  private WebDriverListener eventListener = new WebDriverListener();
  private Logger log = LogManager.getLogger();


  public MainPage(WebDriver driver) {
    super(driver);
  }

  //ожидаем окончания загрузки главной страницы и проверяем наличие блока Популярные курсы
  public void mainPageWaitDownload(String blockName) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(inPutButtonLocator)));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(findNameBlockLocator, blockName))));
  }

  //ищем на главной странице курс по имени
  public void mainPageCourseFind(String courseName) {
    WebElement courseComponentName = driver.findElement(By.xpath(String.format(courseNameLocator, courseName)));
    eventListener.beforeClickOn(courseComponentName, driver);
  }

  //клик по карточке курса
  public void mainPageCourseClick(String courseName) {
    new Actions(driver).click(driver.findElement(By.xpath(String.format(courseNameLocator, courseName)))).perform();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(coursePageCheck, courseName))));
  }

  //ищем курс и кликаем по нему
  public void mainPageCourseFindAndClicK(String courseName) {
    mainPageCourseFind(courseName);
    mainPageCourseClick(courseName);
  }

  //получаем список курсов на главной странице и собираем даты их начала
  public List<LocalDate> mainPageGetCoursesDateList() {
    List<LocalDate> dateList = new ArrayList<>();
    List<List<String>> datesStringList =
            dateItem.stream().map(WebElement::getText)
                    .map(word -> word.split(" "))
                    .map(array -> Arrays.stream(array).skip(1)
                            .limit(2).collect(Collectors.toList())).collect(Collectors.toList());

    for (List<String> dateStringList : datesStringList) {
      if (dateStringList.size() < 3) {
        if(dateStringList.get(1).equals("декабря")){
          dateStringList.add(2, String.valueOf(LocalDate.now().getYear()-1));
        }else {
          dateStringList.add(2, String.valueOf(LocalDate.now().getYear()));
        }
      }

      String dateInString = dateStringList.get(0) + " " + dateStringList.get(1) + " " + dateStringList.get(2);

      if (Pattern.compile("\\d+ [а-я]+ \\d{4}").matcher(dateInString).find()) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        dateList.add(LocalDate.parse(dateInString, formatter));
      }
    }
    return dateList;
  }
  //ищем min дату начала курса
  public MainPage mainPageMinCoursesDate() {
    String minCourseDate = String.valueOf(mainPageGetCoursesDateList().stream().min(Comparator.naturalOrder()).get());
    log.info(String.format("Самый ранний курс стартует %s", minCourseDate));
    return this;
  }

  //ищем max дату начала курса
  public MainPage mainPageMaxCoursesDate() {
    String maxCourseDate = String.valueOf(mainPageGetCoursesDateList().stream().max(Comparator.naturalOrder()).get());
    log.info(String.format("Самый поздний курс стартует %s", maxCourseDate));
    return this;
  }
  public MainPage mainPageMinCoursesDateReduce(){
    String firstDate = String.valueOf(mainPageGetCoursesDateList().stream().reduce((first, current) -> current.isBefore(first) ? current : first).get());
    log.info(String.format("Самый ранний курс стартует %s", firstDate));
    return this;
  }
  public MainPage mainPageMaxCoursesDateReduce(){
    String lastDate = String.valueOf(mainPageGetCoursesDateList().stream().reduce((last, current) -> current.isAfter(last) ? current : last).get());
    log.info(String.format("Самый поздний курс стартует %s", lastDate));
    return this;
  }
}
