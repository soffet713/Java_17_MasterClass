package com.threadchallenge;

public class ChallengeThread extends Thread {

	@Override
	public void run() {
		int even = 0;
		String tname = "Even Number Thread";
		for (int i = 0; i < 5; i++) {
			System.out.print(" " + even + " ");
			try {
				Thread.sleep(750); // Adding a 1-second delay between each count
			} catch (InterruptedException e) {
				System.out.println("\nWhoops!! '" + tname + "' was interrupted.");
				Thread.currentThread().interrupt();
				return;
			}
			even+=2;
		}
		System.out.println("\nEven Number Printing Thread Completed.");
	}
}
