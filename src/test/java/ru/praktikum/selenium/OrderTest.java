package ru.praktikum.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikum.selenium.pageobject.MainPage;
import ru.praktikum.selenium.pageobject.OrderPage;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private final String family;
    private final String name;
    private final String address;
    private final String phone;
    private final String rentDate;
    private final String buttonPosition;

    public OrderTest(String family, String name, String address, String phone, String rentDate, String buttonPosition){
        this.family = family;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rentDate = rentDate;
        this.buttonPosition = buttonPosition;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                { "Иванов","Иван","Москва","89506057577","23.12.2023","bottomOrderButton"},
                { "Петров","Петр","Москва","89506057588","12.12.2023","topOrderButton"},
        };
    }
    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        new MainPage(webDriver).clickOnOrderButton(buttonPosition);
    }

    @Test
    public void checkSuccessfulOrder() {
        new OrderPage(webDriver)
                .setInputOrderValue(family,name,address,phone)
                .clickOrderNextButton()
                .setInputRentalValue(rentDate)
                .clickRentOrderButton()
                .clickYesOrderButton();
        var isDisplayed  = webDriver.findElement(By.xpath(".//div[@class='Order_ModalHeader__3FDaJ'][text()='Заказ оформлен']")).isDisplayed();
        assertTrue("Заказ оформлен отображается", isDisplayed);
    }

    @After
    public void clean(){
        webDriver.quit();
    }
}