package ru.netology.domain.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.domain.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPaymentPage {
    SelenideElement buyByCreditButton = $x(".//span[text()='Купить в кредит']");
    SelenideElement buyByCreditHeading = $(byText("Кредит по данным карты"));
    SelenideElement cardNumberField = $x(".//input[@placeholder='0000 0000 0000 0000']");
    SelenideElement cardNumberFieldError = $x("(//*[@class='input__sub'][text()='Неверный формат'])[1]");
    SelenideElement monthField = $x(".//input[@placeholder='08']");
    SelenideElement monthFieldEmptyError = $x("//*[@class='input__sub'][text()='Неверный формат']");
    SelenideElement wrongMonthFieldError = $x("//*[@class='input__sub'][text()='Неверно указан срок действия карты']");
    SelenideElement yearField = $x(".//input[@placeholder='22']");
    SelenideElement yearFieldError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    SelenideElement earlyYearFieldError = $x("//*[@class='input__sub'][text()='Истёк срок действия карты']");
    SelenideElement futureYearError = $x("//*[@class='input__sub'][text()='Неверно указан срок действия карты']");
    SelenideElement cardholderField = $x("(.//*[@class='input__control'])[4]");
    SelenideElement cardholderFieldError = $x("//*[@class='input__sub'][text()='Поле обязательно для заполнения']");
    SelenideElement cvcField = $x(".//input[@placeholder='999']");
    SelenideElement cvcFieldError = $x("//*[@class='input__sub'][text()='Неверный формат']");
    SelenideElement goOnButton = $x(".//span[text()='Продолжить']");
    SelenideElement successfulPaymentPopUp = $x("//*[@class='notification__title'][text()='Успешно']");
    SelenideElement unsuccessfulPaymentPopUp = $x("//*[@class='notification__title'][text()='Ошибка']");

        public void creditPayment() {
        buyByCreditButton.click();
        buyByCreditHeading.shouldBe(Condition.visible);
    }
    public void fillingForm(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardholderField.setValue(info.getCardholder());
        cvcField.setValue(info.getCvc());
        goOnButton.click();
    }
    public void successCreditPayment() {
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(60));
    }

    public void unSuccessCardPayment() {
        unsuccessfulPaymentPopUp.shouldHave(text("Ошибка"), Duration.ofSeconds(60));
    }

    public void numberFieldError() {
        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(60));
    }

    public void emptyCardData() {
        cardNumberFieldError.shouldBe(visible);
        monthFieldEmptyError.shouldBe(visible);
        yearFieldError.shouldBe(visible);
        cardholderFieldError.shouldBe(visible);
        cvcFieldError.shouldBe(visible);
    }

    public void holderFieldError() {
        cardholderFieldError.shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(60));
    }


    public void monthFieldError() {
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(60));
    }

    public void nonExistentOrPastMonthError() {
        wrongMonthFieldError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(60));
    }

    public void yearError() {
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(60));
    }

    public void pastYearError() {
        earlyYearFieldError.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(60));
    }

    public void farFutureYearError() {
        futureYearError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(60));
    }

    public void cvcError() {
        cvcFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(60));
    }
}
