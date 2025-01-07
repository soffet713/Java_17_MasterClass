package com.bankaccountchallenge.bank;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount{

    private final AccountType type;
    private double balance;
    private final int customerId;
    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();
    private static final String separator = "-".repeat(75);

    BankAccount(AccountType type, double balance, int customerId) {
        this.type = type;
        this.balance = balance;
        this.customerId = customerId;
    }

    public AccountType getType() {
        return type;
    }

    public String getBalance() {
        return "" + balance;
    }

    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    public Map<Long, String> getTransactions() {
        Map<Long, String> transactionMap = new LinkedHashMap<>();
        for(var tx : transactions.entrySet()) {
            transactionMap.put(tx.getKey(), tx.getValue().toString());
        }
        return transactionMap;
    }

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        double newBalance = balance + amount;
        if(newBalance<0) {
            System.out.printf("Insufficient funds for $%.2f withdrawal%n",amount);
        } else {
            balance += amount;
            transactions.put(transactionId, new Transaction(routingNumber, Integer.parseInt(customerId), transactionId,
                    amount, amount>=0 ? Transaction.TransactionType.DEPOSIT : Transaction.TransactionType.WITHDRAWAL));
            System.out.println(transactions.get(transactionId));
            System.out.println(separator);
            System.out.println(amount>0 ? "Deposit Successful" : "Withdrawal Successful");
            System.out.println("New Balance: " + balance);
        }
    }

    @Override
    public String toString() {
        return "Account Type: %8s | Balance: $%s".formatted(getType(), getBalance());
    }
}
