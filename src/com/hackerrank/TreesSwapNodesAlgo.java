package com.hackerrank;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * HackerRank task "Swap Nodes [Algo]."
 *
 * @author
 */
public class TreesSwapNodesAlgo {

    private static String INPUT00 = "3\n" +
            "2 3\n" +
            "-1 -1\n" +
            "-1 -1\n" +
            "2\n" +
            "1\n" +
            "1";
    private static String INPUT01 = "5\n" +
            "2 3\n" +
            "-1 4\n" +
            "-1 5\n" +
            "-1 -1\n" +
            "-1 -1\n" +
            "1\n" +
            "2";
    private static String INPUT02 = "11\n" +
            "2 3\n" +
            "4 -1\n" +
            "5 -1\n" +
            "6 -1\n" +
            "7 8\n" +
            "-1 9\n" +
            "-1 -1\n" +
            "10 11\n" +
            "-1 -1\n" +
            "-1 -1\n" +
            "-1 -1\n" +
            "2\n" +
            "2\n" +
            "4";

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT02);

        int N = scanner.nextInt();
        Node root = new Node(1);
        Deque<Node> tree = new LinkedList<>();
        tree.add(root);
        // Read out the nodes first.
        for (int i = 0; i < N; i++) {
            int leftVal = scanner.nextInt();
            int rightVal = scanner.nextInt();
            Node cur = tree.remove();
            if (leftVal != -1) {
                cur.left = new Node(leftVal);
                tree.add(cur.left);
            }
            if (rightVal != -1) {
                cur.right = new Node(rightVal);
                tree.add(cur.right);
            }
        }

        int T = scanner.nextInt();
        // Swap nodes for the given heights.
        for (int i = 0; i < T; i++) {
            swapNodes(root, scanner.nextInt(), 1);
            printTreeInOrder(root);
            System.out.println();
        }
    }

    private static void swapNodes(Node root, int k, int height) {
        if (root != null) {
            if (height % k == 0 || k == 1) {
                Node tmp = root.left;
                root.left = root.right;
                root.right = tmp;
            }
            swapNodes(root.left, k, height + 1);
            swapNodes(root.right, k, height + 1);
        }
    }

    private static void printTreeInOrder(Node root) {
        if (root != null) {
            printTreeInOrder(root.left);
            System.out.print(root.data + " ");
            printTreeInOrder(root.right);
        }
    }
}
