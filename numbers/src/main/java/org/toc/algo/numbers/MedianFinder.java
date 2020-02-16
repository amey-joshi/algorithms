package org.toc.algo.numbers;

import java.util.ArrayList;
import java.util.List;

public class MedianFinder {
    private List<Integer> data;

    public MedianFinder() {
        data = new ArrayList<>();
    }

    public void addNum(int num) {
        data.add(findPosition(num), num);
    }

    public double findMedian() {
        double m = 0;
        int len = data.size();

        if (len == 0) {
            m = 0;
        } else if (len % 2 == 0) {
            int x = data.get(len / 2);
            int y = data.get(len / 2 - 1);
            m = (x + y) / 2.0;
        } else {
            m = data.get((len - 1) / 2);
        }

        return m;
    }

    int findPosition(int num) {
        int p = 0;

        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i) < num) {
                ++p;
            }
        }

        return p;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
//        System.out.println("Median = " + mf.findMedian());
        mf.addNum(6);
        System.out.println("Median = " + mf.findMedian());
        mf.addNum(10);
        System.out.println("Median = " + mf.findMedian());
        mf.addNum(2);
        System.out.println("Median = " + mf.findMedian());
        mf.addNum(6);
        System.out.println("Median = " + mf.findMedian());
        mf.addNum(5);
        System.out.println("Median = " + mf.findMedian());
        mf.addNum(0);
        System.out.println("Median = " + mf.findMedian());

    }
}
