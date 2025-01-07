package com.bankaccountchallenge.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final int routingNumber;
    private long lastTransactionId = 100000000;
    private final Map<String, BankCustomer> customers = new HashMap<>();

    public Bank(int routingNumber) {
        this.routingNumber = 1000000 + routingNumber;
    }

    public BankCustomer getCustomer(String id) {
        BankCustomer customer = customers.get(id);
        if(customer==null) {
            System.out.println("No such customer in system with ID: " + id);
        }
        return customer;
    }

    public void addCustomer(String name, double checkingInitialBalance, double savingsInitialBalance) {
        BankCustomer newCustomer = new BankCustomer(name);
        if(checkingInitialBalance>0) {
        newCustomer.openAccount(new BankAccount(AccountType.CHECKING, checkingInitialBalance, newCustomer.getId())); }
        if(savingsInitialBalance>0) {
        newCustomer.openAccount(new BankAccount(AccountType.SAVINGS, savingsInitialBalance, newCustomer.getId())); }
        customers.put(newCustomer.getCustomerId(), newCustomer);
    }

    public void openAccount(String customerId, double accountBalance, AccountType type) {
        BankCustomer currCustomer = getCustomer(customerId);
        currCustomer.openAccount(new BankAccount(type,accountBalance,Integer.parseInt(customerId)));
    }

    public void doTransaction(String id, AccountType type, double amount) {
        BankCustomer currCustomer = getCustomer(id);
        BankAccount customerAccount = currCustomer.getAccount(type);
        customerAccount.commitTransaction(routingNumber,lastTransactionId++,id,amount);
    }

    @Override
    public String toString() {
        return "Bank: routingNumber = " + routingNumber + " | lastTransactionId = " + lastTransactionId +
                " | customers = " + customers + '}';
    }
}
