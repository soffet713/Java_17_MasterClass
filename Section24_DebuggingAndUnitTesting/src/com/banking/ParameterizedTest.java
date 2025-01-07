package com.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {

	private BankAccount account;
	private double amount;
	private boolean branch;
	private double expected;

	public ParameterizedTest(double amount, boolean branch, double expected) {
		this.amount = amount;
		this.branch = branch;
		this.expected = expected;
	}

	@BeforeEach
	public void setup() {
		account = new BankAccount("John", "Snow", 7000.00, BankAccount.CHECKING);
		System.out.println("Running a test...");
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays.asList(new Object[][] {
				{100.00, true, 7100.00},
				{200.00, true, 7200.00},
				{325.15, true, 7325.24},
				{489.33, true, 7489.33},
				{1000.00, true, 8000.00}
		});
	}

	@Test
	public void name() {
		assertEquals("John", account.getFirstName());
		assertEquals("Snow", account.getLastName());
	}

	@Test
	public void deposit() {
		account.deposit(amount, branch);
		assertEquals(expected, account.getBalance(), 0.01);
	}

}
