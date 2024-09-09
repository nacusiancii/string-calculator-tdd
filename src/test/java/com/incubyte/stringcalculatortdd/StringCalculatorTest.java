package com.incubyte.stringcalculatortdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	@Test
	void testNull() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(0,stringCalculator.add(null));
	}
	@Test
	void testEmptyString() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(0,stringCalculator.add(""));
	}
	@Test
	void testSingleNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(5,stringCalculator.add("5"));
	}
	@Test
	void testTwoNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(12,stringCalculator.add("5,7"));
	}
	@Test
	void testThreeNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(16,stringCalculator.add("3,9,4"));
	}
	@Test
	void testMultipleNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128,stringCalculator.add("23,67,22,7,9"));
	}
	@Test
	void testEmptyStringWithNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128,stringCalculator.add("23,67,,,,,,22,7,9"));
	}
	@Test
	void testInvalidNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(NumberFormatException.class, ()->stringCalculator.add("abc"));
	}
	@Test
	void testCommandWithNewLines() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(129, stringCalculator.add("23\n67,22,7\n10"));
	}
	@Test
	void testSemiColonDelimiter() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128,stringCalculator.add("//;\n23;67;22;7;9"));
	}
	@Test
	void testSemiColonDelimiterWithNewLines() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128,stringCalculator.add("//;\n23;67\n22;7\n9"));
	}
}