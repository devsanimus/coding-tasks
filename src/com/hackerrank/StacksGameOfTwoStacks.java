package com.hackerrank;

import java.io.*;
import java.util.*;

public class StacksGameOfTwoStacks {
    private static ArrayList<Map.Entry<Integer, Integer>> visited = new ArrayList<>();

    public static final String INPUT4 = "1\n" +
            "5 4 10\n" +
            "4 2 4 6 1\n" +
            "2 1 8 5";
    public static final String INPUT1 = "1\n" +
            "1 2 2\n" +
            "2\n" +
            "1 1 1 1 1 1 1 3";
    public static final String INPUT2 = "2\n" +
            "1 4 6\n" +
            "0\n" +
            "3 3 0 0\n" +
            "1 1 2\n" +
            "1 2";
    public static final String INPUT3 = "1\n" +
            "7 7 10\n" +
            "1 0 2 2 1 0 0\n" +
            "2 1 0 1 1 1 1";
    public static final String INPUT = "1\n" +
            "10 10 599\n" +
            "100 100 100 100 1 1 1 1 1 100\n" +
            "200 100 1 1 1 1 1 1 1 1";
    public static final String INPUT5 = "1\n" +
            "20 35 17\n" +
            "1 1 0 1 1 1 0 0 1 1 0 1 1 1 0 0 0 0 1 0\n" +
            "1 1 1 0 0 0 1 0 0 1 0 1 0 0 0 1 0 0 1 1 0 0 0 1 1 0 1 1 0 1 1 0 0 1 0";

    static class ResultPair {
        private long sum = 0;
        private int num = 0;
        private int maxNum = 0;
        private long maxSum = 0;

        public void add(int sum) {
            this.sum += sum;
            increase();

            if (this.num > this.maxNum) {
                this.maxNum = this.num;
                this.maxSum = this.sum;
            } else if (this.sum > this.maxSum && this.num == this.maxNum) {
                this.maxSum = this.sum;
            }
        }

        public void substract(int sum) {
            this.sum -= sum;
            decrease();
        }

        private void increase() {
            this.num++;
        }

        private void decrease() {
            this.num--;
        }
    }

    //    private static void findMaxCount(Deque<Integer> aList, Deque<Integer> bList, int x, ResultPair result, int aX, int bX) {
//        boolean aOk = !aList.isEmpty(), bOk = !bList.isEmpty();
//
//        if (!aOk && !bOk) {
//            return;
//        }
//
//        AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(aList.size(), bList.size());
//        if (!visited.contains(entry)) {
//            visited.add(entry);
//        } else {
//            return;
//        }
//
//        if (aOk) {
//            int val = aList.pollLast();
//            if ((result.sum + val) <= x) {
//                result.add(val);
//                findMaxCount(aList, bList, x, result);
//                result.substract(val);
//            }
//            aList.addLast(val);
//        }
//
//        if (bOk) {
//            int val = bList.pollLast();
//            if ((result.sum + val) <= x) {
//                result.add(val);
//                findMaxCount(aList, bList, x, result);
//                result.substract(val);
//            }
//            bList.addLast(val);
//        }
//    }
    private static void findMaxCount(Stack<Integer> aList, Stack<Integer> bList, int x, ResultPair result, int aX, int bX) {
        boolean aOk = aX < aList.size(), bOk = bX < bList.size();

        if (!aOk && !bOk) {
            return;
        }

        if (aOk) {
            int val = aList.get(aX);
            aX++;
            if ((result.sum + val) <= x) {
                result.add(val);
                findMaxCount(aList, bList, x, result, aX, bX);
                result.substract(val);
            }
            aX--;
        }

        if (bOk) {
            int val = bList.get(bX);
            bX++;
            if ((result.sum + val) <= x) {
                result.add(val);
                findMaxCount(aList, bList, x, result, aX, bX);
                result.substract(val);
            }
            bX--;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(INPUT3);
        //Scanner in = new Scanner(new File("StacksGameOfTwoStacks.input"));
        //Scanner in = new Scanner(new File("StacksTwoGames2.input"));


        int totalGames = in.nextInt();
        for (int g = 0; g < totalGames; g++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();

            //Deque<Integer> aList = new ArrayDeque<>();
            Stack<Integer> aList = new Stack<>();
            for (int a_i = 0; a_i < n; a_i++) {
                aList.push(in.nextInt());
            }
            //Deque<Integer> bList = new ArrayDeque<>();
            Stack<Integer> bList = new Stack<>();
            for (int b_i = 0; b_i < m; b_i++) {
                bList.push(in.nextInt());
            }

            ResultPair resultPair = new ResultPair();
            findMaxCount(bList, aList, x, resultPair, 0, 0);
            System.out.printf("Max num: %d, Max sum: %d%n", resultPair.maxNum, resultPair.maxSum);

            /*ResultPair p = new ResultPair();
            findMaxCount(bList, aList, x, p);
            System.out.printf("Max num: %d, Max sum: %d%n", p.maxNum, p.maxSum);
*/

            /*
            int[] a = new int[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextInt();
            }
            int[] b = new int[m];
            for (int b_i = 0; b_i < m; b_i++) {
                b[b_i] = in.nextInt();
            }

            // Current pointers for the given arrays.
            int idx[] = new int[2];
            long removedCntSum = 0;
            int removedCnt = 0;
            while (true) {
                long incSum = 0;
                boolean aOk = idx[0] < a.length;
                boolean bOk = idx[1] < b.length;
                int aVal = 0, bVal = 0;
                if (aOk)
                    aVal = a[idx[0]];
                if (bOk)
                    bVal = b[idx[1]];

                if (aOk) {
                    if (bOk) {
                        if (aVal < bVal) {
                            incSum += a[idx[0]]; // Increase the sum.
                            idx[0]++; // Move pointer.
                        } else {
                            incSum += b[idx[1]];
                            idx[1]++;
                        }
                    } else {
                        incSum += a[idx[0]];
                        idx[0]++;
                    }
                } else if (bOk) {
                    incSum += b[idx[1]];
                    idx[1]++;
                }

                removedCntSum += incSum;

                if (removedCntSum > x) {
                    break;
                }

                removedCnt++;

                if ((!aOk && !bOk) || (removedCnt == (a.length + b.length))) {
                    break;
                }
            }
            System.out.println(removedCnt);*/
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
