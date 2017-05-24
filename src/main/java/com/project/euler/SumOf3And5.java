package com.project.euler;

import java.io.*;
import java.util.*;

/**
 Multiples of 3 and 5
 Problem 1

 If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

 Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class SumOf3And5 {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "projecteuler"
            + File.separator + SumOf3And5.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        System.out.println(solution(n));
        System.out.println(solution1(n));
    }

    /**
     * O(1)
     * @param n
     * @return
     */
    public static int solution(int n) {
        int a=3;
        int b=5;
        int gcd=1;
        int lcm=15;

        //To find sum below 1000
        n = n-1;

        // If basic formula to find the sum up to n number is
        // (n^2+n)/2

        // then the formula to find sum of single specific multiple (m) should be
        // [m*Math.floor(n/m)] * [{gcd+Math.floor(n/m)}/2]

        // Now the formula of more than one multiples up to specific number n will be
        // Least Common Divisor(LCM) = m1*m2 = 3*5 = 15
        // Greatest Common Divisor(GCD) = 3 & 5 is 1
        // [m1*Math.floor(n/m1)] * [{gcd+Math.floor(n/m1)}/2]  +  [m2*Math.floor(n/m2)] * [{gcd+Math.floor(n/m2)}/2] - [lcm*Math.floor(n/lcm)] * [{gcd+Math.floor(n/lcm)}/2]

        return formula(n,a,gcd)+formula(n,b,gcd)-formula(n,lcm,gcd);
    }

    private static int formula(int n,int m,int gcd){
        return m*(int)Math.floor(n/m)*(gcd+(int)Math.floor(n/m))/2;
    }


    /**
     * O(n)
     * @param n
     * @return
     */
    public static int solution1(int n) {
        int sum=0;
        for(int i=3;i<n;i++){
            if(i%5==0 || i%3==0)
                sum+=i;
        }
        return sum;
    }
}

