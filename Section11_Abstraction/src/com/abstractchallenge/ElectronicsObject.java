package com.abstractchallenge;

public class ElectronicsObject extends ProductForSale {

    public ElectronicsObject(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {

        System.out.println("This " + type + " is a futuristic, one of a kind advancement in technology.");
        System.out.printf("The price of the piece is $%6.2f %n", price);
        System.out.println(description);
    }
}
