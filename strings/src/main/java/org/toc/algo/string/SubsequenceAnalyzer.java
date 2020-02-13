package org.toc.algo.string;

import java.util.LinkedList;
import java.util.List;

public class SubsequenceAnalyzer {
    /**
     * The string from which we form sub-sequences.
     */
    private String initial;

    /**
     * The only constructor.
     * 
     * @param initial
     */
    public SubsequenceAnalyzer(final String initial) {
        this.initial = initial;
    }

    /**
     * Get the 'initial' string.
     * 
     * @return
     */
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
        return getAllSubsequences(goal).size();
    }

    /**
     * Get a list of all subsequences of 'initial' that make up the 'goal'.
     * 
     * @param goal
     * @return
     */
    public List<String> getAllSubsequences(final String goal) {
        List<String> list = new LinkedList<>();

        if (isSubset(goal)) {
            int from = 0;
            int to = 1;
            for (int i = to; i <= goal.length(); ++i) {
                if (isSubSeqPossible(goal.substring(from, to))) {
                    ++to;
                } else {
                    list.add(goal.substring(from, to - 1));
                    from = to - 1;
                    --i;
                }
            }
            list.add(goal.substring(from));
        }

        return list;
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

        for (char ch : goal.toCharArray()) {
            if (initial.indexOf(ch) == -1) {
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
