package com.bankcodingexercise;

import java.util.ArrayList;

public class Branch {

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String name, double transaction) {
        if(findCustomer(name)==null) {
            Customer newCustomer = new Customer(name, transaction);
            customers.add(newCustomer);
            System.out.println("New Customer Added: " + newCustomer.getName());
            return true;
        }
        System.out.printf("Customer (%s) has already been added.", name);
        return false;
    }

    public boolean addCustomerTransaction(String name, double transaction) {
        Customer customer = findCustomer(name);
        if(customer!=null) {
            customer.getTransactions().add(transaction);
            return true;
        }
        return false;
    }

    private Customer findCustomer(String name) {
        for(Customer c : customers) {
            if(c.getName()==name) {
                return c;
            }
        }
        System.out.printf("Customer (%s) wasn't found %n", name);
        return null;
    }
}
