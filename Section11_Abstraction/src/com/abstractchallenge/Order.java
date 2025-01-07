package com.abstractchallenge;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private ArrayList<OrderItem> items = new ArrayList<>(100);
    private boolean complete = false;

    public Order(OrderItem item) {
        items.add(item);
    }

    public Order(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItems(OrderItem item) {
        items.add(item);
    }

    public void setComplete() {
        complete = true;
    }

    public void printOrder() {
        double salesTotal = 0;
        for(OrderItem item : items) {
            item.product.printPricedItem(item.qty);
            salesTotal += item.product.getSalesPrice(item.qty);
        }
        System.out.printf("Sales Total = $%6.2f %n", salesTotal);
    }
}
