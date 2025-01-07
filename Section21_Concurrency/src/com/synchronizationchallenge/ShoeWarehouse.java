package com.synchronizationchallenge;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {

	private List<Order> shippingItems;
	public final static String[] PRODUCT_LIST = {"Running Shoes","Sandals","Boots","Slippers","High Tops","Loafers",
			"High Heels","Open Toes","Basketball Shoes","Skate Shoes","Bowling Shoes","Golf Shoes","Slip-ons"};

	public ShoeWarehouse() {
		this.shippingItems = new ArrayList<>();
	}

	public synchronized void receiveOrder(Order item) {

		while (shippingItems.size() > 15) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		shippingItems.add(item);
		System.out.println("Incoming: " + item);
		notifyAll();
	}

	public synchronized Order fulfillOrder() {

		while (shippingItems.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		Order item = shippingItems.remove(0);
		System.out.println(Thread.currentThread().getName() + " Fulfilled: " + item);
		notifyAll();
		return item;
	}
}
