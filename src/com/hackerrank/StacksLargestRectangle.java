package com.hackerrank;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/*
    1. Problem is also known as "Maximum Histogram Area"

    Could be solved using indexes inside a stack instead of actual values.
    https://www.youtube.com/watch?v=ZmnqCZp9bBs

    2. Another solution is to use a second stack.
    Put elements inside the second stack and for each popped element count indexes in both stacks
    to find out the max area.
* */
public class StacksLargestRectangle {
    private static final String INPUT = "10\n" +
            "10 4 7 5 9 3 6 8 2 1";
//    private static final String INPUT = "10\n" +
//            "8979 4570 6436 5083 7780 3269 5400 7579 2324 2116";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(INPUT);

        int n = scanner.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = scanner.nextInt();
        }

        long result = largestRectangle(h);

        System.out.println(result);
    }

    private static long largestRectangle(int[] h) {

        Stack<Map.Entry<Integer, Integer>> list = new Stack<>();
        long maxArea = 0;
        for (int i = 0; i < h.length; i++) {
            if (!list.empty()) {
                Map.Entry<Integer, Integer> entry = list.peek();

                // New elements is at the top of the stack.
                if (h[i] == entry.getKey()) {
                    list.peek().setValue(list.peek().getValue() + 1);
                }
                // New element is above the top element of stack.
                else if (h[i] > entry.getKey()) {
                    // Add new element to the stack.
                    list.push(new AbstractMap.SimpleEntry<>(h[i], 1));
                }
                // New element is somewhere in the stack but is less then the top element.
                else if (h[i] < entry.getKey()) {
                    // Counting removed elements
                    int cntRemoved = 0;

                    while (!list.empty() && list.peek().getKey() > h[i]) {
                        entry = list.pop();

                        int value = entry.getValue() + cntRemoved;
                        int removedRectangle = entry.getKey() * value;
                        cntRemoved = value;

                        maxArea = Math.max(maxArea, removedRectangle);
                    }

                    if (!list.empty() && list.peek().getKey() == h[i]) {
                        list.peek().setValue(list.peek().getValue() + cntRemoved + 1);
                    } else {
                        list.push(new AbstractMap.SimpleEntry<>(h[i], cntRemoved + 1));
                    }
                }
                // Lowest element has reached.
                else {
                    // Reset stack and check whether a max rectangle was inside the stack.
                    long result = unstackAndGetMaxArea(list, maxArea);
                    maxArea = Math.max(maxArea, result);
                }
            } else {
                int value = h[i];
                Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(value, 1);
                list.push(entry);
            }
        }

        if (!list.empty()) {
            long result = unstackAndGetMaxArea(list, maxArea);
            maxArea = Math.max(maxArea, result);
        }

        return maxArea;
    }

    private static long unstackAndGetMaxArea(Stack<Map.Entry<Integer, Integer>> list, long maxArea) {
        long result = maxArea;
        // Count removed elements from stack.
        int cntRemoved = 0;
        while (!list.empty()) {
            Map.Entry<Integer, Integer> entry = list.pop();
            int value = entry.getValue() + cntRemoved;
            cntRemoved = value;
            int removedRectangleArea = entry.getKey() * value;

            result = Math.max(result, removedRectangleArea);
        }
        return result;
    }


    /** Solution 2. From hackerrank.
     private static void find(Deque<Long> a) {
     long max = 0;

     Deque<Long> b = new ArrayDeque<Long>();
     while(!a.isEmpty()) {
     long curr = a.pop();
     long idx = 1;

     Iterator<Long> it = a.iterator();
     while(it.hasNext() && it.next() > curr) {
     idx++;
     }

     it = b.iterator();
     while(it.hasNext() && it.next() > curr) {
     idx++;
     }

     long nxt = idx*curr;
     max = nxt > max ? nxt : max;
     //
     b.push(curr);
     }

     System.out.println(max);
     }
     */
}
