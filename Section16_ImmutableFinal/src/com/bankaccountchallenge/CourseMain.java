package com.bankaccountchallenge;

import com.bankaccountchallenge.coursebank.CourseBank;
import com.bankaccountchallenge.coursebank.CourseBankAccount;
import com.bankaccountchallenge.coursebank.CourseBankCustomer;

public class CourseMain {

    static String separator = "-".repeat(75);

    public static void main(String[] args) {

        CourseBank bank = new CourseBank(3214567);
        bank.addCustomer("Joe", 500.00, 10000.00);

        CourseBankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);
        if(bank.doTransaction(joe.getCustomerId(), CourseBankAccount.AccountType.CHECKING,35)) {
            System.out.println(joe);
        }

        if(bank.doTransaction(joe.getCustomerId(), CourseBankAccount.AccountType.CHECKING,-535)) {
            System.out.println(joe);
        }

        CourseBankAccount checking = joe.getAccount(CourseBankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));

        System.out.println(separator);

        //for (var tx : transactions.values()) {
        //    tx.setCustomerId(2);
        //    tx.setAmount(10000.00);
        //}
        //transactions.forEach((k,v) -> System.out.println(k + ": " + v));

        joe.getAccount(CourseBankAccount.AccountType.CHECKING).getTransactions().clear();
        System.out.println(separator);
        joe.getAccount(CourseBankAccount.AccountType.CHECKING).getTransactions().forEach((k,v)
                -> System.out.println(k + ": " + v));
    }
}
