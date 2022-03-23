package ru.netlogy;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        System.setProperty("WebDriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void seUp() {
        driver = new ChromeDriver();
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
    void shouldTest() {
        driver.get("http://localhost:9999/");
        List<WebElement> elements = driver.findElements(By.cssSelector("input.input__control"));
        elements.get(0).sendKeys("Борис");
        elements.get(1).sendKeys("+71231425639");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();
        System.out.println();

        String actual = driver.findElement(By.className("paragraph")).getText().trim();
        String expected = ("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");

        assertEquals(expected, actual);

    }

}
