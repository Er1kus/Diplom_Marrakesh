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
import static org.junit.jupiter.api.Assertions.*;
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
        @Order(1)
        @Name("Получение статуса при оплате одобренной картой")
        void shouldGetStatusWithValidCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            val expected = "APPROVED";
            val actual = getPaymentInfo().getStatus();
            assertEquals(expected, actual);
        }

        @Test
        @Order(2)
        @Name("Получение статуса при оплате по отклонненой картой")
        void shouldGetStatusWithInvalidCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            val expected = "DECLINED";
            val actual = getPaymentInfo().getStatus();
            assertEquals(expected, actual);
        }

        @Test
        @Order(3)
        @Name("При вводе случайного номера карты, данные не сохраняются в таблицу order_entity ")
        void shouldNotSaveRandomCardNumber() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            assertNull(DBHelper.getOrderInfo());
        }

        @Test
        @Order(4)
        @Name("При вводе валидного номера карты, данные в столбце 'payment_id' из 'order_entity' = 'transaction_id' из 'payment_entity'")
        void shouldSaveSamePaymentInfo() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var cardPaymentPage = mainPage.chooseToPayByCard();
            cardPaymentPage.fillingForm(cardInfo);
            cardPaymentPage.successCardPayment();
            assertEquals(DBHelper.getOrderInfo().getPayment_id(), getPaymentInfo().getTransaction_id());
        }
    }

    @Nested
    @DisplayName("Проверки БД при оплате в кредит")
    class CreditPaymentDBTests {
        @Test
        @Order(5)
        @Name("Получение статуса при отправке заявки на кредит по одобренной карте")
        void shouldGetStatusWithValidCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            val expected = "APPROVED";
            val actual = getCreditInfo().getStatus();
            assertEquals(expected, actual);
        }

        @Test
        @Order(6)
        @Name("Получение статуса при отправке заявки на кредит по отклонненой карте")
        void shouldGetStatusWithInvalidCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getDeclinedCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            val expected = "DECLINED";
            val actual = getCreditInfo().getStatus();
            assertEquals(expected, actual);
        }

        @Test
        @Order(7)
        @Name("При вводе случайного номера карты, данные не сохраняются в таблицу credit_request_entity")
        void shouldNotSaveRandomCardNumberCreditRequest() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getRandomCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            assertNull(DBHelper.getCreditInfo());
        }

        @Test
        @Order(8)
        @Name("При покупке в кредит с валидного номера карты, данные в столбце 'payment_id' из 'order_entity' = 'bank_id' из 'credit_request_entity' ")
        void shouldSaveSameCreditInfo() {
            var mainPage = new MainPage();
            var cardInfo = DataHelper.getCardInfo();
            var creditPaymentPage = mainPage.chooseToPayByCredit();
            creditPaymentPage.fillingForm(cardInfo);
            creditPaymentPage.successCreditPayment();
            assertEquals(DBHelper.getOrderInfo().getCredit_id(), getCreditInfo().getBank_id());

        }
    }
}
