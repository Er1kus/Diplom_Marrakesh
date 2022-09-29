package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.MainPage;


import static com.codeborne.selenide.Selenide.open;

public class CVCCVVTest {
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

    @Nested
    @DisplayName("Проверка поля 'CVC/CVV' при оплате картой ")
    class CardCVCValidation {
        @Test
        @Name("Ввод в поле 'CVC/CVV' одной цифры")
        void shouldSetCvcWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCvcWithOneNumber();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.cvcError();
        }
        @Test
        @Name("Ввод в поле 'CVC/CVV' двух цифр")
        void shouldSetCvcWithTwoNumbers() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCvcWithTwoNumber();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.cvcError();
        }
        @Test
        @Name("Пустое поле 'CVC/CVV'")
        void shouldSetCvcEmpty() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getEmptyCvc();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.cvcError();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'CVC/CVV' при оплате в кредит ")
    class CreditCVCValidation {
        @Test
        @Name("Ввод в поле 'CVC/CVV' одной цифры")
        void shouldSetCvcWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCvcWithOneNumber();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.cvcError();
        }
        @Test
        @Name("Ввод в поле 'CVC/CVV' одной цифры")
        void shouldSetCvcWithTwoNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCvcWithTwoNumber();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.cvcError();
        }
        @Test
        @Name("Пустое поле 'CVC/CVV'")
        void shouldSetCvcEmpty() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getEmptyCvc();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.cvcError();
        }
    }
}
