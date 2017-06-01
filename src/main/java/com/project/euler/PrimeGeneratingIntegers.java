package com.project.euler;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 Prime generating integers
 Problem 357
 Consider the divisors of 30: 1,2,3,5,6,10,15,30.
 It can be seen that for every divisor d of 30, d+30/d is prime.

 Find the sum of all positive integers n not exceeding 100 000 000
 such that for every divisor d of n, d+n/d is prime.


 Correct Answer
 //If we use BigInteger.isProbablePrime with certainty 6
 1739023853137
 218 sec.

 //If we use BigInteger.isProbablePrime with certainty 8
 1739023853137
 249 sec.

 //If we use BigInteger.isProbablePrime with certainty 9
 1739023853137
 310 sec. (5 Minutes)

 //If we use our customized isPrime Method
 1739023853137
 1280 sec. (21 Minutes)

 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 //WRONG ANSWER with certainty 5
 1739035562255
 222 sec.

 //WRONG ANSWER with certainty 2
 1740481935357
 161 sec.

 //WRONG ANSWER with certainty 1
 1740870267623
 161 sec.

 //WRONG ANSWER with certainty 4
 1739032688733
 193 sec.

 */

public class PrimeGeneratingIntegers {

    public static void main(String[] args) throws FileNotFoundException {
        long time = System.currentTimeMillis();
        System.out.println(solution());
        System.out.println(((System.currentTimeMillis()-time)/1000)+" sec.");
    }

    public static long solution() {
        long i=2;
        long sum=1;
        long limit = 100000000;
        int certainty=6;
        while(i<limit){
            if(isPrime((i+1),certainty)){
                boolean isValid=true;
                for (int d = 1; d <= Math.ceil(Math.sqrt(i)); d++) {
                    if (i%d == 0 && !isPrime((d + i / d),certainty)) {
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
                    sum+=i;
                }
            }
            i++;
        }
        return sum;
    }

    private static boolean isPrime(long n, int certainty){
        return BigInteger.valueOf(n).isProbablePrime(certainty);
    }

    private static boolean isPrime(long n){
        if(n>9){
            if(n%2==0 || n%3==0 || n%4==0 || n%5==0
                    || n%6==0 || n%7==0 || n%8==0 || n%9==0){
                return false;
            }
        }

        for(int i=2;i<=Math.floor(Math.sqrt(n));i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

}

