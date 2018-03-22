package com.hackerrank;

public class TreesLowestCommonAncestor {
    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    private TreesLowestCommonAncestor() {
        this.root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
    }

    public static void main(String[] args) {
        Node root = new TreesLowestCommonAncestor().root;
        Node lowestCommonAncestor = lowestCommonAncestor(root, 7, 3);
        Node lowestCommonAncestorBST = lowestCommonAncestorBST(root, 7, 3);
        Node lowestCommonAncestorNoRecursionBST = lowestCommonAncestorNoRecursionBST(root, 7, 3);

        System.out.println(lowestCommonAncestor.data);
        System.out.println(lowestCommonAncestorBST.data);
        System.out.println(lowestCommonAncestorNoRecursionBST.data);
    }

    /**
     * Searching for a CLA in a Binary Tree.
     */
    private static Node lowestCommonAncestor(Node root, int a, int b) {
        if (root == null) {
            return null;
        }
        if (root.data == a || root.data == b) {
            return root;
        }
        Node lNode = lowestCommonAncestor(root.left, a, b);
        Node rNode = lowestCommonAncestor(root.right, a, b);
        // Case when LCM was found.
        if (lNode != null && rNode != null) {
            return root;
        } else { // Keep searching for LCA.
            return lNode != null ? lNode : rNode;
        }
    }

    /**
     * Searching for CLA in a BST.
     * Note: BST should always have both nodes.
     */
    private static Node lowestCommonAncestorBST(Node root, int a, int b) {
        if (root.data < a && root.data < b) {
            // Explore right subtree.
            lowestCommonAncestorBST(root.right, a, b);
        } else if (root.data > a && root.data > b) {
            // Explore left subtree.
            lowestCommonAncestorBST(root.left, a, b);
        }
        // Else we found either first element or second or even both - which is LCA.
        return root;
    }

    /**
     * Searching for a CLA in a BST without recursion.
     * Node: BST should always have both values.
     */
    private static Node lowestCommonAncestorNoRecursionBST(Node root, int a, int b) {
        Node parent = root;
        while (true) {
            if (parent.data < a && parent.data < b) {
                parent = parent.right;
            } else if (parent.data > a && parent.data > b) {
                parent = parent.left;
            }
            // Found a common ancestor.
            return parent;
        }
    }
}
