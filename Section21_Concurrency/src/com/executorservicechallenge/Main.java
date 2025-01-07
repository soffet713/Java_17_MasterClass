package com.executorservicechallenge;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

record Order(long orderId, String item, int qty) {
};

public class Main {

	private static final Random rand = new Random();

	public static void main(String[] args) {

		var multiExecutor = Executors.newCachedThreadPool();
		ShoeWarehouse warehouse = new ShoeWarehouse();

		Callable<Order> orderingTask = () -> {
			Order shoeOrder = generateOrder();
			try {
				Thread.sleep(rand.nextInt(500,3500));
				warehouse.receiveOrder(shoeOrder);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return shoeOrder;
		};

		try {
			for (int i = 0; i < 15; i++) {
				Thread.sleep(rand.nextInt(500, 2500));
				multiExecutor.submit(() -> warehouse.receiveOrder(generateOrder()));
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		multiExecutor.shutdown();
		try {
			multiExecutor.awaitTermination(7, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		warehouse.shutDown();
	}

	public static void producerconsumermain(String[] args) {

		ShoeWarehouse warehouse = new ShoeWarehouse();
		Thread producerThread = new Thread(() -> {
			for (int j = 0; j < 10; j++) {
				warehouse.receiveOrder(new Order(rand.nextLong(1000000,9999999),
						ShoeWarehouse.PRODUCT_LIST[rand.nextInt(0,12)],
						rand.nextInt(1,3)));
			}
		});
		producerThread.start();

		for (int i = 0; i < 2; i++) {
			Thread consumerThread = new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					Order item = warehouse.fulfillOrder();
				}
			});
			consumerThread.start();
		}
	}

	private static Order generateOrder () {
		return new Order(rand.nextLong(1000000,9999999),
				ShoeWarehouse.PRODUCT_LIST[rand.nextInt(0,12)],
				rand.nextInt(1,5));
	}
}
