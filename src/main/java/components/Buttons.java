package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Buttons extends AbsComponent{
    public Buttons(WebDriver driver){
        super(driver);
    }
    public void clickButton (By by){

        driver.findElement(by).click();
    }
}
