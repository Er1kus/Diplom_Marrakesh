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
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getDeclinedCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getShortCardInfo() {
        return new CardInfo(generateShortCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo(generateRandomCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getOneDigitCardNumber() {
        return new CardInfo(getCardNumberOfOneDigit(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getCardNumberOfNulls() {
        return new CardInfo(getNullCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getCardNumberOfLetters() {
        return new CardInfo(generateLettersInCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getPunctuationMarksInCardNumber() {
        return new CardInfo("$%{]/.>", generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getWhitespacesInCardNumber() {
        return new CardInfo("             ", generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getLongCardNumber() {
        return new CardInfo(generateLongCardNumber(), generateMonth(), generateYear(), generateName(), generateCvc());
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getNullCardNumber() {
        return "0000 0000 0000 0000";
    }

    public static String generateLettersInCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify("???? ???? ???? ????");
    }

    public static String generateRandomCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.finance().creditCard();
    }

    public static String generateShortCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(15);
    }

    public static String generateLongCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(20);
    }

    public static String getCardNumberOfOneDigit() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(1);
    }

    public static CardInfo getNameInCyrillic() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNameInСyrillic(), generateCvc());
    }

    public static CardInfo getNameInPanjabi() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), "ਹਾਇਰੋਗਲਿਫ਼", generateCvc());
    }

    public static CardInfo getNumbersInName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNumbersInName(), generateCvc());
    }

    public static CardInfo getNameWithOneLetter() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNameWithOneLetter(), generateCvc());
    }

    public static CardInfo getLongName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateLongName(), generateCvc());
    }

    public static CardInfo getNameWithPunctuationMarks() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generatePunctuationMarksInName(), generateCvc());
    }

    public static CardInfo getNameWithUpperCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNameInUpperCase(), generateCvc());
    }

    public static CardInfo getNameWithLowerCase() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNameInLowerCase(), generateCvc());
    }

    public static CardInfo getNameWithLWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYear(), generateNameWithWhitespaces(), generateCvc());
    }

    public static String generatePunctuationMarksInName() {
        return "$%{]/.>";
    }

    public static String generateNameWithWhitespaces() {
        return "            ";
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
        return new CardInfo(getApprovedCardNumber(), generateMonthWithOneNumber(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getMonthWithNulls() {
        return new CardInfo(getApprovedCardNumber(), generateMonthWithNulls(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo getMonthWithMinus() {
        return new CardInfo(getApprovedCardNumber(), generateMonthWithMinus(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo getMonthLargerThan14() {
        return new CardInfo(getApprovedCardNumber(), generateMonthLargerThan14(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo get13Month() {
        return new CardInfo(getApprovedCardNumber(), generateNewThirteenthMonth(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo getMonthWithLetters() {
        return new CardInfo(getApprovedCardNumber(), generateMonthWithLetters(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo getMonthWithWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonthWithWhitespaces(), generateYearPlusOne(), generateName(), generateCvc());
    }

    public static CardInfo getMonthInPast() {
        return new CardInfo(getApprovedCardNumber(), generatePastMonth(), generateYear(), generateName(), generateCvc());
    }

    public static CardInfo getMonthPunctuationMarksInMonth() {
        return new CardInfo(getApprovedCardNumber(), generatePunctuationMarksInName(), generateYear(), generateName(), generateCvc());
    }

    public static String generateMonthWithNulls() {
        return "00";
    }

    public static String generateNewThirteenthMonth() {
        return "13";
    }

    public static String generateMonthWithMinus() {
        return "-1";
    }

    public static String generateMonthWithWhitespaces() {
        return "            ";
    }

    public static String generateMonthWithLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify("??");
    }

    public static String generateMonthLargerThan14() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(14, 99));
    }

    public static String generatePastMonth() {
        return LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateMonthWithOneNumber() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(1, 9));
    }

    public static CardInfo getYearWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearWithOneNumber(), generateName(), generateCvc());
    }

    public static CardInfo getYearInPast() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearInPast(), generateName(), generateCvc());
    }

    public static CardInfo getYearWithNulls() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateMonthWithNulls(), generateName(), generateCvc());
    }

    public static CardInfo getYearWithMinus() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateMonthWithMinus(), generateName(), generateCvc());
    }

    public static CardInfo getYearWithWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateMonthWithWhitespaces(), generateName(), generateCvc());
    }

    public static CardInfo getYearWithLetters() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateMonthWithLetters(), generateName(), generateCvc());
    }

    public static CardInfo getYearWithPunctuation() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generatePunctuationMarksInName(), generateName(), generateCvc());
    }

    public static CardInfo getYearInFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearInFuture(), generateName(), generateCvc());
    }

    public static CardInfo getYearInFarFuture() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearInFarFuture(), generateName(), generateCvc());
    }

    public static String generateYearInPast() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String generateYearInFuture() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String generateYearInFarFuture() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String generateYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String generateYearWithOneNumber() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(1, 9));
    }

    public static String generateYearPlusOne() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static CardInfo getCvcWithOneNumber() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), generateYearWithOneNumber());
    }

    public static CardInfo getCvcWithNulls() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), generateCvcWithNulls());
    }

    public static CardInfo getCvcWithMinus() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), "-333");
    }

    public static CardInfo getCvcWithWhitespaces() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), "    ");
    }

    public static CardInfo getCvcWithLetters() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), generateCvcWithLetters());
    }

    public static CardInfo getCvcWithPunctuation() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(), generateYearPlusOne(), generateName(), generatePunctuationMarksInName());
    }

    public static String generateCvc() {
        Faker faker = new Faker(new Locale("en"));
        return faker.number().digits(3);
    }

    public static String generateCvcWithNulls() {
        return "000";
    }

    public static String generateCvcWithLetters() {
        Faker faker = new Faker(new Locale("en"));
        return faker.letterify("???");
    }


}
