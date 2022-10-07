package ru.netology.domain.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.github.javafaker.CreditCardType.MASTERCARD;
import static com.github.javafaker.CreditCardType.VISA;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String cardholder;
        String cvc;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateName("en"), generateNumber(3));
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getDeclinedCardNumber(), generateMonth(1), generateYear(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getShortCardInfo() {
        return new CardInfo(generateNumber(15), generateMonth(1), generateYear(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo(generateRandomCardNumber(), generateMonth(1), generateYear(0), generateName("en"), generateNumber(3));
    }

    public static CardInfo getCardNumberOfNulls() {
        return new CardInfo("0000 0000 0000 0000", generateMonth(1), generateYear(0), generateName("pa"), generateNumber(3));
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String generateRandomCardNumber() {
        Faker faker = new Faker(new Locale("en"));
//        return faker.finance().creditCard();
        return faker.finance().creditCard(MASTERCARD);
    }

    public static String generateNumber(int digit) {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(digit);
    }

    public static CardInfo getNameInCyrillic() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateName("ru"), generateNumber(3));
    }

    public static CardInfo getNameInPanjabi() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "ਹਾਇਰੋਗਲਿਫ਼", generateNumber(3));
    }

    public static CardInfo getNumbersInName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNumber(10), generateNumber(3));
    }

    public static CardInfo getNameWithOneLetter() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateLetters("?"), generateNumber(3));
    }

    public static CardInfo getLongName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateLetters("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"), generateNumber(3));
    }

    public static CardInfo getNameWithPunctuationMarks() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "$%{]/.>", generateNumber(3));
    }

    public static CardInfo getNameWithUpperCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameInUpperCase(), generateNumber(3));
    }

    public static CardInfo getNameWithLowerCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameInLowerCase(), generateNumber(3));
    }

    public static CardInfo getNameWithLWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "            ", generateNumber(3));
    }

    public static String generateLetters(String count) {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify(count);
    }

    public static String generateNameInUpperCase() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName().toUpperCase();
    }

    public static String generateNameInLowerCase() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName().toLowerCase();
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static CardInfo getMonthWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateNumber(1), generateYear(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getMonthWithNulls() {
        return new CardInfo(getApprovedCardNumber(), "00", generateYear(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo get13Month() {
        return new CardInfo(getApprovedCardNumber(), "13", generateYear(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getMonthInPast() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(-1), generateYear(0), generateName("en"), generateNumber(3));
    }

    public static String generateMonth(int monthToAdd) {
        return LocalDate.now().plusMonths(monthToAdd).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static CardInfo getYearWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateNumber(1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getYearInPast() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(-1), generateName("en"), generateNumber(3));
    }

    public static CardInfo getYearWithNulls() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), "00", generateName("en"), generateNumber(3));
    }

    public static CardInfo getYearInFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(5), generateName("en"), generateNumber(3));
    }

    public static CardInfo getYearInFarFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(6), generateName("en"), generateNumber(3));
    }

    public static String generateYear(int yearToAdd) {
        return LocalDate.now().plusYears(yearToAdd).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static CardInfo getCvcWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(1), generateName("en"), generateNumber(1));
    }

    public static CardInfo getCvcWithTwoNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(1), generateName("en"), generateNumber(2));
    }
    public static CardInfo getEmptyCvc() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(1), generateName("en"), "");
    }


}
