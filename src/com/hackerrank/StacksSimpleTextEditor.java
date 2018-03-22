package com.hackerrank;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class StacksSimpleTextEditor {
    private static final String INPUT = "8\n" +
            "1 abc\n" +
            "3 3\n" +
            "2 3\n" +
            "1 xy\n" +
            "3 2\n" +
            "4 \n" +
            "4 \n" +
            "3 1";

    public static void main(String[] args) {
        Scanner in = new Scanner(INPUT);

        int n = in.nextInt();
        Deque<String> buffer = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int code = in.nextInt();
            String command = "";
            if (!buffer.isEmpty()) {
                command = buffer.peekLast();
            }
            switch (code) {
                case 1: {
                    buffer.add(command + in.next());
                    break;
                }
                case 2: {
                    int num = in.nextInt();
                    command = command.substring(0, command.length() - num);

                    buffer.add(command);
                    break;
                }
                case 3: {
                    int num = in.nextInt();
                    System.out.println(command.charAt(num - 1));
                    break;
                }
                case 4: { // Undo.
                    if (!buffer.isEmpty()) {
                        buffer.removeLast();
                    }
                    break;
                }
                default: {
                    throw new RuntimeException("Received code is not supported.");
                }
            }
        }
    }
}
