package com.junitchallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class UtilitiesTest {

	private Utilities utils;

	@Before
	public void setup() {
		utils = new Utilities();
		System.out.println("Running a test...");
	}

	@Test
	public void everyNthChar() {
		char[] output = utils.everyNthChar(new char[] {'h','e','l','l','o'},2);
		assertArrayEquals(new char[] {'e','l'}, output);
		char[] output2 = utils.everyNthChar(new char[] {'h','e','l','l','o'},8);
		assertArrayEquals(new char[] {'h','e','l','l','o'}, output2);
	}

	@Test
	public void removePairs() {
		assertEquals("ABCDEF", utils.removeAdjacentPairs("AABCDDEFF"));
		assertEquals("ABCABDEF", utils.removeAdjacentPairs("ABCCABDEEF"));

		assertEquals("ABCDEF", utils.removeAdjacentPairs("ABBBCDEEEEF"));
		assertEquals("A", utils.removeAdjacentPairs("A"));
		assertNull(utils.removeAdjacentPairs(null));
	}

	@Test
	public void converter() {
		assertEquals(300, utils.converter(10, 5));
		Assertions.assertThrows(ArithmeticException.class, () -> utils.converter(10, 0),
				"ArithmeticException (Divide by 0)");
	}

	@Test
	public void nullIfOddLength() {
		assertNull("HelloWorld! has an odd length.", utils.nullIfOddLength("HelloWorld!"));
		assertNotNull("HelloWorld has an even length.", utils.nullIfOddLength("HelloWorld"));
	}
}