package com.general.random_practice;

import java.util.Scanner;

public class BinarySearch_20180620 {
    public static final String INPUT = "12\n" +
            "0 1 3 4 7 8 10 15 20 21 23 42";

    private static int binarySearch(int[] arr, int x) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (x < arr[mid]) {
                hi = mid - 1;
            } else if (x > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);
        int sz = scanner.nextInt();
        int[] arr = new int[sz];
        for (int i = 0; i < sz; i++) {
            arr[i] = scanner.nextInt();
        }
        int x = 1;
        System.out.println(String.format("Search for: %d", x));
        int res = binarySearch(arr, x);
        System.out.println(String.format("Found at index: %d", res));
    }
}
