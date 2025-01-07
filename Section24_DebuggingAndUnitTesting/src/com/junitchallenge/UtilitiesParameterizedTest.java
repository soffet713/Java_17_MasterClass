package com.junitchallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilitiesParameterizedTest {

	private Utilities utils;
	private String input;
	private String output;

	public UtilitiesParameterizedTest(String input, String output) {
		this.input = input;
		this.output = output;
	}

	@Before
	public void setup() {
		utils = new Utilities();
		System.out.println("Running a test...");
	}

	@Test
	public void removePairs() {
		assertEquals(output, utils.removeAdjacentPairs(input));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays.asList(new Object[][] {
				{"ABCDEFF", "ABCDEF"},
				{"AB88EFFG", "AB8EFG"},
				{"112233445566", "123456"},
				{"ZYZQQB", "ZYZQB"},
				{"A", "A"}
		});
	}
}