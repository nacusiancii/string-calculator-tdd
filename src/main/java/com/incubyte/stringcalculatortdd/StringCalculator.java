package com.incubyte.stringcalculatortdd;

import java.util.Arrays;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ",";

	public int add(String inputCommand) {
		if (inputCommand == null)
			return 0;
		String[] splitNumbers = inputCommand.split(DEFAULT_DELIMITER);
		return Arrays.stream(splitNumbers)
				.filter(a->!a.equals(""))
				.map(Integer::parseInt)
				.reduce(Integer::sum).orElse(0);
	}
}
