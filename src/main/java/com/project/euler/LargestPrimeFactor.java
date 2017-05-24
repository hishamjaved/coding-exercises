package com.project.euler;

import java.io.*;
import java.util.Scanner;

/**
 Largest prime factor
 Problem 3
 The prime factors of 13195 are 5, 7, 13 and 29.

 What is the largest prime factor of the number 600851475143 ?
 */

public class LargestPrimeFactor {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "projecteuler"
            + File.separator + LargestPrimeFactor.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        long n = in.nextLong();
        System.out.println(solution(n));
    }



    /**
     * O(log n)
     * @param number
     * @return
     */
    public static long solution(long number) {
        long r= (long)Math.floor(Math.sqrt((double) number));
        for(;r>0;r--){
            if(number%r==0){
                if(isPrime(r)){
                    break;
                }
            }
        }
        return r;
    }

    private static boolean isPrime(long n){
        long i=2;
        while(i < Math.sqrt(n)){
            if(n%i==0){
                return false;
            }
            i++;
        }
        return true;
    }
}

