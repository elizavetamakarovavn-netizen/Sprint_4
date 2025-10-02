package ru.yandex.praktikum.scooter.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static ru.yandex.praktikum.scooter.pageobject.util.EnvConfig.EXPLICITY_TIMEOUT;


public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    // Заголовок формы
    private final By orderFormHeader = By.className("Order_Header__BZXOb");
    //Кнопка закрытия баннера куки
    private final By cookieButton = By.id("rcc-confirm-button");

    // Первая часть формы
    // Поле ввода имени
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Выбор станции метро
    private final By metroDropdown = By.className("select-search__input");
    // Поле ввода номера телефона
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Вторая часть формы
    // Раскрытие календаря "Когда привезти самокат"
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Выбор даты
    private final String datePickerDay = "//div[contains(@class,'react-datepicker__day') and text()='%d']";
    // Раскрытие выпадающего списка "Срок аренды"
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    // Выбор срока аренды
    private final String rentalPeriodOption = "//div[contains(@class,'Dropdown-option') and text()='%s']";
    // Чекбокс "Цвет самоката"
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    // Поле ввода комментария
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By orderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and contains(text(),'Заказать')]");
    // Заголовок "Хотите оформить заказ?" в попапе
    private final By modalHeader = By.xpath("//div[contains(@class,'Order_ModalHeader__3FDaJ') and contains(text(),'Хотите оформить заказ')]");
    // Кнопка "Да" в попапе "Хотите оформить заказ?"
    private final By confirmButton = By.xpath("//button[text()='Да']");
    //Заголовок "Заказ оформлен" в попапе успешного создания заказа
    private final By orderConfirmedHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(),'Заказ оформлен')]");


    // Методы
    // Ввод имени
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    // Ввод фамилии
    public void setSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    // Ввод адреса
    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    // Выбор станции метро
    public void selectMetroStation(String stationName) {
        driver.findElement(metroDropdown).click();
        By option = By.xpath(String.format("//div[contains(@class,'select-search__select')]//div[text()='%s']", stationName));
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(option));
        driver.findElement(option).click();
    }

    // Ввод номера телефона
    public void setPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    // Клик по кнопке "Далее"
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    // Выбор даты
    public void setDate(int dayOfMonth) {
        driver.findElement(dateInput).click();
        By day = By.xpath(String.format(datePickerDay, dayOfMonth));
        driver.findElement(day).click();
    }

    // Выбор срока аренды
    public void selectRentalPeriod(String periodText) {
        driver.findElement(rentalPeriodDropdown).click();
        By option = By.xpath(String.format(rentalPeriodOption, periodText));
        driver.findElement(option).click();
    }

    // Выбор цвета самоката
    public void selectColor(String color) {
        if (color == null) {
            return;
        }
        if ("black".equalsIgnoreCase(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equalsIgnoreCase(color)) {
            driver.findElement(greyColorCheckbox).click();
        }
    }

    // Ввод комментария
    public void setComment(String comment) {
        if (comment == null) {
            return;
        }
        driver.findElement(commentInput).sendKeys(comment);
    }

    // Клик по кнопке "Заказать"
    public void clickOrder() {driver.findElement(orderButton).click();}

    // Ожидание появления заголовка попапа "Хотите оформить заказ?"
    public void waitForModalHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
    }

    // Клик по кнопке "Да" в попапе
    public void confirmOrder() {
        driver.findElement(confirmButton).click();
    }

    // Проверка появления заголовка попапа подтверждения заказа
    public boolean isOrderConfirmedHeaderDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_TIMEOUT));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmedHeader));
        return header.isDisplayed();
    }

    //Закрытие куки
    public void acceptCookies() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }
    // Заголовок формы
    public By getOrderFormHeader() {
        return orderFormHeader;
    }
}


