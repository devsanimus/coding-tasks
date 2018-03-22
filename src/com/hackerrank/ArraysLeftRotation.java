package com.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArraysLeftRotation {
    static int[] leftRotation(int[] a, int d) {
        int[] resultArray = new int[a.length];

        int newIndex = d % a.length; // If we have cycled rotations, get rid of them.

        System.arraycopy(a, newIndex, resultArray, 0, a.length - newIndex);
        System.arraycopy(a, 0, resultArray, a.length - newIndex, newIndex);

        return resultArray;
    }

    /* Time O(n), Space O(n)
    * */
    static int[] leftRotationSwap(int[] a, int d) {
        if (d % a.length == 0)
            return a;

        // Clean up rotation count.
        d %= a.length;

        // Placeholder to temporarily save the extracted element.
        int pocket = a[0];
        // Shift order.
        int shift = a.length - d;
        // Current index to extract element from.
        int idx = 0;
        // Some numbers in combination with the specific orders will make the endlees loops, covering only certain elements.
        // Manually shift the index to continue swapping elements properly.
        int idxShift = 0;

        for (int i = 0; i < a.length; i++) {
            // Temporarily extract the element.
            int tmp = a[(idx + shift) % a.length];
            // Place an element from the pocket.
            a[(idx + shift) % a.length] = pocket;

            // Shift index by the order.
            idx = (idx + shift) % a.length;

            // Check if we need to manually shift the index.
            // If start index equals to the end index that means we have a loop and the index needs to be shifted.
            // It happens whe the given order represents a common divider of a number of elements and an order.
            // For example: order of 4 for an array of 12 elements has a common divider equal to 2.
            if (idx % a.length == idxShift) {
                //idxShift += 1;
                idx = ++idxShift;
                // Reassign the pocket value.
                pocket = a[idx];
            } else {
                pocket = tmp;
            }
        }

        return a;
    }


    private static int[] leftRotateReverse(int[] a, int d) {
        if (d % a.length == 0)
            return a;

        d = d % a.length;

        // Reverse all elements.
        reverseElements(a, 0, a.length);
        // Reverse first part.
        reverseElements(a, 0, a.length - d);
        // Reverse second part.
        reverseElements(a, a.length - d, a.length);

        return a;
    }

    private static void reverseElements(int[] a, int from, int to) {
        int length = to + from;
        for (int i = from; i < length / 2; i++) {
            int tmp = a[i];
            a[i] = a[length - i - 1];
            a[length - i - 1] = tmp;

            // Seems to be longer because of XOR operation.
            /*a[i] ^= a[length - i - 1];
            a[length - i - 1] ^= a[i];
            a[i] ^= a[length - i - 1];*/
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("ArraysLeftRotation.input"));
//        Scanner in = new Scanner
//                (
//                "20 10\n" +
//                        "41 73 89 7 10 1 59 58 84 77 77 97 58 1 86 58 26 10 86 51"
//        );
        /*Scanner in = new Scanner(
                "8 4\n" +
                        "1 2 3 4 5 6 7 8 9 10 11"
        );*/


        int n = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }

        int result[];
        long start = System.currentTimeMillis();

        // Solution 1. Java system copy. Space complexity O(2n), Speed O(n)
        // 0-4 sec.
        //result = leftRotation(a, d);

        // Solution 2. Filter input. Not an actual solution.
        int[] result2 = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            //result2[(i + (n - d)) % 5] = a[i]; // Exception on // 4 2 input first line.
        }

        // Solution 3. Swapping elements. Space O(1), Time O(n)
        // 8-12 ms
        //result = leftRotationSwap(a, d);

        // Solution 4. Reversing array. Space O(1), Time O(2N)
        // 4-8 ms. Mostly 4 without XOR operation to copy objects.
        result = leftRotateReverse(a, d);

        // Solution 5.
        // Space O(n). Time O(n)
        /*
          int[]b = new int[a.length];
         for (int i = 0; i < numIntegers; i++)
            {
            index = i - rotateNum;
            if (index < 0)
                {
                index = numIntegers + index;
            }
            b[index] = a[i];
        }
        * */

        long totalSec = (System.currentTimeMillis() - start);
        System.out.printf("Time: %d msec(s).", totalSec);
        System.out.println();

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");

        in.close();
    }
}
