package com.incubyte.stringcalculatortdd;

import java.util.Arrays;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ",";

	public int add(String inputCommand) {
		if (inputCommand == null)
			return 0;
		String delimiters = DEFAULT_DELIMITER;
		if(inputCommand.startsWith("//")) {
			String[] commands = inputCommand.split("\n",2);
			delimiters = commands[0].substring(2);
			inputCommand = commands[1];
		}
		String splitExp = "["+delimiters+"\n]";
		String[] splitNumbers = inputCommand.split(splitExp);
		return Arrays.stream(splitNumbers)
				.filter(a->!a.equals(""))
				.map(Integer::parseInt)
				.reduce(Integer::sum).orElse(0);
	}
}
