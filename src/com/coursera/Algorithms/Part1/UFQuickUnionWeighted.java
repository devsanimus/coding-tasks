package com.coursera.Algorithms.Part1;

import java.util.Arrays;

/*
    Improvements:
       - trees are weighted: adding sub-trees is more efficient.
       - root search traversal is halved by two.
* */
public class UFQuickUnionWeighted implements IUnionFind {
    private int[] list;
    private int[] weight;

    /* N array accesses.
    * */
    public UFQuickUnionWeighted(int size) {
        list = new int[size];
        weight = new int[size];

        for (int i = 0; i < size; i++) {
            list[i] = i;
        }

        Arrays.fill(weight, 1);
    }

    /* Visit all nodes until reached the root.
    Depth of 'a' array accesses.
    * */
    private int root(int a) {
        int next = a;
        while (next != list[next]) {
            next = list[list[next]];
        }
        return next;
    }

    /* Check if two nodes are connected.
    Accesses: depth of both array accesses.
    * */
    public boolean connected(int a, int b) {
        return (root(a) == root(b));
    }

    /* Union two different trees.
    Accesses: Depth of both nodes.
     * */
    public void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);

        if (aRoot == bRoot)
            return;

        if (weight[bRoot] >= weight[aRoot]) {
            list[aRoot] = bRoot;
            weight[bRoot] += weight[aRoot];
        } else {
            list[bRoot] = aRoot;
            weight[aRoot] += weight[bRoot];
        }
        printAll();
    }

    private void printAll() {
        for (int aList : list) {
            System.out.print(aList + " ");
        }
        System.out.println();
    }
}
