package com.coursera.Algorithms.Part1;

import java.util.Scanner;

public class UnionFind {
    private static final String INPUT = "10\n" +
            "4 3\n" +
            "3 8\n" +
            "6 5\n" +
            "9 4\n" +
            "2 1\n" +
            "8 9\n" +
            "5 0\n" +
            "7 2\n" +
            "6 1\n" +
            "1 0\n" +
            "6 7";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);

        int N = scanner.nextInt();
//        IUnionFind quickFind = new UFQuickFind(N);
//        IUnionFind quickFind = new UFQuickUnion(N);
        IUnionFind quickFind = new UFQuickUnionWeighted(N);

        for (int i = 0; i < N; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (!quickFind.connected(a, b)) {
                quickFind.union(a, b);
            }
        }
    }
}
