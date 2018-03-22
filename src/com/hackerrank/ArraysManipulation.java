package com.hackerrank;

import java.util.*;

public class ArraysManipulation {
    final static String INPUT1 = "5 4\n" +
            "1 2 10\n" +
            "2 5 20\n" +
            "3 4 30\n" +
            "4 5 100";
    final static String INPUT = "3 4\n" +
            "1 2 10\n" +
            "2 3 20\n" +
            "1 3 30\n" +
            "1 1 100";
    final static String INPUT2 = "4 3\n" +
            "2 3 603\n" +
            "1 1 286\n" +
            "4 4 882";
    final static String INPUT3 = "5 3\n" +
            "1 2 10\n" +
            "2 3 20\n" +
            "1 3 30\n" +
            "4 5 100";

    public static void main(String[] args) {
        simpleSolution();
        efficientSolution();
        efficientSolution2();
    }

    /* Example was taken from:
        https://codereview.stackexchange.com/questions/95755/algorithmic-crush-problem-hitting-timeout-errors/183567#183567

        Space O(1),
        Time O(m + n)
    */
    public static void efficientSolution() {
        Scanner in = new Scanner(INPUT3);
        int n = in.nextInt();
        int m = in.nextInt();

        long[] list = new long[n];
        long maxValue = 0;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();

            list[a - 1] += k;
            if (b < n) {
                list[b] -= k;
            }
        }
        long x = 0;

        // Compute prefix sum array value.
        for (int i = 0; i < n; i++) {
            x = x + list[i];
            maxValue = Math.max(maxValue, x);
        }

        System.out.println(maxValue);
    }

    // Space O(2m),
    // Time O(m + 2m + nlog(n)) -> O(m + mlog(m)) -> m*log(m)
    public static void efficientSolution2() {
        Scanner in = new Scanner(INPUT3);
        int n = in.nextInt();
        int m = in.nextInt();

        long maxValue = 0;
        Vector<AbstractMap.SimpleEntry<Integer, Integer>> vList = new Vector<>(m * 2);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();

            AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(a, k);
            AbstractMap.SimpleEntry<Integer, Integer> entry2 = new AbstractMap.SimpleEntry<>(b + 1, -k);
            vList.add(entry);
            vList.add(entry2);
        }

        vList.sort(Comparator.comparing(AbstractMap.SimpleEntry::getKey));
        long x = 0;
        for (int i = 0; i < m * 2; i++) {
            x = x + vList.get(i).getValue();
            maxValue = Math.max(maxValue, x);
        }
        System.out.println(maxValue);
    }

    public static void simpleSolution() {
        Scanner in = new Scanner(INPUT3);
        int n = in.nextInt();
        int m = in.nextInt();

        long[] list = new long[n];
        long maxValue = 0;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();

            long newMaxValue = updateList(a, b, k, list);
            if (newMaxValue > maxValue) {
                maxValue = newMaxValue;
            }
        }
        in.close();

        System.out.println(maxValue);
    }

    public static long updateList(int a, int b, int k, long[] list) {
        long maxValue = 0L;
        for (int i = a - 1; i < b; i++) {
            list[i] += k;

            if (list[i] > maxValue) {
                maxValue = list[i];
            }
        }
        return maxValue;
    }
}
