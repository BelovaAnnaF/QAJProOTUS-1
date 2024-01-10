package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventsPage extends AbsBasePage{

    private final String eventsCardElementLocator ="//div[@class='dod_new-event-content']";
    private final Logger log = LogManager.getLogger();

    public EventsPage(WebDriver driver) {
        super(driver);
    }
    public void openEventsPage(){

        Actions actions = new Actions(driver);
//        educationPopup.popupShouldNotBeVisible();
        WebElement educationElement = driver.findElement(By.xpath("//span[@title='Обучение']"));
        actions.moveToElement(educationElement);
        actions.perform();
//        educationPopup.popupShouldBeVisible();
        driver.findElement(By.xpath("//a[contains(text(),'Календарь мероприятий')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='dod_new-header__title']")));
    }
    public void eventsPageContainsCards(){

        if (eventsList().size() > 0){
            log.info("На странице есть мероприятия");
        }else {
            log.info("На странице нет мероприятий");
        }

    }

    public void scrollEventsPage(){
        String loaderLocator = "//div[@class='dod_new-loader']";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        long scrollHeight;
        long scrollHeightNew = (long) js.executeScript("return document.documentElement.scrollHeight");

        do{
            scrollHeight = scrollHeightNew;
                js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," +
                        "document.body.scrollHeight,document.documentElement.clientHeight));");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loaderLocator)));
//                wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loaderLocator))));
                scrollHeightNew = (long) js.executeScript("return document.documentElement.scrollHeight");
            }while (scrollHeight < scrollHeightNew);
    }

    public void validationDatesEvent(){
        List<WebElement> datesEvents = driver
                .findElements(By.xpath("(//span[@class='dod_new-event__date-text'])[position() mod 2 = 1]"));
        LocalDate currentDate = java.time.LocalDate.now();
        for (WebElement element : datesEvents) {
            LocalDate eventDate = LocalDate.parse(element.getText() + " "
                    + currentDate.getYear(), DateTimeFormatter.ofPattern("d MMMM yyyy"));

            Assertions.assertTrue(eventDate.isAfter(currentDate) || eventDate.isEqual(currentDate));
        }
    }


    protected List<WebElement> eventsList(){
        return driver.findElements(By.xpath(eventsCardElementLocator));
    }

    public void eventsFilter(){
        driver.findElement(By.xpath("//div[@class = 'dod_new-events-dropdown js-dod_new_events-dropdown']/div/span")).click();
        driver.findElement(By.xpath("//div[@class='dod_new-events-dropdown js-dod_new_events-dropdown dod_new-events-dropdown_opened']/div[@class='dod_new-events-dropdown__list js-dod_new_events-dropdown']/a[@title ='Открытый вебинар']"))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), ' мероприятия')]/..//div/div/span[contains(text(),'Открытые вебинары')]")));
        }
    public void validationTypeEvents() {
        List<WebElement> typeEvents = driver
                .findElements(By.xpath("(//div[@class='dod_new-type__text'])"));
        String typeEventReference = "Открытый вебинар";
        for(WebElement element : typeEvents){
            Assertions.assertEquals(typeEventReference,element.getText());
        }
    }
}
