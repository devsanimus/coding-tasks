package com.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class StacksBalancedBracketsSolution2 {
    public static final String INPUT1 = "3\n" +
            "{[()]}\n" +
            "{[(])}\n" +
            "{{[[(())]]}}";
    public static final String INPUT = "3\n" +
            "{]{{]]]]]\n" +
            "{[(])}\n" +
            "{{[[(())]]}}";

    public static void main(String[] args) {
        Scanner in = new Scanner(INPUT);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String s = in.next();
            String result = isBalanced(s);
            System.out.println(result);
        }
        in.close();
    }

    public static String isBalanced(String input) {
        boolean res = processInput(input);
        return res ? "YES" : "NO";
    }

    private static boolean processInput(String input) {
        Map<Character, Character> chars = new HashMap<>();
        chars.put('(', ')');
        chars.put('{', '}');
        chars.put('[', ']');

        Stack<Character> list = new Stack<>();

        for (char c : input.toCharArray()) {
            if (chars.containsKey(c)) {
                list.push(c);
            }
            if (chars.containsValue(c)) {
                if (!list.empty() && (chars.get(list.peek()) == c)) {
                    list.pop();
                } else {
                    return false;
                }
            }
        }
        return list.empty();
    }
}
