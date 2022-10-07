package ru.netology.domain.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jdk.jfr.Name;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.domain.data.DBHelper;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.domain.data.DBHelper.*;

public class DBTests {
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
    @DisplayName("Проверки БД при оплате картой")
    class CardPaymentDBTests {
        @Test
        @Name("Получение статуса при оплате одобренной картой")
        void shouldGetStatusWithValidCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            val expectedStatus = "APPROVED";
            val actualStatus = getPaymentStatusInfo();
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        @Name("Получение статуса при оплате по отклонненой картой")
        void shouldGetStatusWithInvalidCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            val expectedStatus = "DECLINED";
            val actualStatus = getPaymentStatusInfo();
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        @Name("При вводе случайного номера карты, данные не сохраняются в таблицу order_entity ")
        void shouldNotSaveRandomCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            assertEquals(0, DBHelper.getOrderCountInfo());
        }

        @Test
        @Name("При вводе валидного номера карты, данные в столбце 'payment_id' из 'order_entity' = 'transaction_id' из 'payment_entity'")
        void shouldSaveSamePaymentInfo() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            assertEquals(DBHelper.getPaymentId(), DBHelper.getPaymentTransactionInfo());

        }
    }

    @Nested
    @DisplayName("Проверки БД при оплате в кредит")
    class CreditPaymentDBTests {
        @Test
        @Name("Получение статуса при отправке заявки на кредит по одобренной карте")
        void shouldGetStatusWithValidCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            val expectedStatus = "APPROVED";
            val actualStatus = getCreditStatusInfo();
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        @Name("Получение статуса при отправке заявки на кредит по отклонненой карте")
        void shouldGetStatusWithInvalidCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            val expectedStatus = "DECLINED";
            val actualStatus = getCreditStatusInfo();
            assertEquals(expectedStatus, actualStatus);
        }

        @Test
        @Name("При вводе случайного номера карты, данные не сохраняются в таблицу credit_request_entity")
        void shouldNotSaveRandomCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            assertEquals(0, DBHelper.getCreditCountInfo());
        }

        @Test
        @Name("При покупке в кредит с валидного номера карты, данные в столбце 'payment_id' из 'order_entity' = 'bank_id' из 'credit_request_entity' ")
        void shouldSaveSameCreditInfo() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            assertEquals(DBHelper.getBankId(), DBHelper.getCreditId());

        }
    }
}
