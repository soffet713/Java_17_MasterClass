package com.abstractchallenge;

public class OrderItem extends ProductForSale {

    ProductForSale product;
    protected int qty;

    public OrderItem(int qty, ProductForSale product) {
        super();
        this.product = product;
        this.qty = qty;
    }

    @Override
    public void showDetails() {

    }

    /*
    @Override
    public void showDetails() {
        System.out.println("Product Information for " + product.type);
        System.out.printf("Price: $%.2f%n", product.price);
        System.out.println("Description: " + product.description);
    }*/
}
