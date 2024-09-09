package com.incubyte.stringcalculatortdd;

import java.util.regex.Pattern;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ",";

	public int add(String inputCommand) {
		if (inputCommand == null || inputCommand.equals(""))
			return 0;
		String[] splitNumbers = getSplitNumbers(inputCommand);

		int sum = 0;
		StringBuilder negatives = new StringBuilder();
		boolean hasNegatives = false;

		for (String numStr : splitNumbers) {
			if (!numStr.isEmpty()) {
				int num = Integer.parseInt(numStr);
				if (num < 0) {
					if (hasNegatives) {
						negatives.append(',');
					}
					negatives.append(numStr);
					hasNegatives = true;
				} else if (num <= 1000) {
					sum += num;
				}
			}
		}

		if (hasNegatives) {
			throw new RuntimeException(
					"negatives not allowed\n following negative numbers are present:" + negatives.toString());
		} else {
			return sum;
		}
	}

	private String[] getSplitNumbers(String inputCommand) {
		String delimiters = DEFAULT_DELIMITER;
		if (inputCommand.startsWith("//")) {
			String[] commands = inputCommand.split("\n", 2);
			delimiters = commands[0].substring(2);
			delimiters = getRegEx(delimiters);
			inputCommand = commands[1];
		}
		String splitExp = "[" + delimiters + "\n]";
		String[] splitNumbers = inputCommand.split(splitExp);
		return splitNumbers;
	}

	private String getRegEx(String delimiters) {
		if (delimiters.length() == 0) {
			throw new RuntimeException("Empty Delimiter");
		} else if (delimiters.matches("\\d")) {
			throw new RuntimeException("Delimiters can't contain numbers");
		} else if (delimiters.length() == 1) {
			return Pattern.quote(delimiters);
		} else if (delimiters.startsWith("[")) {
			return Pattern.quote(delimiters.substring(1, delimiters.length() - 1));
		} else {
			throw new RuntimeException("Invalid Delimiter");
		}

	}
}
