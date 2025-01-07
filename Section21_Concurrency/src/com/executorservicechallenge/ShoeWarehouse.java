package com.executorservicechallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {

	private List<Order> shippingItems;
	private final ExecutorService fulfillmentService;
	public final static String[] PRODUCT_LIST = {"Running Shoes","Sandals","Boots","Slippers","High Tops","Loafers",
			"High Heels","Open Toes","Basketball Shoes","Skate Shoes","Bowling Shoes","Golf Shoes","Slip-ons"};

	public ShoeWarehouse() {
		this.shippingItems = new ArrayList<>();
		fulfillmentService = Executors.newFixedThreadPool(5);
	}

	public void shutDown() {
		fulfillmentService.shutdown();
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
		System.out.println(Thread.currentThread().getName() + " Incoming: " + item);
		fulfillmentService.submit(this::fulfillOrder);
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
