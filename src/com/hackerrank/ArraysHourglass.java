package com.hackerrank;

import java.util.Scanner;

public class ArraysHourglass {

    public static void main(String[] args) {
        int[][] array = new int[6][6];
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner("1 1 1 0 0 0 0 1 0 0 0 0 1 1 1 0 0 0 0 0 2 4 4 0 0 0 0 2 0 0 0 0 1 2 4 0");
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                array[x][y] = scanner.nextInt();
            }
        }
        int maxSum = Integer.MIN_VALUE;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int currSum = 0;
                for (int innerY = 0; innerY < 3; innerY++) {
                    currSum += array[x][y + innerY]; // Top of hourglass
                    currSum += array[x + 2][y + innerY]; // Bottom of hourglass
                }
                currSum += array[x + 1][y + 1]; // Middle of hourglass

                if (currSum > maxSum)
                    maxSum = currSum;
            }
        }
        System.out.println(maxSum);
    }
}
