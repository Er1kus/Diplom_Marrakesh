package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DBHelper;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class CardNumberTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }
    @AfterEach
    void clearData() {
        DBHelper.DropData();
    }

    @Nested
    @DisplayName("Проверка поля 'Номер карты' при оплате картой")
    class CardNumberValidation {
        @Test
        @Name("15ти значный номер карты")
        void shouldInputShortCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getShortCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.numberFieldError();
        }

        @Test
        @Name("Случайный номер карты")
        void shouldInputRandomData() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.unSuccessCardPayment();
        }

        @Test
        @Name("Номер карты из 16-ти нулей")
        void shouldInputNullsInCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardNumberOfNulls();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.unSuccessCardPayment();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'Номер карты' при оплате в кредит")
    class CreditCardNumberValidation {
        @Test
        @Name("15ти значный номер карты")
        void shouldInputShortCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getShortCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.numberFieldError();
        }

        @Test
//        @RepeatedTest(3)
        @Name("Случайный номер карты")
        void shouldInputRandomData() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.unSuccessCardPayment();
        }

        @Test
        @Name("Номер карты из 16-ти нулей")
        void shouldInputNullsInCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardNumberOfNulls();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.unSuccessCardPayment();
        }
    }
}

