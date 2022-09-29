package ru.netology.domain.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    SelenideElement mainTitleHeading = $x("//*[@id=\"root\"]/div/h2");
    SelenideElement mainPageBody = $x("//*[@id=\"root\"]/div/div");
    SelenideElement buyByCardButton = $x("//*[@id=\"root\"]/div/button[1]");
    SelenideElement buyByCreditButton = $x("//*[@id=\"root\"]/div/button[2]");


    public MainPage() {
        mainTitleHeading.shouldHave(Condition.text("Путешествие дня")).shouldBe(Condition.visible);
        mainPageBody.shouldBe(Condition.visible);
    }

    public CardPaymentPage chooseToPayByCard() {
        buyByCardButton.click();
        return new CardPaymentPage();
    }

    public CreditPaymentPage chooseToPayByCredit() {
        buyByCreditButton.click();
        return new CreditPaymentPage();
    }
}