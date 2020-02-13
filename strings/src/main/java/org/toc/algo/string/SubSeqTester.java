package org.toc.algo.string;

public class SubSeqTester {

    public static void main(String[] args) {
        SubsequenceAnalyzer ssa = new SubsequenceAnalyzer("xyz");
        String[] examples = { "xyt", "xyzxy", "xyzxzyx" };

        for (String s : examples) {
            System.out.println(String.format("%s has %d sub-sequences of %s", s, ssa.numSubsequences(s), ssa.inits()));
        }
    }
}
