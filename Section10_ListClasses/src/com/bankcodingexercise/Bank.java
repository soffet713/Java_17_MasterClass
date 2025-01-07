package com.bankcodingexercise;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>(500);
    }

    public boolean addBranch(String branchName) {
        if(findBranch(branchName)==null) {
            Branch newBranch = new Branch(branchName);
            branches.add(newBranch);
            System.out.println(branchName + " added successfully.");
            return true;
        }
        System.out.println(branchName + " could not be added.");
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initTransaction) {
        Branch branch = findBranch(branchName);
        if(branch!=null) {
            return branch.newCustomer(customerName, initTransaction);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        Branch branch = findBranch(branchName);
        if(branch!=null) {
            return branch.addCustomerTransaction(customerName, transaction);
        }
        return false;
    }

    private Branch findBranch(String branchName) {
        for(Branch b : branches) {
            if(b.getName().equalsIgnoreCase(branchName)) {
                return b;
            }
        }
        System.out.printf("Unable to find branch %s%n", branchName);
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        Branch branch = findBranch(branchName);
        boolean exists = false;
        if(branch!=null) {
            exists = true;
            System.out.println("Customer details for branch " + branchName);
            int customerCount = 0;
            if(printTransactions) {
                for(Customer c : branch.getCustomers()) {
                    customerCount++;
                    System.out.printf("Customer: %s[%d]%n", c.getName(), customerCount);
                    System.out.println("Transactions");
                    for(int t=1; t<=c.getTransactions().size(); t++) {
                        System.out.printf("[%d] Amount %10.2f%n", t, c.getTransactions().get(t-1));
                    }
                }
            } else {
                    for (Customer c : branch.getCustomers()) {
                        customerCount++;
                        System.out.printf("Customer: %s[%d]%n", c.getName(), customerCount);
                    }
                }
            }
        return exists;
    }
}
