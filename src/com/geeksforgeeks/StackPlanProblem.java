package com.geeksforgeeks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/* Linear time O(n)
    Space O(n)
    Auxiliary space O(n)
* */
public class StackPlanProblem {
    private static final String INPUT = "7\n" +
            "100 80 60 70 60 75 85";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);

        int n = scanner.nextInt();
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = scanner.nextInt();
        }
        Deque<Integer> daysAlive = new ArrayDeque<>();

        int[] stockSpan = new int[n];

        daysAlive.addFirst(0);
        stockSpan[0] = 1;
        for (int i = 1; i < n; i++) {
            while (!daysAlive.isEmpty() && list[daysAlive.peekFirst()] <= list[i]) {
                daysAlive.removeFirst();
            }
            stockSpan[i] = daysAlive.isEmpty() ? (i + 1) : (i - daysAlive.peekFirst());

            daysAlive.addFirst(i);
        }

        for (int i = 0; i < stockSpan.length; i++) {
            System.out.print(stockSpan[i] + " ");
        }
    }
}
