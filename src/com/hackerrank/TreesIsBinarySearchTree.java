package com.hackerrank;

/**
 * HackerRank Trees: IsBinarySearchTree
 * </br>
 * Constraints: 0 <= data <= 10^4
 */
public class TreesIsBinarySearchTree {
    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node rootNonBST;
    private Node rootNonBST2;
    private Node rootBST;

    private TreesIsBinarySearchTree() {
        rootNonBST = new Node(3);
        rootNonBST.left = new Node(5);
        rootNonBST.right = new Node(2);
        rootNonBST.left.left = new Node(1);
        rootNonBST.left.right = new Node(4);
        rootNonBST.right.left = new Node(6);

        rootBST = new Node(7);
        rootBST.left = new Node(4);
        rootBST.left.left = new Node(3);
        rootBST.left.right = new Node(5);
        rootBST.right = new Node(10);
        rootBST.right.left = new Node(8);
        rootBST.right.right = new Node(11);

        rootNonBST2 = new Node(7);
        rootNonBST2.left = new Node(4);
        rootNonBST2.left.left = new Node(3);
        rootNonBST2.left.right = new Node(8);
        rootNonBST2.right = new Node(10);
        rootNonBST2.right.left = new Node(5);
        rootNonBST2.right.right = new Node(11);
    }

    public static void main(String[] args) {
        Node rootNonBST = new TreesIsBinarySearchTree().rootNonBST;
        Node rootBST = new TreesIsBinarySearchTree().rootBST;
        Node rootNonBST2 = new TreesIsBinarySearchTree().rootNonBST2;

        boolean res = checkTree(rootNonBST2, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(res);
    }

    private static boolean checkTree(Node root, int MIN, int MAX) {
        if (root == null) {
            return true;
        }
        if (root.data <= MIN || root.data >= MAX) {
            return false;
        }

        boolean res;
        res = checkTree(root.left, MIN, root.data);
        res &= checkTree(root.right, root.data, MAX);

        return res;
    }

    /**
     * Nice solution without recursion.
     */
    /*
    boolean checkBST(Node root) {
        Deque<Node> stack = new LinkedList<>();
        int prev = -1;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.data <= prev) {
                    return false;
                } else {
                    prev = root.data;
                }
                root = root.right;
            }
        }
        return true;
    }*/
}