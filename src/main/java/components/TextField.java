package components;

import data.TextFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TextField extends AbsComponent{
    public TextField(WebDriver driver){
        super (driver);
    }
//private String findTextFieldTemplate = "%s";

    public void fillTextField (TextFieldData textFieldData, String text){
       String selector = textFieldData.getName();
       driver.findElement(By.name(selector)).clear();
       driver.findElement(By.name(selector)).sendKeys(text);

    }
    public void compareValuesTextField(TextFieldData textFieldData, String name){
        String selector = textFieldData.getName();
        Assertions.assertEquals(name, driver.findElement(By.name(selector)).getAttribute("value"));
    }
}
