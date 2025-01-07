package com.bankaccountchallenge.bank;

public class Transaction {

    enum TransactionType {DEPOSIT,WITHDRAWAL};

    private final int routingNumber;
    private final int customerId;
    private final long transactionId;
    private final double transactionAmount;
    private final TransactionType transactionType;

    public Transaction(int routingNumber, int customerId, long transactionId, double transactionAmount,
                       TransactionType transactionType) {
        this.routingNumber = routingNumber;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return ("Transaction: routingNumber = %d | customerId = %015d | transactionId = %9d |" +
                " transactionAmount = $%9.2f").formatted(routingNumber,customerId,transactionId,transactionAmount);
    }
}
