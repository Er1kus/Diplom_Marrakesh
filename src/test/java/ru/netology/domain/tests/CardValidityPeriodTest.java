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

public class CardValidityPeriodTest {
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
    @DisplayName("Проверка поля 'Месяц' при оплате картой")
    class MonthValidation {
        @Test
        @Name("Ввод в поле 'Месяц' одной цифры")
        void shouldSetMonthWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthWithOneNumber();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.monthFieldError();
        }

        @Test
        @Name("Ввод 00 в поле 'Месяц'")
        void shouldSetMonthWithNulls() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthWithNulls();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.monthFieldError();
        }

        @Test
        @Name("Ввод 13 в поле 'Месяц'")
        void shouldSetNew13Month() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.get13Month();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.nonExistentOrPastMonthError();
        }

        @Test
        @Name("Ввод прошедшего месяца в поле 'Месяц' ")
        void shouldSetMonthInPast() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthInPast();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.nonExistentOrPastMonthError();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'Месяц' при оплате в кредит")
    class CreditMonthValidation {
        @Test
        @Name("Ввод в поле 'Месяц' одной цифры")
        void shouldSetMonthWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthWithOneNumber();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.monthFieldError();
        }

        @Test
        @Name("Ввод 00 в поле 'Месяц'")
        void shouldSetMonthWithNulls() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthWithNulls();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.monthFieldError();
        }

        @Test
        @Name("Ввод 13 в поле 'Месяц'")
        void shouldSetNew13Month() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.get13Month();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.nonExistentOrPastMonthError();
        }

        @Test
        @Name("Ввод прошедшего месяца в поле 'Месяц' ")
        void shouldSetMonthInPast() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getMonthInPast();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.nonExistentOrPastMonthError();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'Год' при оплате картой")
    class YearValidation {
        @Test
        @Name("Ввод в поле 'Год' одной цифры")
        void shouldSetYearWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearWithOneNumber();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.yearError();
        }

        @Test
        @Name("Ввод прошедшего года в поле 'Год'")
        void shouldSetYearInPast() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInPast();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.pastYearError();
        }

        @Test
        @Name("Ввод 00 в поле 'Год'")
        void shouldSetNullsInYear() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearWithNulls();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.pastYearError();
        }

        @Test
        @Name("Плюс 5 лет к текущему году в поле 'Год'")
        void shouldSetYearInFuture() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInFuture();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
        }

        @Test
        @Name("Плюс 6 лет к текущему году в поле 'Год'")
        void shouldSetYearInFarFuture() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInFarFuture();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.farFutureYearError();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'Год' при оплате в кредит")
    class CreditYearValidation {
        @Test
        @Name("Ввод в поле 'Год' одной цифры")
        void shouldSetYearWithOneNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearWithOneNumber();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.yearError();
        }

        @Test
        @Name("Ввод прошедшего года в поле 'Год'")
        void shouldSetYearInPast() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInPast();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.pastYearError();
        }

        @Test
        @Name("Ввод 00 в поле 'Год'")
        void shouldSetNullsInYear() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearWithNulls();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.pastYearError();
        }

        @Test
        @Name("Плюс 5 лет к текущему году в поле 'Год'")
        void shouldSetYearInFuture() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInFuture();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
        }

        @Test
        @Name("Плюс 6 лет к текущему году в поле 'Год'")
        void shouldSetYearInFarFuture() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getYearInFarFuture();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.farFutureYearError();
        }
    }
}


