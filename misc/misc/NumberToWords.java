package misc;

import java.io.Console;
import java.util.Arrays;

/**
 * A class to express an integer in English words.
 *
 * @author Amey Joshi
 *
 */
// @To Do Handle decimals.
// @To Do Handle errors like illegal characters.
// @To Do Handle scientific notation.
public class NumberToWords {
	private String onesDigit(int n) {
		String word = "";

		switch (n) {
		case 0:
			word = "";
			break;
		case 1:
			word = "one";
			break;
		case 2:
			word = "two";
			break;
		case 3:
			word = "three";
			break;
		case 4:
			word = "four";
			break;
		case 5:
			word = "five";
			break;
		case 6:
			word = "six";
			break;
		case 7:
			word = "seven";
			break;
		case 8:
			word = "eight";
			break;
		case 9:
			word = "nine";
			break;
		default:
			word = "";
			break;
		}

		return word.trim();
	}

	private String tensDigit(int m, int n) {
		String word = "";

		if (m == 1) {
			switch (n) {
			case 0:
				word = "ten";
				break;
			case 1:
				word = "eleven";
				break;
			case 2:
				word = "twelve";
				break;
			case 3:
				word = "thirteen";
				break;
			case 4:
				word = "fourteen";
				break;
			case 5:
				word = "fifteen";
				break;
			case 6:
				word = "sixteen";
				break;
			case 7:
				word = "seventeen";
				break;
			case 8:
				word = "eighteen";
				break;
			case 9:
				word = "nineteen";
				break;
			default:
				word = "";
				break;
			}
		} else {
			switch (m) {
			case 2:
				word = "twenty";
				break;
			case 3:
				word = "thirty";
				break;
			case 4:
				word = "fourty";
				break;
			case 5:
				word = "fifty";
				break;
			case 6:
				word = "sixty";
				break;
			case 7:
				word = "seventy";
				break;
			case 8:
				word = "eighty";
				break;
			case 9:
				word = "ninety";
				break;
			default:
				word = "";
				break;
			}
			word = word + " " + onesDigit(n);
		}

		return word.trim();
	}

	private int[] filterPrefixedZeros(int[] digits) {
		int i = 0;

		for (i = 0; i < digits.length; ++i) {
			if (digits[i] != 0) {
				break;
			}
		}

		return Arrays.copyOfRange(digits, i, digits.length);
	}

	private String tripleToWords(int[] digits, String suffix) {
		String word = "";

		if (digits.length == 1) {
			word = onesDigit(digits[0]) + " " + suffix;
		} else if (digits.length == 2) {
			word = tensDigit(digits[0], digits[1]) + " " + suffix;
		} else if (digits.length == 3) {
			if (digits[0] != 0) {
				if (digits[1] != 0 || digits[2] != 0) {
					word = onesDigit(digits[0]) + " hundred and " + tensDigit(digits[1], digits[2]) + " " + suffix;
				} else {
					word = onesDigit(digits[0]) + " hundred" + " " + suffix;
				}
			} else { // digits[0] == 0.
				if (digits[1] != 0 || digits[2] != 0) {
					word = tensDigit(digits[1], digits[2]) + " " + suffix;
				}
			}
		}

		return word.trim();
	}

    private boolean isLegal(String number) {
        boolean legal = true;
        int start = 0;

        if (number.charAt(0) == '-' || number.charAt(0) == '+') {
            start = 1;
        }

        for (int i = start; i < number.length(); ++i) {
            if (!Character.isDigit(number.charAt(i))) {
                legal = false;
                break;
            }
        }

        return legal;
    }

	public String toWords(String number) {
		final String[] suffix = { "", "thousand", "million", "billion", "trillion" };
        String result = "Not-a-number";

        if (isLegal(number)) {
            boolean minus = false;
            if (number.charAt(0) == '-') {
                minus = true;
                number = number.substring(1);
            } else if (number.charAt(0) == '+') {
                number = number.substring(1);
            }

            int[] digits = filterPrefixedZeros(number.chars().map(c -> c - '0').toArray());

            String word = "";
            int i = 0;

            if (digits.length == 0) {
                word = "zero";
            } else {
                for (int j = digits.length; j > 0; j -= 3) {
                    int high = j;
                    int low = j - 1;

                    if (high - 3 >= 0)
                        low = j - 3;
                    else if (high - 2 >= 0)
                        low = j - 2;
                    else if (high - 1 >= 0)
                        low = j - 1;

                    word = tripleToWords(Arrays.copyOfRange(digits, low, high), suffix[i++]) + " " + word;
                }
            }

            result = minus ? "minus " + word.trim() : word.trim();
        }

		return result;
	}

	public static void main(String[] args) {
		Console console = System.console();
		boolean stop = false;
		NumberToWords n2w = new NumberToWords();

		do {
			String number = console.readLine("Enter an integer: ");
			console.printf("\nYou entered %s.\n", n2w.toWords(number));

			String choice = console.readLine("Do you want to stop? [y|n]: ");
			stop = choice.compareToIgnoreCase("y") == 0;
		} while (!stop);
	}
}
