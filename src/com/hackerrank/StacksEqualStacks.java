package com.hackerrank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class StacksEqualStacks {
    private static final String INPUT1 = "5 3 4\n" +
            "3 2 1 1 1\n" +
            "4 3 2\n" +
            "1 1 4 1";
    private static final String INPUT = "3 3 3\n" +
            "2 0 0\n" +
            "1 0 2\n" +
            "1 1 0";

    public static void main(String[] args) {
        Scanner in = new Scanner(INPUT);

        int[] sum = new int[3];

        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();

        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();
        Deque<Integer> s3 = new ArrayDeque<>();

        for (int h1_i = 0; h1_i < n1; h1_i++) {
            int n = in.nextInt();
            sum[0] += n;
            s1.push(n);
        }
        for (int h2_i = 0; h2_i < n2; h2_i++) {
            int n = in.nextInt();
            sum[1] += n;
            s2.push(n);
        }
        for (int h3_i = 0; h3_i < n3; h3_i++) {
            int n = in.nextInt();
            sum[2] += n;
            s3.push(n);
        }

        System.out.printf("%d %d %d", sum[0], sum[1], sum[2]);

        while (sum[0] != sum[1] || sum[1] != sum[2]) {
            int maxSum = Math.max(Math.max(sum[0], sum[1]), sum[2]);
            for (int i = 0; i < 3; i++) { // Find cylinder with the max sum.
                if (maxSum == sum[i]) {
                    switch (i) {
                        case 0: {
                            sum[i] -= s1.removeLast();
                            break;
                        }
                        case 1: {
                            sum[i] -= s2.removeLast();
                            break;
                        }
                        case 2: {
                            sum[i] -= s3.removeLast();
                            break;
                        }
                    }
                    break; // Cancel loop.
                }
            }
        }
        System.out.println();
        System.out.println("Max highest sum: " + sum[0]);
    }
    /* Solution without helper data structures.
        Scanner sc = new Scanner(System.in);
        int[][] stacks =  new int[3][];
        int[] sums = new int[3];
        int [] indices = new int[3];
        for(int i = 0; i < stacks.length ; i++)
            stacks[i] = new int [sc.nextInt()];
        for(int i = 0 ; i < stacks.length ; i++){
            for(int j = 0 ; j < stacks[i].length ; j++)
                {
                stacks[i][j] = sc.nextInt();
                sums[i] +=stacks[i][j];
            }
        }
        while(sums[0] != sums[1] || sums[1]!=sums[2]){
            int op = 0;
            if(sums[1] > sums[0])
                op = 1;
            if(sums[op] < sums[2])
                op=2;
            sums[op]-=stacks[op][indices[op]];
            indices[op]++;
        }
        System.out.println(sums[0]);
    * */

    /* Solution based on prefix sum array.
        int n1, n2, n3;
        Scanner scan = new Scanner(System.in);
        n1 = scan.nextInt();
        n2 = scan.nextInt();
        n3 = scan.nextInt();
        Set<Integer> s1 = new HashSet<Integer>();
        Set<Integer> s2 = new HashSet<Integer>();

        int[] A = new int[n1];
        int[] B = new int[n2];
        int[] C = new int[n3];

        for (int i=0;i<n1;i++) {
            A[i] = scan.nextInt();
        }
        for (int i=0;i<n2;i++) {
            B[i] = scan.nextInt();
        }
        for (int i=0;i<n3;i++) {
            C[i] = scan.nextInt();
        }

        int h = 0;
        for (int i=n1-1;i>=0;i--) {
            h += A[i];
            s1.add(h);
        }
        h = 0;
        for (int i=n2-1;i>=0;i--) {
            h += B[i];
            s2.add(h);
        }
        int ans = 0;
         h = 0;
        for (int i=n3-1;i>=0;i--) {
            h += C[i];
            if (s1.contains(h) && s2.contains(h)) ans = h;
        }
    * */
}
