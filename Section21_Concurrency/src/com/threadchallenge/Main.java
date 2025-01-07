package com.threadchallenge;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		long now = System.currentTimeMillis();
		Runnable oddRunnable = () -> {
			int odd = 1;
			String tname = "Odd Number Runnable";
			for (int i = 0; i < 5; i++) {
				System.out.print(" " + odd + " ");
				try {
					TimeUnit.MILLISECONDS.sleep(250);

					if (System.currentTimeMillis() - now > 713) {
						Thread.currentThread().interrupt();
					}
				} catch (InterruptedException e) {
					System.out.println("\nWhoops!! '" + tname + "' was interrupted.");
					Thread.currentThread().interrupt();
					return;
				}
				odd+=2;
			}
			System.out.println("\nOdd Number Printing Thread Completed.");
		};

		ChallengeThread firstThread = new ChallengeThread();
		Thread secondThread = new Thread(oddRunnable);

		firstThread.start();
		secondThread.start();
	}
}
