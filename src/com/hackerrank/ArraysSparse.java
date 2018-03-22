package com.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArraysSparse {
    private static final String INPUT = "4\n" +
            "aba\n" +
            "baba\n" +
            "aba\n" +
            "xzxb\n" +
            "3\n" +
            "aba\n" +
            "xzxb\n" +
            "ab";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);

        Map<String, Integer> list = new HashMap<>();

        int n = scanner.nextInt();
        scanner.nextLine(); // Skip new line.
        for (int i = 0; i < n; i++) {
            final String line = scanner.nextLine();
            final Integer prevValue = list.putIfAbsent(line, 1);
            if (prevValue != null) {
                list.put(line, prevValue + 1);
            }
        }

        int q = scanner.nextInt();
        scanner.nextLine(); // Skip new line.
        for (int i = 0; i < q; i++) {
            final String line = scanner.nextLine();
            final Integer cnt = list.get(line);
            System.out.println( (cnt != null) ? cnt : 0 );
        }
    }
}
