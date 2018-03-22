package com.hackerrank;

public class TreesBinarySearchTreeInsertion {
    private Node root = new Node();

    private TreesBinarySearchTreeInsertion() {
        root.data = 4;
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);

    }

    public class Node {
        int data;
        Node left;
        Node right;

        Node(int value) {
            this.data = value;
        }

        Node() {
        }
    }

    public static void main(String[] args) {
        TreesBinarySearchTreeInsertion tree = new TreesBinarySearchTreeInsertion();
        Insert(tree.root, 6);
        System.out.println(tree.root.data);
    }

    private static Node Insert(Node root, int value) {
        if (root != null) {
            if (root.data < value) {
                if (root.right != null) {
                    Insert(root.right, value);
                } else {
                    root.right = Insert(null, value);
                }
            } else {
                if (root.left != null) {
                    Insert(root.left, value);
                } else {
                    root.left = Insert(null, value);
                }
            }
            return root;
        } else {
            TreesBinarySearchTreeInsertion tree = new TreesBinarySearchTreeInsertion();
            return tree.new Node(value);
        }
    }
}
