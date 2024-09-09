package com.incubyte.stringcalculatortdd;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER = ",";

	public int add(String inputCommand) {
		if (inputCommand == null||inputCommand.equals(""))
			return 0;
		String[] splitNumbers = getSplitNumbers(inputCommand);

		int sum = 0;
		StringBuilder negatives = new StringBuilder();
		boolean hasNegatives = false;
		
		for(String numStr:splitNumbers) {
			if(!numStr.isEmpty()) {
				int num = Integer.parseInt(numStr);
				if(num<0){
					if(hasNegatives) {
						negatives.append(',');
					}
					negatives.append(numStr);
					hasNegatives=true;
				} else if(num<=1000) {
					sum+=num;
				} 
			}
		}
		
		if(hasNegatives) {
			throw new RuntimeException("negatives not allowed\n following negative numbers are present:"+negatives.toString());
		} else {
			return sum;
		}
	}

	private String[] getSplitNumbers(String inputCommand) {
		String delimiters = DEFAULT_DELIMITER;
		if (inputCommand.startsWith("//")) {
			String[] commands = inputCommand.split("\n", 2);
			delimiters = commands[0].substring(2,3);
			inputCommand = commands[1];
		}
		String splitExp = "[" + delimiters + "\n]";
		String[] splitNumbers = inputCommand.split(splitExp);
		return splitNumbers;
	}
}
