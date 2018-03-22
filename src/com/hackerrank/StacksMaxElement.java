package com.hackerrank;

import java.util.Scanner;
import java.util.Stack;

public class StacksMaxElement {
    public static final String INPUT = "10\n" +
            "1 97\n" +
            "2\n" +
            "1 20\n" +
            "2\n" +
            "1 26\n" +
            "1 20\n" +
            "2\n" +
            "3\n" +
            "1 91\n" +
            "3";

    /* Time complexity O(n)
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);
        int n = scanner.nextInt();
        Stack<Integer> list = new Stack<>();
        Stack<Integer> listMaxElement = new Stack<>();
        for (int i = 0; i < n; i++) {
            int action = scanner.nextInt();

            switch (action) {
                case 1: {
                    int nextInt = scanner.nextInt();
                    list.push(nextInt);

                    // Check and update the max element.
                    if (listMaxElement.empty() || nextInt >= listMaxElement.peek()) {
                        listMaxElement.push(nextInt);
                    }

                    break;
                }
                case 2: {
                    int deletedElem = list.pop();

                    // Check and delete max element.
                    if (!listMaxElement.empty() && deletedElem == listMaxElement.peek()) {
                        listMaxElement.pop();
                    }

                    break;
                }
                case 3: {
                    if (!listMaxElement.empty()) {
                        System.out.println(listMaxElement.peek());
                    } else {
                        throw new RuntimeException("Not a valid input sequence.");
                    }

                    break;
                }
                default: {
                    throw new RuntimeException("Not a valid command type occurred.");
                }
            }
        }
    }
}
