package com.project.euler;

import java.io.FileNotFoundException;

/**
 Sum square difference
 Problem 6
 The sum of the squares of the first ten natural numbers is,

 12 + 22 + ... + 102 = 385
 The square of the sum of the first ten natural numbers is,

 (1 + 2 + ... + 10)2 = 552 = 3025
 Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 ? 385 = 2640.

 Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */

public class Show10001PrimeNumber {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(solution());
    }

    public static long solution() {
        int i=1;
        int count=0;
        while(count<10001){
            i++;
            if(isPrime(i)){
                count++;
            }
        }
        return i;
    }

    private static boolean isPrime(long n){
        for(int i=2;i<=Math.floor(Math.sqrt(n));i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}

