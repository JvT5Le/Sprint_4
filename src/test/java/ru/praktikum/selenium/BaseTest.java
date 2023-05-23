package ru.praktikum.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.selenium.pageobject.MainPage;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BaseTest {
    private WebDriver webDriver;
    private final String expectedText;
    private final String locatorText;
    public BaseTest(String locatorText, String expectedText){
        this.expectedText = expectedText;
        this.locatorText = locatorText;
    }

@Parameterized.Parameters
 public static Object[][] testData() {
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void dropDownList (){
        new MainPage(webDriver)
                 .clickOnDropDownElement(locatorText);
        String actualText = webDriver.findElement(By.xpath(".//*[text()='"+locatorText+"']/..//following-sibling::div")).getText();
        assertEquals("текст не совпадает",expectedText,actualText);
    }

    @After
    public void clean() {
        webDriver.quit();
    }
}