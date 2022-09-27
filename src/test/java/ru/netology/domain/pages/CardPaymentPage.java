package ru.netology.domain.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.domain.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RequestPage {
    SelenideElement buyByCardButton = $x(".//span[text()='Купить']");
    SelenideElement buyByCardHeading = $(byText("Оплата по карте"));
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
    SelenideElement farFutureYearError = $x("//*[@class='input__sub'][text()='Неверно указан срок действия карты']");
    SelenideElement cardholderField = $x("(.//*[@class='input__control'])[4]");
    SelenideElement cardholderFieldError = $x("//*[@class='input__sub'][text()='Поле обязательно для заполнения']");
    SelenideElement cvcField = $x(".//input[@placeholder='999']");
    SelenideElement cvcFieldError = $x("//*[@class='input__sub'][text()='Неверный формат']");
    SelenideElement goOnButton = $x(".//span[text()='Продолжить']");
    SelenideElement successfulPaymentPopUp = $x("//*[@class='notification__title'][text()='Успешно']");
    SelenideElement unsuccessfulPaymentPopUp = $x("//*[@class='notification__title'][text()='Ошибка']");


    public void cardPayment() {
        buyByCardButton.click();
        buyByCardHeading.shouldBe(Condition.visible);
    }

//    public void creditPayment() {
//        buyByCreditButton.click();
//        buyByCreditHeading.shouldBe(Condition.visible);
//    }

    public void fillingForm(String number, String month, String year, String cardholder, String cvc) {
        cardNumberField.setValue(number);
        monthField.setValue(month);
        yearField.setValue(year);
        cardholderField.setValue(cardholder);
        cvcField.setValue(cvc);
        goOnButton.click();
    }

    public void validCardNumberPay(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(20));
    }

    public void invalidCardNumberPay(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        unsuccessfulPaymentPopUp.shouldHave(text("Ошибка"), Duration.ofSeconds(20));
    }

//    public void validCardNumberCreditPay(DataHelper.CardInfo info) {
//        creditPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(20));
//    }

    public void shortCardNumberPay(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

//    public void longCardNumberPay(DataHelper.CardInfo info) {
//        cardPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        unsuccessfulPaymentPopUp.shouldHave(text("Ошибка"), Duration.ofSeconds(20));
//    }

    public void emptyCardData(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardNumberFieldError.shouldBe(visible);
        monthFieldEmptyError.shouldBe(visible);
        yearFieldError.shouldBe(visible);
        cardholderFieldError.shouldBe(visible);
        cvcFieldError.shouldBe(visible);
    }

    public void randomCardData(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        unsuccessfulPaymentPopUp.shouldHave(text("Ошибка"), Duration.ofSeconds(20));
    }

//    public void cardNumberOfOneDigit(DataHelper.CardInfo info) {
//        cardPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
//    }

    public void cardNumberOfNulls(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        unsuccessfulPaymentPopUp.shouldHave(text("Ошибка"), Duration.ofSeconds(20));
    }

//    public void cardNumberOfLetters(DataHelper.CardInfo info) {
//        cardPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
//    }

//    public void cardNumberOfPunctuationAndOtherMarks(DataHelper.CardInfo info) {
//        cardPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
//    }

//    public void cardNumberOfWhitespaces(DataHelper.CardInfo info) {
//        cardPayment();
//        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
//        cardNumberFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
//    }

    public void nameInCyrillic(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void nameInPanjabi(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void numbersInName(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void nameWithOneLetter(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void nameLong(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void nameWithPunctuationAndOtherMarks(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void nameWithUpperCase(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(20));
    }

    public void nameWithLowerCase(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(20));
    }

    public void nameWithWhitespaces(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cardholderFieldError.shouldHave(text("Поле обязательно для заполнения"), Duration.ofSeconds(20));
    }

    public void monthWithOneNumber(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void monthWithNulls(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void monthWithMinus(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(20));
    }

    public void monthLargerThan12(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        wrongMonthFieldError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    public void monthWithLetters(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void monthWithWhitespaces(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void monthNew13Month(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        wrongMonthFieldError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    public void monthInPast(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        wrongMonthFieldError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    public void punctuationInMonth(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        monthFieldEmptyError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearWithOneNumber(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearWithInPast(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        earlyYearFieldError.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(15));
    }

    public void yearWithNulls(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        earlyYearFieldError.shouldHave(text("Истёк срок действия карты"), Duration.ofSeconds(15));
    }

    public void yearWithMinus(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearWithWhitespaces(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearWithLetters(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearWithPunctuation(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        yearFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void yearInFuture(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(15));
    }

    public void yearInFarFuture(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        farFutureYearError.shouldHave(text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    public void cvcWithOneDigit(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cvcFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void cvcWithNulls(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(15));
    }

    public void cvcWithMinus(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        successfulPaymentPopUp.shouldHave(text("Успешно"), Duration.ofSeconds(15));
    }

    public void cvcWithWhitespaces(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cvcFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void cvcWithLetters(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cvcFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }

    public void cvcWithPunctuation(DataHelper.CardInfo info) {
        cardPayment();
        fillingForm(info.getNumber(), info.getMonth(), info.getYear(), info.getCardholder(), info.getCvc());
        cvcFieldError.shouldHave(text("Неверный формат"), Duration.ofSeconds(15));
    }


}
