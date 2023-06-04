package myprofile;

import components.DropDownList;
import components.Buttons;
import components.TextField;
import data.DropDownListData;
import data.SelectListData;
import data.TextFieldData;
import exceptions.DriverNotSupported;
import factory.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.LKAboutMePage;
import pageobjects.MainPage;

import static data.DropDownListData.*;

public class OwnDataTests {
    private static DriverFactory driverFactory;

    @BeforeAll
    public static void init() {
        driverFactory = new DriverFactory();
    }

    private WebDriver driver;

    @BeforeEach
    public void initDriver() throws DriverNotSupported {
        driver = new DriverFactory().create();
    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void addOwnData() throws InterruptedException {
        String firstName = "Студент";
        String lastName = "Тестовый";
        String fNameLat = "Student";
        String lNameLat = "Test";
        String blogName = "testik";
        String birthDate = "27.07.1993";
        String contactFirstText = "mejonof1";
        String contactSecondText = "mejonof1";
        String companyName = "OTUS";
        String work = "Autotester";

        new MainPage(driver).open();
        new MainPage(driver).loginLK();
        new LKAboutMePage(driver, "/lk/biography/personal/").open();
        new TextField(driver).fillTextField(TextFieldData.FIRST_NAME, firstName);
        new TextField(driver).fillTextField(TextFieldData.LAST_NAME, lastName);
        new TextField(driver).fillTextField(TextFieldData.FNAME_LAT, fNameLat);
        new TextField(driver).fillTextField(TextFieldData.LNAME_LAT, lNameLat);
        new TextField(driver).fillTextField(TextFieldData.BLOG_NAME, blogName);
        new TextField(driver).fillTextField(TextFieldData.BIRTH_DATE, birthDate);
        String country = new DropDownList(driver).clickAndChooseDropDownList(COUNTRY, 6);
        String city = new DropDownList(driver).clickAndChooseDropDownList(CITY, 3);
        String english_level = new DropDownList(driver).clickAndChooseDropDownList(ENGLISH_LEVEL, 3);
        new Buttons(driver).clickButton(By.xpath("//button[contains(text(), 'Добавить')]"));
        String contactFirst = new DropDownList(driver).clickAndChooseDropDownList(CONTACT_FIRST, 3);
        new TextField(driver).fillTextField(TextFieldData.CONTACT_FIRST, contactFirstText);
        String contactSecond = new DropDownList(driver).clickAndChooseDropDownList(CONTACT_SECOND, 6);
        new TextField(driver).fillTextField(TextFieldData.CONTACT_SECOND, contactSecondText);
        new DropDownList(driver).chooseFromSelectList(SelectListData.FEMALE);
        new TextField(driver).fillTextField(TextFieldData.COMPANY, companyName);
        new TextField(driver).fillTextField(TextFieldData.WORK, work);
        new Buttons(driver).clickButton(By.xpath("//button[contains(text(), 'Сохранить и продолжить')]"));
        driver.manage().deleteAllCookies();
        new MainPage(driver).open();
        new MainPage(driver).loginLK();
        new LKAboutMePage(driver, "/lk/biography/personal/").open();

        new TextField(driver).compareValuesTextField(TextFieldData.FIRST_NAME, firstName);
        new TextField(driver).compareValuesTextField(TextFieldData.LAST_NAME, lastName);
        new TextField(driver).compareValuesTextField(TextFieldData.FNAME_LAT, fNameLat);
        new TextField(driver).compareValuesTextField(TextFieldData.LNAME_LAT, lNameLat);
        new TextField(driver).compareValuesTextField(TextFieldData.BLOG_NAME, blogName);
        new TextField(driver).compareValuesTextField(TextFieldData.BIRTH_DATE, birthDate);
        new DropDownList(driver).compareValuesDropDownList(DropDownListData.COUNTRY, country);
        new DropDownList(driver).compareValuesDropDownList(DropDownListData.CITY, city);
        new DropDownList(driver).compareValuesDropDownList(DropDownListData.ENGLISH_LEVEL, english_level);
        //new DropDownList(driver).compareValuesDropDownList(DropDownListData.CONTACT_FIRST, contactFirst);
        new TextField(driver).compareValuesTextField(TextFieldData.CONTACT_FIRST, contactFirstText);
        new TextField(driver).compareValuesTextField(TextFieldData.CONTACT_SECOND, contactSecondText);
        //new DropDownList(driver).compareValuesDropDownList(DropDownListData.CONTACT_SECOND, contactSecond);
        Assertions.assertEquals("f", driver.findElement(By.id("id_gender")).getAttribute("value"));
        new TextField(driver).compareValuesTextField(TextFieldData.COMPANY, companyName);
        new TextField(driver).compareValuesTextField(TextFieldData.WORK, work);
    }
}