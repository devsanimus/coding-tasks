package com.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StacksGameOfTwoStacks2 {
    public static final String INPUT3 = "1\n" +
            "5 4 10\n" +
            "4 2 4 6 1\n" +
            "2 1 8 5";
    public static final String INPUT = "1\n" +
            "10 10 601\n" +
            "200 100 1 1 1 1 1 1 1 1\n" +
            "100 100 100 100 1 1 1 1 1 100";

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner sc = new Scanner(INPUT3);
        Scanner sc = new Scanner(new File("StacksGameOfTwoStacks.input"));
        //Scanner sc = new Scanner(new File("StacksTwoGames2.input"));

        long tStart = System.currentTimeMillis();

        int totalGames = sc.nextInt();
        for (int g = 0; g < totalGames; g++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int x = sc.nextInt();
            int[] a = readArray(sc, n);
            int[] b = readArray(sc, m);

            System.out.println(solveMulti(a, b, x));
            //System.out.println(solve(a, b, x));
        }
        long tEnd = System.currentTimeMillis() - tStart;
        System.out.printf("Total time: %.02f%n", ((float) tEnd / 1000L));
    }

    static int[] readArray(Scanner sc, int size) {
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = sc.nextInt();
        }
        return result;
    }

    /*
       Space O(n)
       Time O(nlogn)

       Has errors.
    */
    static int solveMulti(int[] a, int[] b, int x) {
        int maxSum = 0;
        int bLen = 0, aLen = 0;
        int bb[] = new int[b.length - 1];
        while (bLen < b.length && maxSum + b[bLen] <= x) {
            maxSum += b[bLen];
            if (bLen > 0) {
                bb[bLen - 1] = maxSum; // Store prefix sum.
            }
            bLen++;
        }

        int maxCount = 0;
        while (aLen < a.length) {
            maxSum += a[aLen];

            if (maxSum > x && bLen > 0) {
                // Find the highest possible value.
                // maxSum - x - is the difference we want to remove from array b.
                // We can do that using the prefix-sum array of b-values.
                int bbLen = bLen - 1; // Array of sums is one less then the original array.
                int idx = upperBound(bb, bbLen, maxSum - x);
                // Check if we found needed element inside the array.
                if (idx < bLen && idx >= 0) {
                    maxSum -= (bb[bbLen - 1] - bb[idx]);
                    bLen -= (bbLen - 1 - idx);
                }
            }

            if (maxSum > x)
                break;

            aLen++;
            maxCount = Math.max(maxCount, aLen + bLen);
        }

        return maxCount;
    }

    /**
     * Finds the upper bound value of the given array of sums.
     *
     * @param x    The highest value to look in the array.
     * @param len  length of the given array.
     * @param sums Input array to process.
     * @return -1 if value not found. </br>
     * If value was found returns the index of the array containing it.
     */
    private static int upperBound(int sums[], int len, int x) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int midSum = sums[len - 1] - sums[mid];
            if (midSum >= x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    /* Space O(1)
        Time O(n)
    */
    static int solve(int[] a, int[] b, int x) {
        int maxSum = 0;
        int bLen = 0;
        while (bLen < b.length && maxSum + b[bLen] <= x) {
            maxSum += b[bLen];
            bLen++;
        }

        int maxCount = bLen, aLen = 0;
        while (aLen < a.length) {
            maxSum += a[aLen];
            while (maxSum > x && bLen > 0) {
                // Shift second array.
                bLen--;
                maxSum -= b[bLen];
            }
            if (maxSum > x) {
                break;
            }

            aLen++;
            maxCount = Math.max(maxCount, aLen + bLen);
        }

        return maxCount;
    }
}