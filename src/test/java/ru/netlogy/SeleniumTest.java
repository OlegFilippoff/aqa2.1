package ru.netlogy;

import com.sun.jna.Structure;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
//        System.setProperty("WebDriver.chrome.driver", "C://Gradle//chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void seUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void shouldTestHappyPath() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Борис-Морис");
        elements.get(1).sendKeys("+71234567899");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("checkbox")).isSelected();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.className("paragraph")).getText().trim();
        String expected = ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestHappyPathWhiteSpaceName() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Лил Джон");
        elements.get(1).sendKeys("+71234567899");
        driver.findElement(By.cssSelector("span.checkbox__box")).click();
        driver.findElement(By.className("checkbox")).isSelected();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.className("paragraph")).getText().trim();
        String expected = ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestBlankName() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("");
        {
            if (elements.get(0) == null) {
                driver.findElement(By.className("input__sub")).getText();
            }
        }
        elements.get(1).sendKeys("+71234567899");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.className("input__sub")).getText();
        String expected = ("Поле обязательно для заполнения");

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestBlankPhone() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Борис");
        elements.get(1).sendKeys("");
        {
            if (elements.get(1) == null) {
                driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).getText();
            }
        }
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).getText();
        String expected = ("Поле обязательно для заполнения");

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestEnglishName() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Boris");
        elements.get(1).sendKeys("+71234567899");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.cssSelector("div span.input__sub")).getText();
        String expected = ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");

        assertEquals(expected, actual);

    }

    @Test
    void shouldTestPhoneWithoutPlus() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Борис");
        elements.get(1).sendKeys("79155175236");
        {
            if (elements.get(1) == null) {
                driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).getText();
            }
        }
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).getText();
        String expected = ("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.");

        assertEquals(expected, actual);

    }


}
