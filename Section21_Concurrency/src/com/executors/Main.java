package com.executors;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class ColorThreadFactory implements ThreadFactory {

	private String threadName;
	private int colorValue = 1;

	public ColorThreadFactory() {
	}

	public ColorThreadFactory (ThreadColor color) {
		this.threadName = color.name();
	}

	@Override
	public Thread newThread(Runnable r) {

		Thread thread = new Thread(r);
		String name = threadName;
		if (name == null) {
			name = ThreadColor.values()[colorValue].name();
		}

		if (++colorValue > (ThreadColor.values().length - 1)) {
			colorValue = 1;
		}
		thread.setName(name);
		return thread;
	}
}

public class Main {

	public static void main(String[] args) {

		var multiExecutor = Executors.newCachedThreadPool();
		List<Callable<Integer>> taskList = List.of(
				() -> Main.sum(1, 10, 1, "red"),
				() -> Main.sum(5,50,5,"orange"),
				() -> Main.sum(2, 20, 2, "yellow"),
				() -> Main.sum(7, 70, 7, "green"),
				() -> Main.sum(10, 100, 10, "cyan"),
				() -> Main.sum(11, 99, 11, "purple")
		);
		try {
			var results = multiExecutor.invokeAll(taskList);
			for (var result : results) {
				System.out.println(result.get(500, TimeUnit.MILLISECONDS));
			}
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			throw new RuntimeException(e);
		} finally {
			multiExecutor.shutdown();
		}
	}

	public static void cachedmain(String[] args) {

		Random rand = new Random();
		var multiExecutor = Executors.newCachedThreadPool();
		try {
			multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
			multiExecutor.execute(() -> Main.sum(5,50,5,"orange"));
			multiExecutor.execute(() -> Main.sum(2, 20, 2, "yellow"));
			var greenValue = multiExecutor.submit(() ->
					Main.sum(7, 70, 7, "green"));
			var cyanValue = multiExecutor.submit(() ->
					Main.sum(10, 100, 10, "cyan"));
			var purpleValue = multiExecutor.submit(() ->
					Main.sum(11, 99, 11, "purple"));

			try {
				System.out.println(greenValue.get(500, TimeUnit.SECONDS));
				System.out.println(cyanValue.get(500, TimeUnit.SECONDS));
				System.out.println(purpleValue.get(500, TimeUnit.SECONDS));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			//try {
			//	TimeUnit.SECONDS.sleep(1);
			//} catch (InterruptedException e) {
			//	throw new RuntimeException(e);
			//}

			//System.out.println("-".repeat(45));
			//System.out.println("New Tasks Will be executed..");
			//System.out.println("-".repeat(45));

			//for (var color : new String[]{"red","cyan","purple","green","yellow","white","blue","orange"}) {
			//	multiExecutor.execute(() -> Main.sum(1, 50, rand.nextInt(1,7), color));
			//}

		} finally {
			multiExecutor.shutdown();
		}
	}

	public static void fixedmain(String[] args) {

		int count = 9;
		var multiExecutor = Executors.newFixedThreadPool(count, new ColorThreadFactory());

		for (int i = 0; i < count; i++) {
			multiExecutor.execute(Main::countDown);
		}
		multiExecutor.shutdown();
	}

	public static void singlemain(String[] args) {

		var redExecutor = Executors.newSingleThreadExecutor(
				new ColorThreadFactory(ThreadColor.ANSI_RED)
		);
		redExecutor.execute(Main::countDown);
		redExecutor.shutdown();

		boolean isDone = false;
		try {
			isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		if (isDone) {
			System.out.println("Red finished, starting Orange..");
			var orangeExecutor = Executors.newSingleThreadExecutor(
					new ColorThreadFactory(ThreadColor.ANSI_ORANGE)
			);
			orangeExecutor.execute(Main::countDown);
			orangeExecutor.shutdown();

			try {
				isDone = orangeExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		if (isDone) {
			System.out.println("Orange finished, starting Yellow..");
			var yellowExecutor = Executors.newSingleThreadExecutor(
					new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
			);
			yellowExecutor.execute(Main::countDown);
			yellowExecutor.shutdown();

			try {
				isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		if (isDone) {
			System.out.println("Yellow finished, starting Green..");
			var greenExecutor = Executors.newSingleThreadExecutor(
					new ColorThreadFactory(ThreadColor.ANSI_GREEN)
			);
			greenExecutor.execute(Main::countDown);
			greenExecutor.shutdown();

			try {
				isDone = greenExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		if (isDone) {
			System.out.println("Green finished, starting Cyan..");
			var cyanExecutor = Executors.newSingleThreadExecutor(
					new ColorThreadFactory(ThreadColor.ANSI_CYAN)
			);
			cyanExecutor.execute(Main::countDown);
			cyanExecutor.shutdown();

			try {
				isDone = cyanExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		if (isDone) {
			System.out.println("Cyan finished, starting Purple..");
			var purpleExecutor = Executors.newSingleThreadExecutor(
					new ColorThreadFactory(ThreadColor.ANSI_PURPLE)
			);
			purpleExecutor.execute(Main::countDown);
			purpleExecutor.shutdown();

			try {
				isDone = purpleExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (isDone) {
				System.out.println("All process completed.");
			}
		}
	}

	public static void notmain(String[] args) {

		Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());
		Thread orange = new Thread(Main::countDown, ThreadColor.ANSI_ORANGE.name());
		Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());
		Thread green = new Thread(Main::countDown, ThreadColor.ANSI_GREEN.name());
		Thread cyan = new Thread(Main::countDown, ThreadColor.ANSI_CYAN.name());
		Thread purple = new Thread(Main::countDown, ThreadColor.ANSI_PURPLE.name());

		red.start();

		try {
			red.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		orange.start();

		try {
			orange.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		yellow.start();

		try {
			yellow.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		green.start();

		try {
			green.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		cyan.start();

		try {
			cyan.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		purple.start();

		try {
			purple.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void countDown () {

		String threadName = Thread.currentThread().getName();
		var threadColor = ThreadColor.ANSI_RESET;
		try {
			threadColor = ThreadColor.valueOf(threadName.toUpperCase());
		} catch (IllegalArgumentException ignore) {
			// User may pass a bad color name. Ignore this error.
		}

		String color = threadColor.color();
		for (int i = 20; i >= 0; i--) {
			System.out.println(color + " " + threadName.replace("ANSI_", "") + "  " + i);
		}
	}

	private static int sum (int start, int end, int delta, String colorString) {

		var threadColor = ThreadColor.ANSI_RESET;
		try {
			threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
		} catch (IllegalArgumentException e) {
			// User may pass a bad color name. Ignore this error.
		}

		String color = threadColor.color();
		int sum = 0;
		for (int i = start; i <= end; i += delta) {
			//System.out.println(color + "Adding " + colorString + " " + i + " to " + sum);
			sum += i;
		}
		System.out.println(color + Thread.currentThread().getName() + ", " + colorString + "  " + sum);
		return sum;
	}

	/*
	private static void sum (int start, int end, int delta, String colorString) {

		var threadColor = ThreadColor.ANSI_RESET;
		try {
			threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
		} catch (IllegalArgumentException e) {
			// User may pass a bad color name. Ignore this error.
		}

		String color = threadColor.color();
		int sum = 0;
		for (int i = start; i <= end; i += delta) {
			//System.out.println(color + "Adding " + colorString + " " + i + " to " + sum);
			sum += i;
		}
		System.out.println(color + Thread.currentThread().getName() + ", " + colorString + "  " + sum);
	}
	 */
}
