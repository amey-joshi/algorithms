/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.toc.algo.numbers;

import java.io.Console;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * A class that reads an integer and converts in into words. For example, the
 * integer 1234 will be converted to one thousand two hundred and thirty four.
 * 
 * It admits a plus or a minus sign at the start of the number and it reads
 * numbers up to 1 less than 1000 trillions on either side of zero.
 * 
 * @author amey
 */
public class IntToWord {

    private String onesDigit(int n) {
        String word;

        switch (n) {
        case 0:
            word = "";
            break;
        case 1:
            word = "One";
            break;
        case 2:
            word = "Two";
            break;
        case 3:
            word = "Three";
            break;
        case 4:
            word = "Four";
            break;
        case 5:
            word = "Five";
            break;
        case 6:
            word = "Six";
            break;
        case 7:
            word = "Seven";
            break;
        case 8:
            word = "Eight";
            break;
        case 9:
            word = "Nine";
            break;
        default:
            word = "";
            break;
        }

        return word.trim();
    }

    private String tensDigit(int m, int n) {
        String word;

        if (m == 1) {
            switch (n) {
            case 0:
                word = "Ten";
                break;
            case 1:
                word = "Eleven";
                break;
            case 2:
                word = "Twelve";
                break;
            case 3:
                word = "Thirteen";
                break;
            case 4:
                word = "Fourteen";
                break;
            case 5:
                word = "Fifteen";
                break;
            case 6:
                word = "Sixteen";
                break;
            case 7:
                word = "Seventeen";
                break;
            case 8:
                word = "Eighteen";
                break;
            case 9:
                word = "Nineteen";
                break;
            default:
                word = "";
                break;
            }
        } else {
            switch (m) {
            case 2:
                word = "Twenty";
                break;
            case 3:
                word = "Thirty";
                break;
            case 4:
                word = "Forty";
                break;
            case 5:
                word = "Fifty";
                break;
            case 6:
                word = "Sixty";
                break;
            case 7:
                word = "Seventy";
                break;
            case 8:
                word = "Eighty";
                break;
            case 9:
                word = "Ninety";
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
        int i;

        for (i = 0; i < digits.length; ++i) {
            if (digits[i] != 0) {
                break;
            }
        }

        return Arrays.copyOfRange(digits, i, digits.length);
    }

    private String tripleToWords(int[] digits, String suffix) {
        String word = "";

        switch (digits.length) {
        case 1:
            word = onesDigit(digits[0]) + " " + suffix;
            break;
        case 2:
            word = tensDigit(digits[0], digits[1]) + " " + suffix;
            break;
        case 3:
            if (digits[0] != 0) {
                if (digits[1] != 0 || digits[2] != 0) {
                    word = onesDigit(digits[0]) + " Hundred " + tensDigit(digits[1], digits[2]) + " " + suffix;
                } else {
                    word = onesDigit(digits[0]) + " Hundred" + " " + suffix;
                }
            } else { // digits[0] == 0.
                if (digits[1] != 0 || digits[2] != 0) {
                    word = tensDigit(digits[1], digits[2]) + " " + suffix;
                }
            }
            break;
        default:
            break;
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
        final String[] suffix = { "", "Thousand", "Million", "Billion", "Trillion" };
        String result = "Not an integer";

        if (isLegal(number)) {
            boolean minus = false;
            if (number.charAt(0) == '-') {
                minus = true;
                number = number.substring(1);
            } else if (number.charAt(0) == '+') {
                number = number.substring(1);
            }

            int[] digits = filterPrefixedZeros(number.chars().map(c -> c - '0').toArray());

            String word;
            int i = 0;

            if (digits.length == 0) {
                word = "Zero";
            } else {
                LinkedList<String> wordList = new LinkedList<>();
                for (int j = digits.length; j > 0; j -= 3) {
                    int high = j;
                    int low = j - 1;

                    if (high - 3 >= 0) {
                        low = j - 3;
                    } else if (high - 2 >= 0) {
                        low = j - 2;
                    } else if (high - 1 >= 0) {
                        low = j - 1;
                    }

                    wordList.addFirst(tripleToWords(Arrays.copyOfRange(digits, low, high), suffix[i++]));
                }

                word = String.join(" ", wordList);
            }

            result = minus ? "minus " + word.trim() : word.trim();
        }

        return result.trim().replaceAll(" +", " ");
    }

    public static void main(String[] args) {
        Console console = System.console();
        boolean stop;
        IntToWord n2w = new IntToWord();

        do {
            String number = console.readLine("Enter an integer: ");
            console.printf("\nYou entered %s.\n", n2w.toWords(number));

            String choice = console.readLine("Do you want to stop? [y|n]: ");
            stop = choice.compareToIgnoreCase("y") == 0;
        } while (!stop);
    }

}
