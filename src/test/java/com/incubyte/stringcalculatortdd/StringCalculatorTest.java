package com.incubyte.stringcalculatortdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
	StringCalculator stringCalculator = new StringCalculator();
	@Test
	void testEmptyString() {
		assertEquals(0, stringCalculator.add(""));
	}

	@Test
	void testSingleNumber() {
		assertEquals(1, stringCalculator.add("1"));
	}

	@Test
	void testTwoNumbers() {
		assertEquals(3, stringCalculator.add("1,2"));
	}

	@Test
	void testMultipleNumbers() {
		assertEquals(6, stringCalculator.add("1,2,3"));
		assertEquals(10, stringCalculator.add("1,2,3,4"));
	}

	@Test
	void testNewLinesBetweenNumbers() {
		assertEquals(6, stringCalculator.add("1\n2,3"));
	}

	@Test
	void testDifferentDelimiters() {
		assertEquals(3, stringCalculator.add("//;\n1;2"));
		assertEquals(3, stringCalculator.add("//.\n1.2"));
	}

	@Test
	void testSingleNegativeNumber() {
		RuntimeException e = assertThrows(RuntimeException.class, () -> stringCalculator.add("1,-2"));
		assertEquals("negatives not allowed\n following negative numbers are present:-2", e.getMessage());
	}

	@Test
	void testMultipleNegativeNumbers() {
		RuntimeException e = assertThrows(RuntimeException.class, () -> stringCalculator.add("1,-2,3,-4,5,-6"));
		assertEquals("negatives not allowed\n following negative numbers are present:-2,-4,-6", e.getMessage());
	}

	@Test
	void testNumbersBiggerThanThousand() {
		assertEquals(2, stringCalculator.add("2,1001"));
		assertEquals(3, stringCalculator.add("1,2,1901"));
	}

	@Test
	void testDelimitersOfAnyLength() {
		assertEquals(6, stringCalculator.add("//[...]\n1...2...3"));
		assertEquals(9, stringCalculator.add("//[ab]\n4ab5"));
	}

	@Test
	void testTwoDelimiters() {
		assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
	}

	@Test
	void testTwoDelimitersWithMultipleChars() {
		assertEquals(128, stringCalculator.add("//[;;][,,]\n23;;67;;22;;7,,9"));
		assertEquals(128, stringCalculator.add("//[;][,,]\n23;67;22;7,,9"));
	}

	@Test
	void testNull() {
		assertEquals(0, stringCalculator.add(null));
	}

	@Test
	void testInvalidNumber() {
		assertThrows(NumberFormatException.class, () -> stringCalculator.add("abc"));
	}

	@Test
	void testSemiColonDelimiterWithNewLines() {
		assertEquals(128, stringCalculator.add("//;\n23;67\n22;7\n9"));
	}

	@Test
	void testEmptyDelimiter() {
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//\n1,2"));
	}

	@Test
	void testWrongMultiCharDelimiter() {
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//...\n1,2"));
	}

	@Test
	void testWrongDelimitersEnding() {
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;][,]abc\n23;67;22;7,9"));
	}

	@Test
	void testWrongDelimiterStart() {
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;]a][,]\n23;67;22;7,9"));
	}

	@Test
	void testWrongDelimiterStart2() {
		assertThrows(RuntimeException.class, () -> stringCalculator.add("//[;]a[,]\n23;67;22;7,9"));
	}
}