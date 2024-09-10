package com.incubyte.stringcalculatortdd;

import java.util.regex.Pattern;

public class StringCalculator {

	private static final int MAX_ALLOWED_NUMBER = 1000;
	private static final String DELIMITER_PREFIX = "//";
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
				} else if (num <= MAX_ALLOWED_NUMBER) {
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
		if (inputCommand.startsWith(DELIMITER_PREFIX)) {
			String[] commands = inputCommand.split("\n", 2);
			delimiters = commands[0].substring(2);
			delimiters = getRegEx(delimiters);
			inputCommand = commands[1];
		}
		String splitExp = "[" + delimiters + "\n]";
		String[] splitNumbers = inputCommand.split(splitExp);
		return splitNumbers;
	}

	public String getRegEx(String delimiters) {
		String closeSqBracketExp = Pattern.quote("]");
		if (delimiters.length() == 0) {
			throw new RuntimeException("Empty Delimiter");
		} else if (delimiters.length() == 1) {
			return Pattern.quote(delimiters);
		} else if (delimiters.startsWith("[")) {
			if(!delimiters.endsWith("]")) {
				throw new RuntimeException("Delimiters should end with ]");
			}
			
			String[] splitDelimiters = delimiters.split(closeSqBracketExp);
			StringBuilder regEx = new StringBuilder();
			boolean startedRegEx = false;

			for(int i=0;i<splitDelimiters.length;i++) {
				String delim = splitDelimiters[i];
				if(!delim.startsWith("[")) {
					throw new RuntimeException("Delimiters should start with '[' when multiple are present");
				}
				String quotedRegEx = Pattern.quote(delim.substring(1));
				if(startedRegEx) {
					regEx.append("|");
				}
				regEx.append(quotedRegEx);
				startedRegEx = true;
			}
			return regEx.toString();
		} else {
			throw new RuntimeException("Invalid Delimiter");
		}

	}
}
