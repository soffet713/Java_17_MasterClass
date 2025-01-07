package com.abstractchallenge;

public class FurnitureObject extends ProductForSale{

    public FurnitureObject(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {

        System.out.println("This " + type + " is an amazingly elegant piece of furniture.");
        System.out.printf("The price of the piece is $%6.2f %n", price);
        System.out.println(description);
    }
}
