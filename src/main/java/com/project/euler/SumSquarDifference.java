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

public class SumSquarDifference {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(solution());
    }

    public static long solution() {
        int n=100;
        long a = (long)Math.pow(((Math.pow(n,2)+n)/2),2);
        long b= 0;
        for(int i=1;i<=n;i++){
            b+= Math.pow(i,2);
        }
        return a-b;

    }
}

