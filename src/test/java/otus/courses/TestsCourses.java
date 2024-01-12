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
//    Необходимо создать проект в Maven'e и реализовать:
    public void openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open("/");
        mainPage.mainPageWaitDownload();
    }
//    1. Фабрику (WebDriverFactory), которая будет получать значение из окружения и запускать соответствующий браузер
//    Браузеры: Chrome, Firefox, Opera
//    2. Реализовать подсветку элементов перед нажатием, после нажатия вернуть данные в исходное состояние
//    3. На главно странице Otus'a снизу найти список курсов(популярные курсы, специализации, рекомендации) и реализовать:
//    -Метод фильтр по названию курса
//    -Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
//    4. Реализовать движение мыши при помощи и выбор курса при помощи библиотеки Actions
}
