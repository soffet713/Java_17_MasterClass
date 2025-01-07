package com.bankaccountchallenge.coursebank;

import java.util.HashMap;
import java.util.Map;

public class CourseBank {

    public final int routingNumber;
    private long lastTransactionId = 1;
    private final Map<String, CourseBankCustomer> customers;

    public CourseBank(int routingNumber) {
        this.routingNumber = routingNumber;
        customers = new HashMap<>();
    }

    public CourseBankCustomer getCustomer(String id) {
        return customers.get(id);
    }

    public void addCustomer(String name, double checkingInitialBalance, double savingsInitialBalance) {
        CourseBankCustomer customer = new CourseBankCustomer(name, checkingInitialBalance, savingsInitialBalance);
        customers.put(customer.getCustomerId(), customer);
    }

    public boolean doTransaction(String id, CourseBankAccount.AccountType accountType, double amount) {
        CourseBankCustomer customer = customers.get(id);
        if(customer != null) {
            CourseBankAccount account = customer.getAccount(accountType);
            if(account != null) {
                if(account.getBalance() + amount < 0) {
                    System.out.println("Insufficient funds");
                } else {
                    account.commitTransaction(routingNumber, lastTransactionId++, id, amount);
                    return true;
                }
            }
        } else {
            System.out.println("Invalid customer id");
        }
        return false;
    }
}
