package ru.yandex.praktikum.scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.scooter.pageobject.MainPage;
import ru.yandex.praktikum.scooter.pageobject.OrderPage;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.scooter.pageobject.util.EnvConfig.EXPLICITY_TIMEOUT;

public class OrderButtonTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testTopOrderButtonOpensOrderForm() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = factory.getMainPage();
        OrderPage orderPage = factory.getOrderPage();

        mainPage.clickTopOrderButton();

        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPage.getOrderFormHeader()));


        assertTrue("Ожидалась форма заказа после клика по верхней кнопке", header.isDisplayed());
    }

    @Test
    public void testBottomOrderButtonOpensOrderForm() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = factory.getMainPage();
        mainPage.clickBottomOrderButton();
        OrderPage orderPage = factory.getOrderPage();

        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPage.getOrderFormHeader()));


        assertTrue("Ожидалась форма заказа после клика по нижней кнопке", header.isDisplayed());
    }
}




