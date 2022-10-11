package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DBHelper;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class CardholderTest {
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
    @DisplayName("Проверка поля 'Владелец' при оплате картой")
    class CardholderValidation {
        @Test
        @Name("Ввод данных на кириллице в поле 'Владелец'")
        void shouldSetNameInCyrillic() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameInCyrillic();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод данных на панджаби в поле 'Владелец'")
        void shouldSetNameInPanjabi() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameInPanjabi();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод числовых значений в поле 'Владелец'")
        void shouldSetNumbersInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNumbersInName();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод одного буквенного символа в поле 'Владелец'")
        void shouldSetOneLetterInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithOneLetter();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод текста из более 256 буквенных символов в поле 'Владелец'")
        void shouldSetLongName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getLongName();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод спецсимволов в поле 'Владелец'")
        void shouldSetPunctuationAndOtherMarksInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithPunctuationMarks();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод данных в поле 'Владелец' в верхнем регистре")
        void shouldSetInUpperCase() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithUpperCase();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
        }

        @Test
        @Name("Ввод данных в поле 'Владелец' в нижнем регистре")
        void shouldSetInLowerCase() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithLowerCase();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
        }

        @Test
        @Name("Ввод пробелов в поле 'Владелец'")
        void shouldSetNameWithWhitespaces() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithLWhitespaces();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.holderFieldError();
        }
    }

    @Nested
    @DisplayName("Проверка поля 'Владелец' при оплате с помощью кредита")
    class CreditCardholderValidation {
        @Test
        @Name("Ввод данных на кириллице в поле 'Владелец'")
        void shouldSetNameInCyrillic() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameInCyrillic();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод данных на панджаби в поле 'Владелец'")
        void shouldSetNameInPanjabi() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameInPanjabi();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод числовых значений в поле 'Владелец'")
        void shouldSetNumbersInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNumbersInName();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод одного буквенного символа в поле 'Владелец'")
        void shouldSetOneLetterInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithOneLetter();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод текста из более 256 буквенных символов в поле 'Владелец'")
        void shouldSetLongName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getLongName();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод спецсимволов в поле 'Владелец'")
        void shouldSetPunctuationAndOtherMarksInName() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithPunctuationMarks();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }

        @Test
        @Name("Ввод данных в поле 'Владелец' в верхнем регистре")
        void shouldSetInUpperCase() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithUpperCase();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
        }

        @Test
        @Name("Ввод данных в поле 'Владелец' в нижнем регистре")
        void shouldSetInLowerCase() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithLowerCase();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
        }

        @Test
        @Name("Ввод пробелов в поле 'Владелец'")
        void shouldSetNameWithWhitespaces() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getNameWithLWhitespaces();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.holderFieldError();
        }
    }
}


