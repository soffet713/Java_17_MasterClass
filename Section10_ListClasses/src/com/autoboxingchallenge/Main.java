package com.autoboxingchallenge;

import java.util.ArrayList;

record Customer(String name, ArrayList<Double> transactions) {

    public Customer(String name, double initialDeposit) {
        this(name.toUpperCase(), new ArrayList<Double>(500));
        transactions.add(initialDeposit);
    }
}

public class Main {

    public static void main(String[] args) {

        Customer john = new Customer("John H", 15000.0);

        System.out.println(john);

        Bank bank = new Bank("Chase");
        bank.addCustomer("Matthew B", 500.0);
        bank.addCustomer("John H", 15000.0);
        System.out.println(bank);
        System.out.println("\n");

        bank.addTransaction("john h", 200.0);
        bank.addTransaction("john h", -2500.00);
        bank.addTransaction("JOHN H", -375.0);
        bank.addTransaction("John H", 35.0);
        bank.addTransaction("John H", 25675.0);
        bank.addTransaction("John h", -17250);
        bank.printStatement("John H");
    }
}

/*
class Customer {

    private String name;
    protected ArrayList<Double> transactions;

    public Customer(String name) {
        this.name = name;
        this.transactions = new ArrayList<Double>();
    }

    public String getName() {
        return name;
    }
}
*/

class Bank {

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Bank(String name) {
        this.name = name;
    }

    private Customer getCustomer(String name) {
        for(Customer c : customers) {
            if(c.name().equalsIgnoreCase(name)) {
                return c;
            }
        }
        System.out.printf("Customer (%s) wasn't found %n", name);
        return null;
    }

    public void addCustomer(String name, double initDeposit) {
        if(getCustomer(name)==null) {
            Customer newCustomer = new Customer(name, initDeposit);
            customers.add(newCustomer);
            System.out.println("New Customer Added: " + newCustomer);
            return;
        }
        System.out.printf("Customer (%s) has already been added.", name);
    }

    public void addTransaction(String name, double transaction) {
        Customer customer = getCustomer(name);
        if(customer!=null) {
            customer.transactions().add(transaction);
        }
    }

    public void printStatement(String name) {

        Customer customer = getCustomer(name);

        if(customer==null) { return; }

        System.out.println("Statement for " + name);
        System.out.println("-".repeat(45));
        for (double t : customer.transactions()) {
            System.out.printf("Transaction Amount: $%10.2f (%s)%n", t, t < 0 ? "debit" : "credit");
        }

        //From course
        /*
        System.out.println("-".repeat(30));
        System.out.println("Customer Name: " + customer.name());
        System.out.println("Transactions:);
        for(double d : customer.transactions()) {
            System.out.printf("$%10.2f (%s)%n", d, d < 0 ? "debit" : "credit");
        }
         */
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }
}
