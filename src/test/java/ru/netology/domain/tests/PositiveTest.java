package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DBHelper;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.MainPage;


import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveTest {
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
    @DisplayName("Позитивные проверки при оплате картой")
    class SuccessWayCardPayment {
        @Test
        @Name("Оплата с валидного номера карты")
        void shouldGoWithValid() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
        }

        @Test
        @Name("Оплата с невалидного номера карты")
        void shouldGoWithInvalid() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.unSuccessCardPayment();
        }

        @Test
        @Name("Все поля пустые")
        void shouldInputEmptyFields() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getEmptyCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.emptyCardData();
        }
    }

    @Nested
    @DisplayName("Позитивные проверки при оплате в кредит")
    class SuccessWayCreditCardPayment {
        @Test
        @Name("Покупка в кредит с валидного номера карты")
        void shouldGoWithValidCredit() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
        }

        @Test
        @Name("Покупка в кредит с невалидного номера карты")
        void shouldGoWithInvalidCredit() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.unSuccessCardPayment();
        }

        @Test
        @Name("Все поля пустые")
        void shouldInputEmptyFieldsCredit() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getEmptyCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.emptyCardData();
        }
    }
}
