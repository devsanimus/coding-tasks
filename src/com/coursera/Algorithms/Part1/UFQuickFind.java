package com.coursera.Algorithms.Part1;

/*
Quadratic algorithm.
O(N^2)
<p>
Quadratic algorithms do not scale with technology.

Defects:
- union is too expensive.
- trees are flat but too expensive to keep them that way.
*/
public class UFQuickFind implements IUnionFind {

    /* Data structure is backed by a simple array.
    * */
    private int[] list;

    /* Initialize Union Find data structure.
    N array accesses.
    * */
    public UFQuickFind(int size) {
        list = new int[size];

        // Set default values of the data structure.
        for (int i = 0; i < size; i++) {
            list[i] = i;
        }
    }

    /* Checks whether two nodes are connected.
    2 array accesses.
    * */
    public boolean connected(int a, int b) {
        return list[a] == list[b];
    }

    /* Adds connection between two nodes.
    At most: 2N + 2 array access.
    * */
    public void union(int a, int b) {
        int aVal = list[a]; // 1 access.
        int bVal = list[b]; // 1 access.
        for (int i = 0; i < list.length; i++) {
            if (list[i] == bVal) { // N array acceses.
                list[i] = aVal; // N accesses.
            }
        }
    }

}
