package com.abstractchallenge;

import java.util.ArrayList;

public class Store {

    private String name;
    protected ArrayList<ProductForSale> products = new ArrayList<>();
    protected ArrayList<Order> orders;

    public Store(String name) {
        this.name = name;
        this.orders = new ArrayList<>(100);
    }

    public void listProducts() {

        for (var item : products) {
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

    public void createOrder(int orderNumber, int itemIndex, int qty) {
        Order order = new Order(new OrderItem(qty, products.get(itemIndex)));
        orders.add(orderNumber, order);
    }

    public void addItemToOrder(int orderNumber, int itemIndex,
                                      int qty) {
        orders.get(orderNumber).addItems(new OrderItem(qty, products.get(itemIndex)));
    }
}
