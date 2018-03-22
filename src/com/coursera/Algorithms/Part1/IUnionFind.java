package com.coursera.Algorithms.Part1;

public interface IUnionFind {
    boolean connected(int a, int b);

    void union(int a, int b);
}
