package com.incubyte.stringcalculatortdd;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ",";

	public int add(String inputCommand) {
		if (inputCommand == null)
			return 0;
		String delimiters = DEFAULT_DELIMITER;
		if (inputCommand.startsWith("//")) {
			String[] commands = inputCommand.split("\n", 2);
			delimiters = commands[0].substring(2);
			inputCommand = commands[1];
		}
		String splitExp = "[" + delimiters + "\n]";
		String[] splitNumbers = inputCommand.split(splitExp);

		int sum = 0;
		StringBuilder negatives = new StringBuilder();
		boolean hasNegatives = false;
		
		for(String numStr:splitNumbers) {
			if(!numStr.isEmpty()) {
				int num = Integer.parseInt(numStr);
				if(num>=0) {
					sum+=num;
				} else {
					if(hasNegatives) {
						negatives.append(',');
					}
					negatives.append(numStr);
					hasNegatives=true;
				}
			}
		}
		if(hasNegatives) {
			throw new RuntimeException("negatives not allowed\n following negative numbers are present:"+negatives.toString());
		} else {
			return sum;
		}
	}
}
