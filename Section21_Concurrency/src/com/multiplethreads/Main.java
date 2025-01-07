package com.multiplethreads;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		StopWatch greenWatch = new StopWatch(TimeUnit.SECONDS);
		StopWatch purpleWatch = new StopWatch(TimeUnit.SECONDS);
		StopWatch redWatch = new StopWatch(TimeUnit.SECONDS);
		StopWatch cyanWatch = new StopWatch(TimeUnit.SECONDS);
		StopWatch orangeWatch = new StopWatch(TimeUnit.SECONDS);
		Thread green = new Thread(greenWatch::countDown, ThreadColor.ANSI_GREEN.name());
		Thread purple = new Thread(() -> purpleWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
		Thread red = new Thread(redWatch::countDown, ThreadColor.ANSI_RED.name());
		Thread cyan = new Thread(() -> cyanWatch.countDown(9), ThreadColor.ANSI_CYAN.name());
		Thread orange = new Thread(() -> orangeWatch.countDown(11), ThreadColor.ANSI_ORANGE.name());
		green.start();
		purple.start();
		red.start();
		cyan.start();
		orange.start();
	}
}

class StopWatch {

	private TimeUnit timeUnit;
	private int i;

	public StopWatch(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public void countDown () {
		countDown(5);
	}

	public void countDown (int unitCount) {

		String threadName = Thread.currentThread().getName();

		ThreadColor threadColor = ThreadColor.ANSI_RESET;

		try {
			threadColor = ThreadColor.valueOf(threadName);
		} catch (IllegalArgumentException ignore) {
			// User may pass a bad color name. Just ignore this error.
		}

		String color = threadColor.color();
		for (i = unitCount; i > 0; i--) {
			try {
				timeUnit.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
		}
	}
}