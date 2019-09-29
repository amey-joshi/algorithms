package org.toc.algo.numbers;

public class Utils {
	public static boolean isNonnegativeInt(String number) {
		boolean result = true;

		for (int i = 0; i < number.length(); ++i) {
			if (!Character.isDigit(number.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static boolean isInteger(String number) {
		boolean result = false;

		char first = number.charAt(0);

		if (first == '+' || first == '-' || Character.isDigit(first)) {
			result = isNonnegativeInt(number.substring(1));
		}

		return result;
	}
}
