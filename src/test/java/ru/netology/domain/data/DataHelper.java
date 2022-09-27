package ru.netology.domain.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String cardholder;
        private String cvc;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateName(), generateCvc());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getDeclinedCardNumber(), generateMonth(1), generateYear(1), generateName(), generateCvc());
    }

    public static CardInfo getShortCardInfo() {
        return new CardInfo(generateShortCardNumber(), generateMonth(1), generateYear(1), generateName(), generateCvc());
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo(generateRandomCardNumber(), generateMonth(1), generateYear(0), generateName(), generateCvc());
    }

    public static CardInfo getCardNumberOfNulls() {
        return new CardInfo("0000 0000 0000 0000", generateMonth(1), generateYear(0), generateName(), generateCvc());
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String generateRandomCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.finance().creditCard();
    }

    public static String generateShortCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(15);
    }

    public static CardInfo getNameInCyrillic() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameInСyrillic(), generateCvc());
    }

    public static CardInfo getNameInPanjabi() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "ਹਾਇਰੋਗਲਿਫ਼", generateCvc());
    }

    public static CardInfo getNumbersInName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNumbersInName(), generateCvc());
    }

    public static CardInfo getNameWithOneLetter() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameWithOneLetter(), generateCvc());
    }

    public static CardInfo getLongName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateLongName(), generateCvc());
    }

    public static CardInfo getNameWithPunctuationMarks() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "$%{]/.>", generateCvc());
    }

    public static CardInfo getNameWithUpperCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameInUpperCase(), generateCvc());
    }

    public static CardInfo getNameWithLowerCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateNameInLowerCase(), generateCvc());
    }

    public static CardInfo getNameWithLWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), "            ", generateCvc());
    }

    public static String generateNameWithOneLetter() {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify("?");
    }

    public static String generateNameInUpperCase() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName().toUpperCase();
    }

    public static String generateNameInLowerCase() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName().toLowerCase();
    }

    public static String generateLongName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String generateNameInСyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String generateNumbersInName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(12);
    }

    public static CardInfo getMonthWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateNaturalNumbers(), generateYear(1), generateName(), generateCvc());
    }

    public static CardInfo getMonthWithNulls() {
        return new CardInfo(getApprovedCardNumber(), "00", generateYear(1), generateName(), generateCvc());
    }

    public static CardInfo get13Month() {
        return new CardInfo(getApprovedCardNumber(), "13", generateYear(1), generateName(), generateCvc());
    }

    public static CardInfo getMonthInPast() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(-1), generateYear(0), generateName(), generateCvc());
    }

    public static String generateMonth(int monthToAdd) {
        return LocalDate.now().plusMonths(monthToAdd).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateNaturalNumbers() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(1, 9));
    }

    public static CardInfo getYearWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateNaturalNumbers(), generateName(), generateCvc());
    }

    public static CardInfo getYearInPast() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(-1), generateName(), generateCvc());
    }

    public static CardInfo getYearWithNulls() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), "00", generateName(), generateCvc());
    }

    public static CardInfo getYearInFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(5), generateName(), generateCvc());
    }

    public static CardInfo getYearInFarFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(6), generateName(), generateCvc());
    }

    public static String generateYear(int yearToAdd) {
        return LocalDate.now().plusYears(yearToAdd).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static CardInfo getCvcWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(1), generateName(), generateNaturalNumbers());
    }

    public static String generateCvc() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(3);
    }
}
