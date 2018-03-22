package com.hackerrank;

/**
 * HackerRank "Tree: Huffman Decoding"
 * Recursive solution.
 *
 * @author
 */
public class TreesHaufmanCodes {
    class Node {
        int freq;
        Node left;
        Node right;
        Character data;

        Node(Character input) {
            data = input;
        }

        Node(Character input, int freq) {
            this.data = input;
            this.freq = freq;
        }
    }

    private Node root;

    private TreesHaufmanCodes() {
        root = new Node('\0', 5);
        root.left = new Node('\0', 2);
        root.left.left = new Node('B', 1);
        root.left.right = new Node('C', 1);
        root.right = new Node('A', 3);
    }

    public static void main(String[] args) {
        TreesHaufmanCodes tree = new TreesHaufmanCodes();
        String res = decode("101101100", tree.root, tree.root);
        System.out.println(res);
    }

    private static String decode(String sb, Node parent, Node root) {
        if (parent == null) {
            return "\0";
        }
        if (sb.isEmpty()) {
            return String.valueOf(parent.data);
        }

        Character curVal = sb.charAt(0);
        String res;
        // Visit left child.
        if (curVal.equals('0')) {
            res = decode(sb.substring(1), parent.left, root);
        }
        // Visit right child.
        else {
            res = decode(sb.substring(1), parent.right, root);
        }

        if (res.equalsIgnoreCase("\0")) {
            return String.valueOf(parent.data) + decode(sb, root, root);
        } else {
            return res;
        }
    }

    /** Non-recursive solution.
     *  1. Iterate over an input string and for each taken char shift current node to left or right.
     *  2. Repeat shifting until node with no leafs is reached - output value of the current node.
     *  3. Repeat 1-2 for the rest of the input.
     * */
}