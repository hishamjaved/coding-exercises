package com.interview.bit;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(findPrimeNumbers(n));
        System.out.println(isPrimeNumber(n));
    }

    private static ArrayList<Integer> findPrimeNumbers(int a) {
        ArrayList<Integer> factors;
        ArrayList<Integer> primes = new ArrayList<Integer>();
        if (a == 0)
            return primes;
        else if (a == 1) {
            primes.add(1);
            return primes;
        }
        for (int j = 1; j <= a; j++) {
            factors = new ArrayList<>();
            for (int i = 1; i <= Math.sqrt(j); i++) {
                if (j % i == 0) {
                    factors.add(i);
                    if (j / i != Math.sqrt(j)) {
                        factors.add(j / i);
                    }
                    if (factors.size() > 2)
                        break;
                }
            }
            if (factors.size() == 2) {
                primes.add(j);
            }
        }
        return primes;
    }

    private static int isPrimeNumber(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                factors.add(i);
                if (a / i != Math.sqrt(a)) {
                    factors.add(a / i);
                }
                if (factors.size() > 2)
                    break;
            }
        }
        return factors.size() == 2 ? 1 : 0;
    }
}

