package com.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.IntConsumer;

public class StacksBalancedBrackets {
    public static final String INPUT1 = "4\n" +
            "{}\n" +
            "{[()]}\n" +
            "{[(])}\n" +
            "{{[[(())]]}}";
    public static final String INPUT = "4\n" +
            "{]{{]]]]]\n" +
            "{[(])}\n" +
            "{{[[(())]]}}\n" +
            "}}}{}{}{{[][]]]]]][[[[[]]]]][][][]]]]]}}}";

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
        BracketConsumer bracketConsumer = new BracketConsumer();
        input.chars().forEachOrdered(bracketConsumer);
        return bracketConsumer.isBalanced() ? "YES" : "NO";
    }

    static class BracketConsumer implements IntConsumer {
        private Stack<Integer> cList = new Stack<>();
        Map<Character, Character> chars = new HashMap<>();

        private BracketConsumer() {
            chars.put('(', ')');
            chars.put('{', '}');
            chars.put('[', ']');
        }

        @Override
        public void accept(int value) {
            if (chars.containsValue((char) value)) {
                if (!cList.empty()) {
                    // Why we need to cast to char.
                    //System.out.println((char)cList.peek().intValue()); // Will print '{'
                    //System.out.println((cList.peek())); // Will print '123'
                    char lastBracket = (char) cList.peek().intValue();
                    if (chars.containsKey(lastBracket) && (chars.get(lastBracket) == value)) {
                        cList.pop();
                        return;
                    }
                }
            }
            cList.push(value);
        }

        public boolean isBalanced() {
            return cList.empty();
        }
    }
}
