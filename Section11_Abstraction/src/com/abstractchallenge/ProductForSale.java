package com.abstractchallenge;

public abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

    public ProductForSale() {

    }

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public abstract void showDetails();

    public double getSalesPrice(int quantity) {
        return price * quantity;
    }

    public void printPricedItem(int quantity) {
        System.out.printf("%13s | %35s | $%7.2f each | Qty. %d -> Total: $%7.2f%n",type,description,price,quantity,
                getSalesPrice(quantity));
    }
}
