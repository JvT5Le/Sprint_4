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

    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
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

    public String getTextElement(String element){
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='"+element+"']/..//following-sibling::div")));
            return webDriver.findElement(By.xpath(".//*[text()='"+element+"']/..//following-sibling::div")).getText();
    }
}
