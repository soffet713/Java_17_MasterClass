package com.bankaccountchallenge.coursebank;

import java.util.LinkedHashMap;
import java.util.Map;

public class CourseBankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType type;
    private double balance;
    private final Map<Long, CourseTransaction> transactions = new LinkedHashMap<>();

    CourseBankAccount(AccountType type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() {
        Map<Long, String> txMap = new LinkedHashMap<>();
        for(var tx : transactions.entrySet()) {
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        balance += amount;
        transactions.put(transactionId, new CourseTransaction(routingNumber, transactionId, Integer.parseInt(customerId), amount));
    }

    @Override
    public String toString() {
        return "Account Type: %9s | Balance: $%9.2f".formatted(getType(), getBalance());
    }
}
