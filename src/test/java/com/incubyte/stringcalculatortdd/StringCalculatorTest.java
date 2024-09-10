package com.incubyte.stringcalculatortdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	@Test
	void testEmptyString() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(0, stringCalculator.add(""));
	}

	@Test
	void testSingleNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(1, stringCalculator.add("1"));
	}

	@Test
	void testTwoNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(3, stringCalculator.add("1,2"));
	}

	@Test
	void testMultipleNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("1,2,3"));
		assertEquals(10, stringCalculator.add("1,2,3,4"));
	}

	@Test
	void testNewLinesBetweenNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("1\n2,3"));
	}

	@Test
	void testDifferentDelimiters() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(3, stringCalculator.add("//;\n1;2"));
		assertEquals(3, stringCalculator.add("//.\n1.2"));
	}

	@Test
	void testSingleNegativeNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		RuntimeException e = assertThrows(RuntimeException.class, () -> stringCalculator.add("1,-2"));
		assertEquals("negatives not allowed\n following negative numbers are present:-2", e.getMessage());
	}

	@Test
	void testMultipleNegativeNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		RuntimeException e = assertThrows(RuntimeException.class, () -> stringCalculator.add("1,-2,3,-4,5,-6"));
		assertEquals("negatives not allowed\n following negative numbers are present:-2,-4,-6", e.getMessage());
	}

	@Test
	void testNumbersBiggerThanThousand() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(2, stringCalculator.add("2,1001"));
		assertEquals(3, stringCalculator.add("1,2,1901"));
	}

	@Test
	void testDelimitersOfAnyLength() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("//[...]\n1...2...3"));
		assertEquals(9, stringCalculator.add("//[ab]\n4ab5"));
	}

	@Test
	void testTwoDelimiters() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
	}

	@Test
	void testTwoDelimitersWithMultipleChars() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128, stringCalculator.add("//[;;][,,]\n23;;67;;22;;7,,9"));
		assertEquals(128, stringCalculator.add("//[;][,,]\n23;67;22;7,,9"));
	}

	@Test
	void testNull() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(0, stringCalculator.add(null));
	}

	@Test
	void testEmptyStringWithNumbers() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128, stringCalculator.add("23,67,,,,,,22,7,9"));
	}

	@Test
	void testInvalidNumber() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(NumberFormatException.class, () -> stringCalculator.add("abc"));
	}

	@Test
	void testSemiColonDelimiterWithNewLines() {
		StringCalculator stringCalculator = new StringCalculator();
		assertEquals(128, stringCalculator.add("//;\n23;67\n22;7\n9"));
	}

	@Test
	void testEmptyDelimiter() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//\n1,2"));
	}

	@Test
	void testWrongMultiCharDelimiter() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//...\n1,2"));
	}

	@Test
	void testWrongDelimitersEnding() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;][,]abc\n23;67;22;7,9"));
	}

	@Test
	void testWrongDelimiterStart() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;]a][,]\n23;67;22;7,9"));
	}

	@Test
	void testWrongDelimiterStart2() {
		StringCalculator stringCalculator = new StringCalculator();
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;]a[,]\n23;67;22;7,9"));
	}
}