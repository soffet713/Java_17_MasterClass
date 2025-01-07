package com.localeproject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Default Locale = " + Locale.getDefault());
        Locale.setDefault(Locale.JAPAN);
        System.out.println("Default Locale = " + Locale.getDefault());

        Locale jp = new Locale("jp", "JP");
        Locale en = new Locale("en");
        Locale enUS = new Locale("en", "US");
        Locale enIE = new Locale("en", "IE");
        Locale enAU = new Locale.Builder().setLanguage("en").setRegion("AU").build();
        Locale enCA = new Locale.Builder().setLanguage("en").setRegion("CA").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        for(var locale : List.of(Locale.getDefault(), jp, enUS, en, enIE, enAU, enCA, Locale.UK, enNZ)) {
            System.out.println(locale.getDisplayName() + " = " + LocalDateTime.now().format(dtf.withLocale(locale)));
        }

        DateTimeFormatter wdayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

        LocalDate May5 = LocalDate.of(2020,5,5);
        System.out.println(65);
        for(var locale: List.of(Locale.CANADA, Locale.CANADA_FRENCH, Locale.CHINA, Locale.FRANCE, Locale.KOREA,
                Locale.ITALY, Locale.GERMANY, Locale.JAPAN, Locale.US)) {
            System.out.println(locale.getDisplayName() + " : " + locale.getDisplayName(locale) + " =\n\t" +
                    May5.format(wdayMonth.withLocale(locale)));
            System.out.printf(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", May5);

            NumberFormat decimalInfo = NumberFormat.getNumberInstance(locale);
            decimalInfo.setMaximumFractionDigits(6);
            System.out.println(decimalInfo.format(123456789.123456));

            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
            Currency localCurrency = Currency.getInstance(locale);
            System.out.println(currency.format(555.555) + " [" + localCurrency.getCurrencyCode() + "] " +
                    localCurrency.getDisplayName(locale) + "/" + localCurrency.getDisplayName());
        }

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the loan amount: ");
        sc.useLocale(Locale.ITALY);
        BigDecimal myLoan = sc.nextBigDecimal();
        NumberFormat decimalInfo = NumberFormat.getNumberInstance(Locale.ITALY);
        System.out.println("My Loan = " + decimalInfo.format(myLoan));
        System.out.println("My Loan = " + myLoan);
    }
}
