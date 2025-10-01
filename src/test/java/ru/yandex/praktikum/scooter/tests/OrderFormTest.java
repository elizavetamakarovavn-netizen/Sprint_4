package ru.yandex.praktikum.scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.scooter.pageobject.OrderPage;
import ru.yandex.praktikum.scooter.pageobject.MainPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderFormTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int dayOfMonth;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderFormTest(String name,
                         String surname,
                         String address,
                         String metroStation,
                         String phone,
                         int dayOfMonth,
                         String rentalPeriod,
                         String color,
                         String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.dayOfMonth = dayOfMonth;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Алла", "Пугачёва", "Москва", "Перово", "+79998453001", 23, "сутки", "black", "И 1 000 000 алых роз, пожалуйста"},
                {"Молния", "МакКуин", "Радиатор-Спрингс, Шоссе 6-6", "Авиамоторная", "89111990622", 30, "двое суток", "grey", "Кчау!"},
                {"Борат", "Сагдиев", "Кушкек, пер. Принглс, 45", "Китай-город", "+79214550000", 1, "шестеро суток", null, "Tsar' vo dvortsa"},
                {"Шерлок", "Холмс", "Бейкер-стрит, 221Б", "Улица 1905 года", "80800800088", 15, "семеро суток", "black", null},
        };
    }

    @Test
    public void testOrderFlow() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = factory.getMainPage();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.acceptCookies();
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.setPhone(phone);
        orderPage.clickNext();

        orderPage.setDate(dayOfMonth);
        orderPage.selectRentalPeriod(rentalPeriod);
        orderPage.selectColor(color);
        orderPage.setComment(comment);
        orderPage.clickOrder();

        boolean isModalDisplayed = orderPage.isModalHeaderDisplayed();
        assertTrue("Ожидалось, что появится попап 'Хотите оформить заказ', но он не появился", isModalDisplayed);

        orderPage.confirmOrder();

        boolean isConfirmedDisplayed = orderPage.isOrderConfirmedHeaderDisplayed();
        assertTrue("Ожидалось, что появится попап 'Заказ оформлен', но он не появился", isConfirmedDisplayed);

    }
}



