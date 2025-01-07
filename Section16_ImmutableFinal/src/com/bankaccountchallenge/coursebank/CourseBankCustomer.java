package com.bankaccountchallenge.coursebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseBankCustomer {

    private static int lastCustomerId = 10000000;
    private final String name;
    private final int customerId;
    private final List<CourseBankAccount> accounts = new ArrayList<>();

    CourseBankCustomer(String name, double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new CourseBankAccount(CourseBankAccount.AccountType.CHECKING, checkingAmount));
        accounts.add(new CourseBankAccount(CourseBankAccount.AccountType.SAVINGS, savingsAmount));
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    public List<CourseBankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public CourseBankAccount getAccount(CourseBankAccount.AccountType type) {
        for(var account : accounts) {
            if(account.getType() == type) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Name: %s | ID: %09d | %n\t%s%n".formatted(name, customerId,String.join("\n\t", accountStrings));
    }
}
