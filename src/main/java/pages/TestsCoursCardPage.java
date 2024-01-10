package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestsCoursCardPage extends AbsBasePage {

    private final String courseComponentLocator = "(//section[@class='sc-o4bnil-0 riKpM']/div/div/a)[%s]";

    public TestsCoursCardPage(WebDriver driver) {
        super(driver);
    }
    public void coursComponentclick(String coursesNumber){
//открыли страницу курсов
        TestsCoursesPages testCoursesPages = new TestsCoursesPages(driver);
        testCoursesPages.openTestPajes();
        testCoursesPages.chekingPajeContains();
        String componentLocator = String.format(courseComponentLocator,coursesNumber);
        driver.findElement(By.xpath(componentLocator)).click();
    }

    public void returnCoursInfo() {
        String name = "";
        String specification = "";
        String length = "";
        String format = "";
        name = driver.findElement(By.xpath("//h1")).getText();
        specification = driver.findElement(
                By.xpath("//h1/../div[@class='sc-1og4wiw-0 sc-s2pydo-3 gaEufI dZDxRw']")).getText();
        length = driver
                .findElement(By.xpath("(//div[@class='sc-3cb1l3-4 kGoYMV']/p[@class='sc-1og4wiw-0 sc-3cb1l3-0 jQNgtj dgWykw'])[3]"))
                .getText();
        format = driver
                .findElement(By.xpath("(//div[@class='sc-3cb1l3-4 kGoYMV']/p[@class='sc-1og4wiw-0 sc-3cb1l3-0 jQNgtj dgWykw'])[4]"))
                .getText();
    }
}
