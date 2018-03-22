package com.hackerrank;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/* Time for an input file "StackPoisonousPlants.input":
    All structures are giving the best Time O(N^2)
    1 <= N <= 10^5

    Java int[] arrays only primitives       ~1.5 sec    (only 55 points out of 70 on hackerrank.)
    Java int[] arrays with                  ~3.3 sec  (two Integer temp variables !!!)
    Java Deque = ArrayDequeue takes         ~5.4 sec.
    Java Deque = LinkedList takes           ~7.7 sec.
    Java Stack<> takes                      ~18 sec.
    Java Deque = ConcurrentLinkedDeque takes ~30 sec.
    Java List = ArrayList takes             > 5 minutes ???
    Java List = LinkedList takes            ~11 sec.
* */
public class StacksPoisonousPlants {
    //        private static final String INPUT = "5\n" +
//            "1 5 2 4 3 5 5 4 3 2 1 2 2 3 3 4 4 5 5 6 6 5 5 4 4 3 3 2 2 1";
//    private static final String INPUT = "7\n" +
    //          "6 5 8 4 7 10 9";
//    private static final String INPUT = "8\n" +
//            "3 1 10 7 3 5 6 6"; // Test case 7, exp: 3
//    private static final String INPUT = "5\n" +
//        "2 1 7 6 5 4"; // 4
//    private static final String INPUT = "9\n" +
//        "3 7 1 2 4 8 2 7 10"; // Case 2 Exp 2
//    private static final String INPUT = "10\n" +
//        "2 11 5 14 5 10 9 19 12 5"; // Case 8 Exp 4
      private static final String INPUT = "17\n" +
        "20 5 6 15 2 2 17 2 11 5 14 5 10 9 19 12 5"; // Case 8 Exp 4
    /*
    (3) 5
    //(2) 12
    //1 19
 //   (2) 9
    //1 10
    //(2)(3)3 5
    //1 14
 // (2)2 5
    //1 11
    (2)2 2
    //1 17
    (2)2 2
    //1 15
    //1 6
    //1 5
    //0 20
    * */
    //    "20 5 6 15 2 2 17 2 11 5 14 5 10 9 19 12 5"; // Case 8 Exp 4
    //    "20     15 2 2    2    5    5    9    12 5"; // Case 8 Exp 4
    //    "20     15 2 2    2         5            5"; // Case 8 Exp 4


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(INPUT);
//            Scanner in = new Scanner(new File("StacksPoisonousPlants2.input")); // Expected 99900
//       Scanner in = new Scanner(new File("StacksPoisonousPlants.input")); // Expected 32399

        long start = System.currentTimeMillis();
        int daysElapsed = solutionIndexArray(in);
        //int daysElapsed = solutionShiftsOnly(in);
        //int daysElapsed = solutionArraysOnly(in);
        //int daysElapsed = solutionDataSctructures(in);

        System.out.println(daysElapsed);

        long end = System.currentTimeMillis() - start;
        System.out.printf("time: %.2f sec. %n", (float) end / 1000);
    }

    private static int solutionDataSctructures(Scanner in) {
        int n = in.nextInt();

        Deque<Integer> s1 = new ArrayDeque<>(n);
        Deque<Integer> s2 = new ArrayDeque<>(n);
        Deque<Integer> sTemp;


        for (int i = 0; i < n; i++) {
            s1.addFirst(in.nextInt());
        }

        int daysElapsed = 0;
        while (true) {
            boolean hasRemovedElements = false;
            // Move elements from initial stack to the supplementary.
            while (!s1.isEmpty()) {
                Integer e1 = s1.removeFirst(), e2;
                if (!s1.isEmpty()) {
                    e2 = s1.peekFirst();

                    if (e1 <= e2) {
                        s2.addFirst(e1);
                    } else {
                        hasRemovedElements = true;
                    }
                } else {
                    s2.addFirst(e1);
                }
            }
            if (!hasRemovedElements) {
                break;
            }
            daysElapsed++;

            // Swap stacks.
            sTemp = s1;
            s1 = s2;
            s2 = sTemp;

            hasRemovedElements = false;
            if (!s1.isEmpty()) {
                Integer tmp = s1.removeFirst();
                s2.addFirst(tmp);
                while (!s1.isEmpty()) {
                    Integer e1 = tmp, e2 = s1.removeFirst();
                    if (e2 <= e1) {
                        s2.addFirst(e2);
                    } else {
                        hasRemovedElements = true;
                    }
                    tmp = e2;
                }
            }
            if (!hasRemovedElements) {
                break;
            }
            daysElapsed++;

            // Swap stacks.
            sTemp = s1;
            s1 = s2;
            s2 = sTemp;
        }
        return daysElapsed;
    }


    private static int solutionIndexArray(Scanner in) {
        int n = in.nextInt();

        int[] s1 = new int[n];

        for (int i = 0; i < n; i++) {
            s1[i] = in.nextInt();
        }

        int daysElapsed = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> maxAlive = new ArrayDeque<>();
        stack.addFirst(s1[0]);
        maxAlive.addFirst(0);
        for (int i = 1; i < n; i++) {
            if (s1[i] > stack.peekFirst()) {
                stack.addFirst(s1[i]);
                maxAlive.addFirst(1); // Will die on first day.
                daysElapsed = Math.max(daysElapsed, 1);
            } else {
                // Get max days from previous element and
                // remove previous element.
                stack.removeFirst();
                int prevDays = maxAlive.removeFirst();

                int curMax = prevDays + 1; // Estimated min days alive.
                if (stack.isEmpty() || s1[i] <= stack.peekFirst()) {
                    while(!stack.isEmpty() && s1[i] <= stack.peekFirst()){
                        stack.removeFirst();
                        int prevMax = maxAlive.removeFirst();
                        curMax=Math.max(curMax, prevMax+1);
                    }
                    if(stack.isEmpty()){
                        // This element will survive.
                        curMax = 0;
                    }

                }
                stack.addFirst(s1[i]);
                maxAlive.addFirst(curMax);

                daysElapsed = Math.max(daysElapsed,curMax);
            }
        }

        return daysElapsed;
    }

    private static int solutionShiftsOnly(Scanner in) {
        int n = in.nextInt();

        int[] s1 = new int[n];

        for (int i = 0; i < n; i++) {
            s1[i] = in.nextInt();
        }

        int daysElapsed = 0;

        int prevVal = s1[0], leftMost = s1[0], maxDaysElapsed = 0;
        boolean hasDied = false;
        for (int i = 0; i < n; i++) {
            int val = s1[i];

            if (val > leftMost) {
                if ((val <= prevVal) || ((val > prevVal) && !hasDied)) {
                    if (i > 1) {
                        if (s1[i - 2] < val && val == prevVal) {
                            //daysElapsed++;
                        } else daysElapsed++;
                    } else
                        daysElapsed++;
                }
                hasDied = true;
            } else {
                leftMost = val;
                maxDaysElapsed = Math.max(maxDaysElapsed, daysElapsed);
            }
            prevVal = val;
        }
        maxDaysElapsed = Math.max(maxDaysElapsed, daysElapsed);


        return maxDaysElapsed;
    }

    private static int solutionArraysOnly(Scanner in) {
        int n = in.nextInt();

        int[] s1 = new int[n];
        int[] s2 = new int[n];
        int[] sTemp;

        for (int i = 0; i < n; i++) {
            s1[i] = in.nextInt();
        }

        int daysElapsed = 0;
        int idx1 = s1.length - 1, idx2 = 0;
        while (true) {
            boolean hasRemovedElements = false;
            // Move elements from initial stack to the supplementary.
            while (idx1 >= 0) {
                int e1 = s1[idx1--], e2;
                if (idx1 >= 0) {
                    e2 = s1[idx1];
                    if (e1 <= e2) {
                        s2[idx2++] = (e1);
                    } else {
                        hasRemovedElements = true;
                    }
                } else {
                    s2[idx2++] = (e1);
                }
            }
            if (!hasRemovedElements) {
                break;
            }
            daysElapsed++;

            // Swap stacks.
            sTemp = s1;
            s1 = s2;
            s2 = sTemp;

            idx1 = idx2 - 1;
            idx2 = 0;

            hasRemovedElements = false;
            if (idx1 >= 0) {
                int tmp = s1[idx1--];
                s2[idx2++] = (tmp);
                while (idx1 >= 0) {
                    Integer e1 = tmp, e2 = s1[idx1--];
                    if (e2 <= e1) {
                        s2[idx2++] = (e2);
                    } else {
                        hasRemovedElements = true;
                    }
                    tmp = e2;
                }
            }
            if (!hasRemovedElements) {
                break;
            }
            daysElapsed++;

            // Swap stacks.
            sTemp = s1;
            s1 = s2;
            s2 = sTemp;

            idx1 = idx2 - 1;
            idx2 = 0;
        }

        return daysElapsed;
    }
}
