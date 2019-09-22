import java.util.Arrays;
import java.io.Console;

public class NumberToWords {
    private String onesDigit(int n) {
        String word = "";

        switch(n) {
            case 0: word = ""; break;
            case 1: word = "one"; break;
            case 2: word = "two"; break;
            case 3: word = "three"; break;
            case 4: word = "four"; break;
            case 5: word = "five"; break;
            case 6: word = "six"; break;
            case 7: word = "seven"; break;
            case 8: word = "eight"; break;
            case 9: word = "nine";
        }

        return word.trim();
    }

    private String tensDigit(int m, int n) {
        String word = "";

        if (m == 1) {
            switch(n) {
                case 0: word = "ten"; break;
                case 1: word = "eleven"; break;
                case 2: word = "twelve"; break;
                case 3: word = "thirteen"; break;
                case 4: word = "fourteen"; break;
                case 5: word = "fifteen"; break;
                case 6: word = "sixteen"; break;
                case 7: word = "seventeen"; break;
                case 8: word = "eighteen"; break;
                case 9: word = "nineteen";
            }
        } else {
            switch(m) {
                case 2: word = "twenty"; break;
                case 3: word = "thirty"; break;
                case 4: word = "fourty"; break;
                case 5: word = "fifty"; break;
                case 6: word = "sixty"; break;
                case 7: word = "seventy"; break;
                case 8: word = "eighty"; break;
                case 9: word = "ninety";
            }
            word = word + " " + onesDigit(n);
        }

        return word.trim();
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

    public String toWords(String number) {
        final String[] suffix = {"", "thousand", "million", "billion", "trillion"};
        int[] digits = number.chars().map(c -> c - '0').toArray();
        int highest = 0;

        if (digits.length >= 13) {
            highest = 4;
        } else if (digits.length >= 10) {
            highest = 3;
        } else if (digits.length >= 7) {
            highest = 2;
        } else if (digits.length >= 4) {
            highest = 1;
        } else {
            highest = 0;
        }

        String word = "";
        int i = 0;
        for (int j = digits.length; j > 0; j -= 3) {
            int high = j;
            int low = j - 1;

            if (high - 3 >= 0) low = j - 3;
            else if (high - 2 >= 0) low = j - 2;
            else if (high - 1 >= 0) low = j - 1;

            word = tripleToWords(Arrays.copyOfRange(digits, low, high), suffix[i++]) + " " + word;
        }
        
        return word.trim();   
    }

    public static void main(String[] args) {
        Console console = System.console();
        boolean stop = false;
        NumberToWords n2w = new NumberToWords();

        do {
            String number = console.readLine("Enter an integer: ");
            System.out.println();
            console.printf("You entered %s.\n", n2w.toWords(number));

            String choice = console.readLine("Do you want to stop? [y|n]: ");
            stop = choice.compareToIgnoreCase("y") == 0;
        } while (!stop);
    }
}
