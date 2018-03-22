package com.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class StacksAndXorOr {
    private static final String INPUT = "8\n" +
            "1 3 6 7 2 20 6 5 ";

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(INPUT);
//        Scanner scanner = new Scanner(new File("input16.txt")); // Exp. result: 262142
        Scanner scanner = new Scanner(new File("input17.txt")); // Exp. result: 262143
        int N = scanner.nextInt();
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = scanner.nextInt();
        }
        long maxResult = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i < list.length; i++) {
            while (!stack.isEmpty() && stack.peekFirst() > list[i]) {
                int deletedElem = stack.removeFirst();
                int result = deletedElem ^ list[i];
                maxResult = Math.max(maxResult, result);
            }
            // Calculate the stack's top element with the current value.
            if (!stack.isEmpty()) {
                maxResult = Math.max(maxResult, stack.peekFirst() ^ list[i]);
            }
            stack.addFirst(list[i]);
        }
        System.out.println(maxResult);
    }
}
