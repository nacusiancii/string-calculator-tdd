package com.incubyte.stringcalculatortdd;

public class StringCalculator {
	public int add(String inputCommand) {
		int sum = 0;
		if (inputCommand.equals(""))
			return 0;
		String[] splitNumbers = inputCommand.split(",");
		for(String s:splitNumbers) {
			int i = Integer.parseInt(s);
			sum+=i;
		}
		return sum;
	}
}
