package com.bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double policyAmount = 100_000_000;
        int beneficiaries = 3;
        float percentageFloat = 1.0f / beneficiaries;
        double percentage = 1.0 / beneficiaries;

        System.out.printf("Payout = %,.2f%n", policyAmount * percentageFloat);
        System.out.printf("Payout = %,.2f%n", policyAmount * percentage);

        double totalUsingFloat = policyAmount - ((policyAmount * percentageFloat) * beneficiaries);
        System.out.printf("totalUsingFloat: %,.2f%n", totalUsingFloat);

        double totalUsingDouble = policyAmount - ((policyAmount * percentage) * beneficiaries);
        System.out.printf("totalUsingDouble: %,.2f%n", totalUsingDouble);

        String[] tests = {"15.456", "8", "10000.000001", ".123"};
        BigDecimal[] bds = new BigDecimal[tests.length];
        Arrays.setAll(bds, i -> new BigDecimal(tests[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for(var bd : bds) {
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        double[] doubles = {15.456, 8, 10000.000001, .125};
        Arrays.setAll(bds, i -> BigDecimal.valueOf(doubles[i]));
        System.out.println("-".repeat(75));
        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for(var bd : bds) {
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        //BigDecimal test1 = new BigDecimal("1.1111122222333334444455555");
        //BigDecimal test2 = BigDecimal.valueOf(1.1111122222333334444455555);
        //System.out.println("-".repeat(75));
        //System.out.printf("%-30s %-30s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        //System.out.printf("%-30s %-30d %-8d %d %n", test1, test1.unscaledValue(), test1.scale(), test1.precision());
        //System.out.printf("%-30s %-30d %-8d %d %n", test2, test2.unscaledValue(), test2.scale(), test2.precision());
        System.out.println("-".repeat(75));
        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        BigDecimal policyPayout = new BigDecimal("100000000");
        System.out.printf("%-15s %-15d %-8d %d %n", policyPayout, policyPayout.unscaledValue(), policyPayout.scale(),
                policyPayout.precision());
        policyPayout = new BigDecimal("100000000.00");
        System.out.printf("%-15s %-15d %-8d %d %n", policyPayout, policyPayout.unscaledValue(), policyPayout.scale(),
                policyPayout.precision());
        policyPayout = new BigDecimal("100e6");
        System.out.printf("%-15s %-15d %-8d %d %n", policyPayout, policyPayout.unscaledValue(), policyPayout.scale(),
                policyPayout.precision());

        System.out.println();
        System.out.println("-".repeat(75));
        BigDecimal percent32 = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries), MathContext.DECIMAL32);
        System.out.println(percent32);
        BigDecimal percent64 = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries), MathContext.DECIMAL64);
        System.out.println(percent64);
        BigDecimal percent128 = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries), MathContext.DECIMAL128);
        System.out.println(percent128);
        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),
                new MathContext(60, RoundingMode.UP));
        System.out.println(percent);
        System.out.println();
        System.out.println("-".repeat(75));
        BigDecimal checkAmount = policyPayout.multiply(percent);
        System.out.printf("%.2f%n", checkAmount);
        System.out.printf("%-15s %-15d %-8d %d %n", checkAmount, checkAmount.unscaledValue(), checkAmount.scale(),
                checkAmount.precision());
        checkAmount = checkAmount.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("%-15s %-15d %-8d %d %n", checkAmount, checkAmount.unscaledValue(), checkAmount.scale(),
                checkAmount.precision());

        System.out.println();
        System.out.println("-".repeat(75));

        BigDecimal totalChecksAmount = checkAmount.multiply(BigDecimal.valueOf(beneficiaries));
        System.out.printf("Combined: %.2f%n", totalChecksAmount);
        System.out.println("Remaining = " + policyPayout.subtract(totalChecksAmount));
        System.out.printf("%-15s %-15d %-8d %d %n", totalChecksAmount, totalChecksAmount.unscaledValue(),
                totalChecksAmount.scale(), totalChecksAmount.precision());
    }
}
