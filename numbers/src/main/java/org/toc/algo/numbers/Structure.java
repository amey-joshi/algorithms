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

		if (number.length() % 2 == 1) {
			char[] digits = number.toCharArray();
			int i = 0;
			int j = number.length() - 1;
			for (i = 0; i < number.length() / 2; i++) {
				if (digits[i] != digits[j--]) {
					break;
				}
			}

			result = (i == number.length() / 2);
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
		int sum = 0;

		if (Utils.isNonnegativeInt(number)) {
			sum = number.chars().map(c -> c - '0').reduce(0, Integer::sum);
		}

		return sum;
	}

	/**
	 * Returns a list of all prime factors of a positive integer.
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> allPrimeFactors(int n) {
		List<Integer> factors = new ArrayList<>();

		if (n > 0) {
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
		}

		return factors;
	}

	/**
	 * Return a list of all factors of a positive integer. Note that these are not
	 * just the prime factors.
	 * 
	 * @param n
	 * 
	 * @return {@link List} of all factors.
	 */
	public static List<Integer> allFactors(int n) {
		List<Integer> factors = new ArrayList<>();

		if (n > 0) {
			factors.add(1); // One is a factor of every integer.

			int limit = n / 2;

			for (int i = 2; i <= limit; ++i) {
				if (n % i == 0) {
					factors.add(i);
				}
			}

			if (n > 1) {
				factors.add(n); // Every integer is a factor of itself.
			}
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
		int sum = 0;

		if (n > 0) {
			sum = allFactors(n).stream().reduce(0, Integer::sum) - n;
		}

		return sum;
	}

	/**
	 * Checks whether a positive integer is a perfect number.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPerfect(int n) {
		boolean result = false;

		if (n > 0) {
			result = sumOfFactors(n) == n;
		}

		return result;
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
		boolean stop = false;
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
