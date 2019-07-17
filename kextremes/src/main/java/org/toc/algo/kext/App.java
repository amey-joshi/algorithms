package org.toc.algo.kext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		KExtreme kmin = new KExtreme(5);
		int[] elements = {16, 21, 43, 40, 15, 6, 75, 81, 9, 10}; 
		show(kmin.getLeastElements(elements));
		show(kmin.getMostElements(elements));
	}
	
	private static void show(int[] extremes) {
		String s = Arrays.stream(extremes).mapToObj(String::valueOf).collect(Collectors.joining(","));
		System.err.println(s);
	}
}
