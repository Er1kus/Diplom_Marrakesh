package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.CardPaymentPage;
import ru.netology.domain.pages.MainPage;


import static com.codeborne.selenide.Selenide.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HappyPathTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    @DisplayName("Positive tests")
    class SuccessWayPayment {
        @BeforeEach
        public void setUp() {
            Configuration.holdBrowserOpen = true;
            open("http://localhost:8080");
        }

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
        @Name("Покупка в кредит с валидного номера карты")
        void shouldGoWithValidCredit() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
        }
    }

//
//    @Nested
//    class CardholderNameValidation {
//        @BeforeEach
//        public void setUp() {
//            Configuration.holdBrowserOpen = true;
//            open("http://localhost:8080");
//        }
//
//        @Test
//        @Name("Ввод данных на кириллице в поле 'Владелец'")
//        void shouldSetNameInCyrillic() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameInCyrillic();
//            requestPage.nameInCyrillic(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод данных на панджаби в поле 'Владелец'")
//        void shouldSetNameInPanjabi() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameInPanjabi();
//            requestPage.nameInPanjabi(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод числовых значений в поле 'Владелец'")
//        void shouldSetNumbersInName() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNumbersInName();
//            requestPage.numbersInName(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод одного буквенного символа в поле 'Владелец'")
//        void shouldSetOneLetterInName() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameWithOneLetter();
//            requestPage.nameWithOneLetter(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод текста из более 256 буквенных символов в поле 'Владелец'")
//        void shouldSetLongName() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getLongName();
//            requestPage.nameLong(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод спецсимволов в поле 'Владелец'")
//        void shouldSetPunctuationAndOtherMarksInName() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameWithPunctuationMarks();
//            requestPage.nameWithPunctuationAndOtherMarks(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод данных в поле 'Владелец' в верхнем регистре")
//        void shouldSetInUpperCase() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameWithUpperCase();
//            requestPage.nameWithUpperCase(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод данных в поле 'Владелец' в нижнем регистре")
//        void shouldSetInLowerCase() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameWithLowerCase();
//            requestPage.nameWithLowerCase(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод пробелов в поле 'Владелец'")
//        void shouldSetNameWithWhitespaces() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getNameWithLWhitespaces();
//            requestPage.nameWithWhitespaces(cardInfo);
//        }
//    }
//
//    @Nested
//    class MonthValidation {
//        @BeforeEach
//        public void setUp() {
//            Configuration.holdBrowserOpen = true;
//            open("http://localhost:8080");
//        }
//
//        @Test
//        @Name("Ввод в поле 'Месяц' одной цыфры")
//        void shouldSetMonthWithOneNumber() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthWithOneNumber();
//            requestPage.monthWithOneNumber(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод 00 в поле 'Месяц'")
//        void shouldSetMonthWithNulls() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthWithNulls();
//            requestPage.monthWithNulls(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод в поле 'Месяц' отрицательного значения")
//        void shouldSetMonthWithMinus() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthWithMinus();
//            requestPage.monthWithMinus(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод в поле 'Месяц' значения больше 13")
//        void shouldSetMonthLargerThan12() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthLargerThan14();
//            requestPage.monthLargerThan12(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод в поле 'Месяц' буквенных значений")
//        void shouldSetMonthWithLetters() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthWithLetters();
//            requestPage.monthWithLetters(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод пробелов в поле 'Месяц'")
//        void shouldSetMonthWithWhitespaces() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthWithWhitespaces();
//            requestPage.monthWithWhitespaces(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод 13 в поле 'Месяц'")
//        void shouldSetNew13Month() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.get13Month();
//            requestPage.monthNew13Month(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод прошедшего месяца в поле 'Месяц' ")
//        void shouldSetMonthInPast() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthInPast();
//            requestPage.monthInPast(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод спецсимволов в поле 'Месяц'")
//        void shouldSetPunctuationInMonth() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getMonthPunctuationMarksInMonth();
//            requestPage.punctuationInMonth(cardInfo);
//        }
//    }
//
//    @Nested
//    class YearValidation {
//        @BeforeEach
//        public void setUp() {
//            Configuration.holdBrowserOpen = true;
//            open("http://localhost:8080");
//        }
//
//        @Test
//        @Name("Ввод в поле 'Год' одной цыфры")
//        void shouldSetYearWithOneNumber() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithOneNumber();
//            requestPage.yearWithOneNumber(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод прошедшего года в поле 'Год'")
//        void shouldSetYearInPast() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearInPast();
//            requestPage.yearWithInPast(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод 00 в поле 'Год'")
//        void shouldSetNullsInYear() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithNulls();
//            requestPage.yearWithNulls(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод отрицательного значения в поле 'Год'")
//        void shouldSetMinusInYear() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithMinus();
//            requestPage.yearWithMinus(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод пробелов в поле 'Год'")
//        void shouldSetWhitespacesInYear() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithWhitespaces();
//            requestPage.yearWithWhitespaces(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод буквенных символов в поле 'Год'")
//        void shouldSetLettersInYear() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithLetters();
//            requestPage.yearWithLetters(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод спецсимволов в поле 'Год'")
//        void shouldSetPunctuationInYear() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearWithPunctuation();
//            requestPage.yearWithPunctuation(cardInfo);
//        }
//
//        @Test
//        @Name("Плюс 5 лет к текущему году в поле 'Год'")
//        void shouldSetYearInFuture() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearInFuture();
//            requestPage.yearInFuture(cardInfo);
//        }
//
//        @Test
//        @Name("Плюс 6 лет к текущему году в поле 'Год'")
//        void shouldSetYearInFarFuture() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getYearInFarFuture();
//            requestPage.yearInFarFuture(cardInfo);
//        }
//    }
//
//    @Nested
//    class CvcValidation {
//        @BeforeEach
//        public void setUp() {
//            Configuration.holdBrowserOpen = true;
//            open("http://localhost:8080");
//        }
//
//        @Test
//        @Name("Ввод в поле 'CVC/CVV' одной цыфры")
//        void shouldSetCvcWithOneNumber() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithOneNumber();
//            requestPage.cvcWithOneDigit(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод 000 в поле 'CVC/CVV'")
//        void shouldSetCvcWithNulls() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithNulls();
//            requestPage.cvcWithNulls(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод отрицательного числа в поле 'CVC/CVV'")
//        void shouldSetCvcWithMinus() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithMinus();
//            requestPage.cvcWithMinus(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод пробелов в поле 'CVC/CVV'")
//        void shouldSetCvcWithWhitespaces() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithWhitespaces();
//            requestPage.cvcWithWhitespaces(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод буквенных символов в поле 'CVC/CVV'")
//        void shouldSetCvcWithLetters() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithLetters();
//            requestPage.cvcWithLetters(cardInfo);
//        }
//
//        @Test
//        @Name("Ввод спецсимволов в поле 'CVC/CVV'")
//        void shouldSetCvcWithPunctuation() {
//            var requestPage = new RequestPage();
//            var cardInfo = DataHelper.getCvcWithPunctuation();
//            requestPage.cvcWithPunctuation(cardInfo);
//        }
//
//    }
}
