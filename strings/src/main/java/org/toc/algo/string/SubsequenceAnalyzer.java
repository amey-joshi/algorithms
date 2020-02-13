package org.toc.algo.string;

public class SubsequenceAnalyzer {
	private String initial;

	public SubsequenceAnalyzer(final String initial) {
		this.initial = initial;
	}

	public String inits() {
		return initial;
	}

	/**
	 * Count the number of subsequences of 'initial' that make up the 'goal'. If
	 * none are possible, return zero.
	 * 
	 * @param goal
	 * @return
	 */
	public int numSubsequences(final String goal) {
		int nss = -1; // number of sub-sequences

		if (isSubset(goal)) {
			nss = 0;
			int from = 0;
			int to = 1;
			for (int i = to; i <= goal.length(); ++i) {

				if (isSubSeqPossible(goal.substring(from, to))) {
					++to;
				} else {
					from = to - 1;
					--i;
					++nss;
				}
			}
			++nss;
		}

		return nss;
	}

	/**
	 * Can you generate 'goal' from the characters in 'initial'?
	 * 
	 * @param initial
	 * @param goal
	 * @return
	 */
	private boolean isSubset(String goal) {
		boolean possible = true;

		for (char g : goal.toCharArray()) {
			if (initial.indexOf(g) == -1) {
				possible = false;
				break;
			}
		}

		return possible;
	}

	/**
	 * Can a part of the 'goal' be a sub-sequence of 'initial'?
	 * 
	 * @param partOfGoal
	 * @return
	 */
	private boolean isSubSeqPossible(String partOfGoal) {
		boolean possible = true;
		int[] positions = new int[partOfGoal.length()];

		int from = 0;
		for (int i = 0; i < partOfGoal.length(); ++i) {
			int pos = initial.indexOf(partOfGoal.charAt(i), from);

			if (pos != -1) {
				positions[i] = pos;
				from = pos;
			} else {
				possible = false;
				break;
			}
		}

		return possible;
	}
}
