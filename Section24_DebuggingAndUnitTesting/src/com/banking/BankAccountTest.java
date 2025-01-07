package com.banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

	private BankAccount account;
	private static int count;

	@org.junit.jupiter.api.BeforeAll
	public static void beforeClass() {
		System.out.println("This executes before any test cases...Count = " + count++);
	}

	@org.junit.jupiter.api.BeforeEach
	public void setup() {
		account = new BankAccount("John", "Doe", 14000.00, BankAccount.CHECKING);
		System.out.println("Running a test...");
	}

	@org.junit.jupiter.api.Test
	public void deposit() {
		double balance = account.deposit(200.00, true);
		assertEquals(14200.00, balance, 0);
		//assertEquals(14200.00, account.getBalance(), 0);
	}

	@org.junit.jupiter.api.Test
	public void withdrawal_branch() {
		double balance = account.withdrawal(600.00, true);
		assertEquals(13400.00, balance, 0);
	}

	@org.junit.jupiter.api.Test
	public void withdrawal_notBranchOver500() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdrawal(600.00, false),
				"IllegalArgumentException was expected. Withdrawals > 500.00 cannot be made at ATMs.");
	}

	@org.junit.jupiter.api.Test
	public void withdrawal_notBranchUnder500() {
		double balance = account.withdrawal(400.00, false);
		assertEquals(13600.00, balance, 0);
	}

	@org.junit.jupiter.api.Test
	public void getBalance_deposit() {
		account.deposit(200.00, true);
		assertEquals(14200.00, account.getBalance(), 0);
	}

	@org.junit.jupiter.api.Test
	public void getBalance_withdrawal() {
		account.withdrawal(200.00, true);
		assertEquals(13800.00, account.getBalance(), 0);
	}

	@Test
	public void name() {
		assertEquals("John", account.getFirstName());
		assertEquals("Doe", account.getLastName());
	}

	@org.junit.jupiter.api.Test
	public void isChecking_true() {
		assertTrue(account.isChecking());
	}

	@org.junit.jupiter.api.AfterAll
	public static void afterClass() {
		System.out.println("This executes after any test cases...Count = " + count++);
	}

	@org.junit.jupiter.api.AfterEach
	public void teardown() {
		System.out.println("Count = " + count++);
	}
}