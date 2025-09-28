package ru.yandex.praktikum.scooter.tests;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pageobject.MainPage;
import java.time.Duration;
import static ru.yandex.praktikum.scooter.pageobject.util.EnvConfig.IMPLICITY_TIMEOUT;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;
    private MainPage mainPage;

    public WebDriver getDriver() {
        return driver;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else {
            startChrome();
        }

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    private void startChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }

    private void startFirefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }


    public void before() {
        initDriver();
    }


    public void after() {
        driver.quit();

    }
}

