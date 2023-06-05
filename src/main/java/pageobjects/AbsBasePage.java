package pageobjects;

import commons.AbsPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbsBasePage extends AbsPageObject {

    private static final String BASE_URL = System.getProperty("base.url", "https://otus.ru");//, "https://otus.ru");
    private static final String LOGIN = System.getProperty("login", "mejonof918@pgobo.com");//"wirato4727@ippals.com";
    private static final String PASSWORD = System.getProperty("password", "Qwerty123!");
    protected WebDriver driver;

    private String path;

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.driver = driver;
    if (!path.startsWith("/")){
        path = "/" + path;
    }
        this.path = path;

        PageFactory.initElements(driver, this);
    }

    public void open() {

        driver.get(BASE_URL + path);
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiting.until(driver -> true);
    }

    public void loginLK() throws InterruptedException{
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiting.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@placeholder='Электронная почта']")))
                .sendKeys(LOGIN);
        driver.findElement(By.xpath("//input[@type='password'][@placeholder='Введите пароль']"))
                .sendKeys(PASSWORD);
        driver.findElement(By.xpath("//div[@class='new-log-reg__body']/descendant::button[contains(text(), 'Войти')]")).click();
//        waiting.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[data-name='user-info'].header3__nav-item-user-info")))).click();
        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("[data-name='user-info'].header3__nav-item-user-info"))).build().perform();
        //driver.findElement(By.cssSelector("[data-name='user-info'].header3__nav-item-user-info")).click();
        waiting.until(ExpectedConditions.attributeToBeNotEmpty((driver.findElement(By.cssSelector(".header3__nav-item-user-info"))), "style"));
        driver.findElement(By.xpath("//a[contains(text(), 'Личный кабинет')]")).click();


    }
    public void clickButton (By by){

        driver.findElement(by).click();
    }

}
