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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Structure {

    /**
     * Returns true if a positive integer is a palindrome, false otherwise.
     *
     * @param number
     *
     * @return true or false.
     */
    public static boolean isPalindrome(String number) {
        boolean result = false;
        final int L = number.length();

        if (L % 2 == 1) {
            result = IntStream.range(0, L / 2).noneMatch(n -> number.charAt(n) != number.charAt(L - 1 - n));
        }

        return result;
    }

    /**
     * Sum of digits is a positive integer.
     *
     * @param number
     * @return
     */
    public static int sumOfDigits(String number) {
        if (Utils.isNonnegativeInt(number)) {
            String msg = String.format("Could not interpret %s as a non-negative integer.", number);
            throw new IllegalArgumentException(msg);
        }

        return number.chars().map(c -> c - '0').reduce(0, Integer::sum);
    }

    /**
     * Returns a list of all prime factors of a positive integer.
     *
     * @param n
     * @return
     */
    public static List<Integer> allPrimeFactors(int n) {
        if (n <= 0) {
            String msg = String.format("Cannot find factors of a non-positive integer %d.", n);
            throw new IllegalArgumentException(msg);
        }

        List<Integer> factors = new ArrayList<>();

        int p = 2;

        while (n >= p * p) {
            if (n % p == 0) {
                factors.add(p);
                n /= p;
            } else {
                p++;
            }
        }

        factors.add(n);

        return factors;
    }

    /**
     * Return a list of all factors of a positive integer. Note that these are
     * not just the prime factors.
     *
     * @param n
     *
     * @return {@link List} of all factors.
     */
    public static List<Integer> allFactors(int n) {
        if (n <= 0) {
            String msg = String.format("Cannot find factors of a non-positive integer %d.", n);
            throw new IllegalArgumentException(msg);
        }

        List<Integer> factors = new ArrayList<>();

        factors.add(1); // One is a factor of every integer.
        IntStream.range(2, n / 2 + 1).filter(i -> n % i == 0).forEach(i -> factors.add(i));
        if (n > 1) {
            factors.add(n); // Every integer is a factor of itself.
        }

        return factors;
    }

    /**
     * Sum of factors of a positive integer. If the argument is not positive the
     * method returns 0.
     *
     * @param n
     * @return
     */
    public static int sumOfFactors(int n) {
        if (n <= 0) {
            String msg = String.format("Cannot find factors of a non-positive integer %d.", n);
            throw new IllegalArgumentException(msg);
        }

        return allFactors(n).stream().reduce(0, Integer::sum) - n;
    }

    /**
     * Checks whether a positive integer is a perfect number.
     *
     * @param n
     * @return
     */
    public static boolean isPerfect(int n) {
        if (n <= 0) {
            String msg = String.format("Cannot check if a non-positive integer %d is perfect.", n);
            throw new IllegalArgumentException(msg);
        }

        return sumOfFactors(n) == n;
    }

    public static void allTests(String number) {
        if (isPalindrome(number)) {
            System.out.println("\nYou entered a palindrome.");
        } else {
            System.out.println("\nThe number is not a palindrome.");
        }

        System.out.println("Sum of the digits is " + sumOfDigits(number));

        int n = Integer.parseInt(number);
        List<Integer> factors = allFactors(n);

        System.out.println("Its factors are: " + Arrays.toString(factors.toArray()));
        System.out.println("Number of divisors: " + (factors.size() - 1));

        System.out.println("Sum of its factors: " + sumOfFactors(n));

        if (isPerfect(n)) {
            System.out.println(String.format("%d is a perfect number.", n));
        } else {
            System.out.println(String.format("%d is not a perfect number.", n));
        }

        System.out.println("Prime factors:" + Arrays.toString(allPrimeFactors(n).toArray()));
    }

    public static void main(String[] args) {
        boolean stop;
        Console console = System.console();

        do {
            String number = console.readLine("Enter a positive integer: ");

            if (Utils.isNonnegativeInt(number)) {
                allTests(number);
            } else {
                System.out.println("You didn't enter a positive integer");
            }

            String choice = console.readLine("Do you want to stop? [y|n]?");
            stop = (choice.compareToIgnoreCase("y") == 0);
        } while (!stop);
    }
}
