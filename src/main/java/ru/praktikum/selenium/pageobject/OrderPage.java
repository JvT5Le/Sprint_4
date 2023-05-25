package ru.praktikum.selenium.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    WebDriver webDriver;
    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By nextOrderButton = By.xpath (".//button[text()='Далее']");
    private final By firstNameOrderInput = By.xpath (".//input[@type='text'][@placeholder='* Имя']");
    private final By secondNameOrderInput = By.xpath(".//input[@type='text'][@placeholder='* Фамилия']");
    private final By addressOrderInput = By.xpath(".//input[@type='text'][@placeholder='* Адрес: куда привезти заказ']");
    private final By phoneNumberOrderInput = By.xpath(".//input[@type='text'][@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderDate = By.xpath(".//input[@type='text'][@placeholder='* Когда привезти самокат']");
    private final By rentOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By yesOrderButton = By.xpath(".//button[text()='Да']");
    private final By metroSelectSearch = By.className("select-search__input");
    private final By dayDropDownArrow = By.cssSelector(".Dropdown-arrow");
    private final By oneDaySelectDropDown = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");

    public OrderPage clickOrderNextButton(){
        webDriver.findElement(By.xpath(".//div/button[@class='App_CookieButton__3cvqF']")).click();
        webDriver.findElement(nextOrderButton).click();
        return this;
    }
    public  OrderPage setInputOrderValue(String family, String name, String address, String phone){
        webDriver.findElement(firstNameOrderInput).sendKeys(family);
        webDriver.findElement(secondNameOrderInput).sendKeys(name);
        webDriver.findElement(addressOrderInput).sendKeys(address);
        webDriver.findElement(metroSelectSearch).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);;
        webDriver.findElement(phoneNumberOrderInput).sendKeys(phone);
    return this;
    }
    public OrderPage setInputRentalValue(String rentDate){
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(orderDate));
        webDriver.findElement(orderDate).sendKeys(rentDate);
        webDriver.findElement(dayDropDownArrow).click();
        webDriver.findElement(oneDaySelectDropDown).click();
        return this;
    }
    public OrderPage clickRentOrderButton(){
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.elementToBeClickable(rentOrderButton));
        webDriver.findElement(rentOrderButton).click();
        return this;
            }
    public OrderPage clickYesOrderButton(){
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(yesOrderButton));
        webDriver.findElement(yesOrderButton).click();
        return this;
    }
    public boolean isSuccessfulOrderModalHeaderDisplayed() {
        return webDriver.findElement(By.xpath(".//div[@class='Order_ModalHeader__3FDaJ'][text()='Заказ оформлен']")).isDisplayed();
    }
}