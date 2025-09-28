package ru.yandex.praktikum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static ru.yandex.praktikum.scooter.pageobject.util.EnvConfig.BASE_URL;
import static ru.yandex.praktikum.scooter.pageobject.util.EnvConfig.EXPLICITY_TIMEOUT;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
    }

    // Верхняя кнопка "Заказать"
    private final By topOrderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath("//div[contains(@class,'Home_FinishButton')]/button");
    // Список вопросов
    private final By faqQuestions = By.cssSelector(".accordion__heading");
    // Список ответов
    private final By faqAnswers = By.cssSelector(".accordion__panel");
    //Кнопка закрытия баннера куки
    private final By cookieButton = By.id("rcc-confirm-button");

    //Методы
    // Открытие страницы
    public void open() {
        driver.get(BASE_URL);
    }

    // Клик по кнопке "Заказать" сверху
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Заказать']"));
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }

    // Клик по кнопке "Заказать" снизу
    public void clickBottomOrderButton() {
        WebElement button = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();

    }

    // Клик по вопросу и возврат текста ответа
    public String getFaqAnswerByIndex(int index) {
        List<WebElement> questions = driver.findElements(faqQuestions);
        WebElement question = questions.get(index);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();

        List<WebElement> answers = driver.findElements(faqAnswers);
        WebElement answer = answers.get(index);
        wait.until(d -> answer.getAttribute("hidden") == null);
        return answer.getText();
    }

    //Закрытие куки
    public void acceptCookies() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }
}


