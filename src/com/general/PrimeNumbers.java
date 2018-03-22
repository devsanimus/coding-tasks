package com.general;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/**
 * Find all prime numbers from 1 to N.
 */
public class PrimeNumbers {
    public static void main(String[] args) {
        int N = 1000000;
        int list[] = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = i;
        }

        //generatePrimes1(list, N);
        generatePrimes2(list, N);
    }

    static ArrayList<Integer> getPrimes(int numPrimes) {
        ArrayList<Integer> primes = new ArrayList<>(numPrimes);
        int n = 2;
        while (primes.size() < numPrimes) {
            while (!isPrime(n)) {
                n++;
            }
            primes.add(n);
            n++;
        }
        return primes;
    }

    static boolean isPrime(int n) {
        if (n < 2 || n % 2 == 0) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        int d = 3;
        while (d * d <= n) {
            if (n % d == 0) {
                return false;
            }
            d += 2;
        }
        return true;
    }

    static void generatePrimes2(int[] list, int N) {
        Deque<Integer> primes = new ArrayDeque<>();
        primes.addFirst(2);
        primes.addFirst(3);
        for (int i = 5; i < N; i += 2) {
            boolean isPrime = true;
            for (int k = 2; k * k < i; k++) {
                if (i % k == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.addFirst(i);
            }
        }
        final Iterator<Integer> iterator = primes.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static void generatePrimes1(int[] list, int N) {
        // Filter list.
        int prime = getNextPrime(list, 1);
        while (prime != -1) {
            for (int i = prime + prime; i < N; i = i + prime) {
                list[i] = -1;
            }
            prime = getNextPrime(list, prime);
        }
        for (int i = 2; i < list.length; i++) {
            if (list[i] != -1) {
                System.out.println(list[i]);
            }
        }
    }

    static int getNextPrime(int[] list, int prevPosition) {
        for (int i = prevPosition + 1; i < list.length; i++) {
            if (list[i] != -1) {
                return list[i];
            }
        }
        return -1;
    }
}
