package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends  AbsBasePage{

    public MainPage(WebDriver driver) {
        super(driver);
    }
    //ожидаем окончания загрузки главной страницы
    public void mainPageWaitDownload(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Войти')]")));
    }
}
