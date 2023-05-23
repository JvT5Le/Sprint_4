package ru.praktikum.selenium.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ru.praktikum.selenium.config.AppConfig.APP_URL;

public class MainPage {
    WebDriver webDriver;
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(APP_URL);
    }

    private final By orderStateButton = By.className("Header_Link__1TAG7");
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    private final By orderNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    private final By accordion = By.className("accordion");

    public MainPage clickOnOrderButton(String buttonPosition) {
        if (buttonPosition != null && buttonPosition.equals("topOrderButton"))  webDriver.findElement(topOrderButton).click();
        else if (buttonPosition !=null && buttonPosition.equals("bottomOrderButton")) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webDriver.findElement(bottomOrderButton));
            webDriver.findElement(bottomOrderButton).click();
        }
         return this;
    }
    public MainPage clickOnDropDownElement(String element){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", webDriver.findElement(accordion));
        webDriver.findElement(By.xpath(".//*[text()='"+element+"']")).click();
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='"+element+"']/..//following-sibling::div")));
        return this;
    }
    public MainPage clickOrderStateButton(){
        webDriver.findElement(orderStateButton).click();
        return this;
    }
    public MainPage writeOrderNumber(String order){
        webDriver.findElement(orderNumberInput).sendKeys(order);
        webDriver.findElement(orderNumberInput).sendKeys(order);
        return this;
    }
    public OrderPage clickGoButton(){
        webDriver.findElement(goButton).click();
        return new OrderPage(webDriver);
    }
}
