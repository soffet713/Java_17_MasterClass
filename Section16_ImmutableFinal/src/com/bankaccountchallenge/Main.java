package com.bankaccountchallenge;

import com.bankaccountchallenge.bank.AccountType;
import com.bankaccountchallenge.bank.Bank;
import com.bankaccountchallenge.bank.BankAccount;
import com.bankaccountchallenge.bank.BankCustomer;

import java.util.List;
import java.util.Random;

public class Main {

    static String separator = "-".repeat(75);
    static Random random = new Random();

    public static void main(String[] args) {

        Bank bank1 = new Bank(getRoutingNumber());
        bank1.addCustomer("John",2500,0);
        BankCustomer customer1 = bank1.getCustomer("000000010000000");
        System.out.print(customer1);
        bank1.openAccount("000000010000000",10000.00,AccountType.SAVINGS);

        List<BankAccount> accounts = customer1.getAccounts();
        accounts.clear();
        System.out.print(customer1);

        System.out.println();
        bank1.doTransaction("000000010000000",AccountType.CHECKING,3750.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,150.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,1250.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,-450.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,-2750.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,50.00);
        bank1.doTransaction("000000010000000",AccountType.CHECKING,-5000.00);
        System.out.print(customer1);

        BankAccount customer1Checking = customer1.getAccount(AccountType.CHECKING);
        var transactions = customer1Checking.getTransactions();
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));

        //accounts.add(new BankAccount(AccountType.CHECKING, 150000, customer1.getId()));
        //System.out.print(customer1);
        //System.out.println(accounts);

        //System.out.println();
        //System.out.println(separator);
        //System.out.println();

        //CourseBankCustomer joe = new CourseBankCustomer("Joe", 2500.00, 10000.00);
        //System.out.println(joe);

        //List<CourseBankAccount> courseAccounts = joe.getAccounts();
        //accounts.clear();
        //System.out.println(joe);
    }

    public static int getRoutingNumber() {
        int routingNumber = 0;
        int currMultiplier = 100000;
        for(int r = 0; r < 6; r++) {
            int currRandom = random.nextInt(1, 10);
            routingNumber += currMultiplier * currRandom;
            currMultiplier/=10;
        }
        return routingNumber;
    }
}
