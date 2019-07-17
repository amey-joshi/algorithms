package org.toc.algo.kext;

import java.util.PriorityQueue;

public class KExtreme {
    private int k;

    public KExtreme(int k) {
        if (k < 1)
            throw new IllegalArgumentException(String.format("Illegal to set k = %d", k));
        this.k = k;
    }

    public int[] getLeastElements(int[] elements) {
        return getExtremeElements(elements, true);
    }

    public int[] getMostElements(int[] elements) {
        return getExtremeElements(elements, false);
    }

    private int[] getExtremeElements(int[] elements, boolean flipSign) {
        // A PriorityQueue gives highest priority to the highest integer.
        // We want to find least k elements. Therefore, we build a queue of
        // negative of the elements and get the maximum k among them. Before
        // returning the result, we flip the sign.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        int c = flipSign ? -1 : 1;
        boolean firstElement = true;

        for (int n : elements) {
            if (firstElement) {
                firstElement = false;
                priorityQueue.add(c * n);
            } else {
                if (priorityQueue.size() < k) {
                    priorityQueue.add(c * n);
                } else if (checkCurrentExtreme(flipSign, n, priorityQueue.peek())) {
                    priorityQueue.remove();
                    priorityQueue.add(c * n);
                }
            }
        }

        return priorityQueue.stream().mapToInt(n -> c * n.intValue()).toArray();
    }

    private boolean checkCurrentExtreme(boolean flipSign, int n, int currentExtreme) {
        if (flipSign) {
            return -n < -currentExtreme;
        } else {
            return n > currentExtreme;
        }
    }
}
