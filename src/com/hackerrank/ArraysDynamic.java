package com.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class ArraysDynamic {
    private static final String input =
            "2 5\n" +
                    "1 0 5\n" +
                    "1 1 7\n" +
                    "1 0 3\n" +
                    "2 1 0\n" +
                    "2 1 1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(input);

        int N = scanner.nextInt();
        int S = scanner.nextInt();

        int queries[][] = new int[S][3];
        for (int x = 0; x < S; x++) {
            for (int y = 0; y < 3; y++) {
                queries[x][y] = scanner.nextInt();
            }
        }

        int resultArray[][] = new int[N][0];
        int lastAnswer = 0;

        for (int q = 0; q < S; q++) {
            int x = queries[q][1];
            int y = queries[q][2];

            int idx = ((x ^ lastAnswer) % N);

            switch (queries[q][0]) {
                case 1: {
                    increaseArraySize(resultArray, idx);
                    resultArray[idx][resultArray[idx].length - 1] = y; // Place the value into the empty cell.
                    break;
                }
                case 2: {
                    lastAnswer = resultArray[idx][y % resultArray[idx].length];
                    System.out.println(lastAnswer);
                    break;
                }
                default: {
                    throw new RuntimeException("Unsupported query type.");
                }
            }
        }
    }

    private static void increaseArraySize(int[][] array, int index) {
        int newArray[] = Arrays.copyOf(array[index], array[index].length + 1);
        array[index] = newArray;
    }
}
