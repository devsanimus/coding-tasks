package com.coursera.Algorithms.Part1;

/*
    Defects:
    - trees can get tall.
    - find is too expensive(up to N array accesses).
* */
public class UFQuickUnion implements IUnionFind {
    private int[] list;

    /* N array accesses.
    * */
    public UFQuickUnion(int size) {
        list = new int[size];

        for (int i = 0; i < size; i++) {
            list[i] = i;
        }
    }

    /* Visit all nodes until reached the root.
    Depth of 'a' array accesses.
    * */
    private int root(int a) {
        int next = a;
        while (next != list[next]) {
            next = list[next];
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
        list[aRoot] = bRoot;
    }
}
