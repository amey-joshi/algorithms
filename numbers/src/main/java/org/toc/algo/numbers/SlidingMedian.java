package org.toc.algo.numbers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SlidingMedian {
    private List<Integer> data;
    int left;

    int findPosition(int num) {
        int p = 0;

        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i) < num) {
                ++p;
            }
        }

        return p;
    }

    private double getMedian(int[] window, boolean first) {
        double m = 0.0;

        if (first) {
            for (int n : window) {
                data.add(findPosition(n), n);
            }
        } else {
            data.remove(data.indexOf(left));
            int right = window[window.length - 1];
            data.add(findPosition(right), right);
        }

        int len = window.length;
        if (len % 2 == 0) {
            double x = data.get(len / 2) * 1.0;
            double y = data.get(len / 2 - 1) * 1.0;
            m = (x + y) / 2.0;
        } else {
            m = data.get((len - 1) / 2);
        }

        left = window[0];

        return m;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] medians = new double[nums.length - k + 1];
        data = new ArrayList<>(nums.length);

        boolean first = true;
        for (int i = 0; i < medians.length; ++i) {
            medians[i] = getMedian(Arrays.copyOfRange(nums, i, i + k), first);
            first = false;
        }

        return medians;
    }

    public static void main(String[] args) {
        SlidingMedian sm = new SlidingMedian();
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        double[] medians = sm.medianSlidingWindow(nums, 3);

        for (double m : medians) {
            System.out.print(m + " ");
        }
        System.out.println();

        int[] nums1 = { 23, 23 };
        medians = sm.medianSlidingWindow(nums1, 2);

        for (double m : medians) {
            System.out.print(m + " ");
        }
        System.out.println();

        int[] nums3 = null;
        try {
            nums3 = sm.largeSample();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Processing begins...");
        long start = System.currentTimeMillis();
        medians = sm.medianSlidingWindow(nums3, 3406);
        long end = System.currentTimeMillis();
        double elapsed = (end - start) / 1000;
        System.out.println("Took " + elapsed + " seconds.");
    }

    private int[] largeSample() throws IOException, IOException {
        final String filename = "/home/amey/code/Java/algorithms/numbers/large.sample";
        int nums[] = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            ArrayList<Integer> L = new ArrayList<>();
            while (st.hasMoreTokens()) {
                L.add(Integer.parseInt(st.nextToken()));
            }

            nums = new int[L.size()];
            for (int i = 0; i < L.size(); ++i) {
                nums[i] = L.get(i);
            }
        }
        return nums;
    }
}
