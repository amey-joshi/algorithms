package org.toc.algo.numbers;

public class Pairs {
	/**
	 * Euclid's algorithm for finding highest common factor (HCF). It is also called
	 * the greatest common divisor (GCD).
	 * 
	 * @param a
	 * @param b
	 * 
	 * @return HCF of a and b.
	 */
	public static int hcf(int a, int b) {
		if (b == 0) {
			return 0;
		} else {
			return hcf(b, a % b);
		}
	}

	/**
	 * Least common multiple (LCM) of two integers.
	 * 
	 * @param a
	 * @param b
	 * 
	 * @return LCM of a and b.
	 */
	public static int lcm(int a, int b) {
		return a * b / hcf(a, b);
	}
}
