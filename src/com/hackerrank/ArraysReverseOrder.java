package com.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class ArraysReverseOrder {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("ArrayReverseOrder.txt"));
            int length = scanner.nextInt();

            Stack<Integer> integerStack = new Stack<>();
            for (int i = 0; i < length; i++) {
                integerStack.push(scanner.nextInt());
            }

            while (!integerStack.empty()) {
                System.out.println(integerStack.pop());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
