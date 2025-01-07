package com.bankaccountchallenge.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {

    private static int lastId = 10000000;
    private final String name;
    private final int id;
    private final List<BankAccount> accounts;

    BankCustomer(String name) {
        this(name, null);
    }

    BankCustomer(String name, List<BankAccount> accounts) {
        this.name = name;
        this.id = lastId++;
        this.accounts = accounts == null ? new ArrayList<>() : List.copyOf(accounts);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCustomerId() {
        return "%015d".formatted(id);
    }

    public final List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public final BankAccount getAccount(AccountType type) {
        BankAccount currAccount = null;
        for(BankAccount a : accounts) {
            if(a.getType()==type) {
                currAccount = a;
            }
        }
        return currAccount;
    }

    public void openAccount(BankAccount account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        String[] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Name: %s | ID: %s | %n\t%s%n".formatted(name,getCustomerId(), String.join("\n\t", accountStrings));
    }
}
