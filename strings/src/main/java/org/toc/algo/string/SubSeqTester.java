package org.toc.algo.string;

import java.util.List;

public class SubSeqTester {

    public static void main(String[] args) {
        SubsequenceAnalyzer ssa = new SubsequenceAnalyzer("xyz");
        String[] examples = { "xyt", "xyzxy", "xyzxzyx" };

        for (String s : examples) {
            System.out.println(String.format("Subsequences of %s from characters in %s:", s, ssa.inits()));
            List<String> list = ssa.getAllSubsequences(s);
            list.forEach(ss -> System.out.print(ss + ", "));
            System.out.println();
        }
    }
}
