package com.interview.bit.math.numbertheory;


import java.io.*;
import java.util.Scanner;

public class TrailingZerosInFactorial {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println(trailingZeroes(in.nextInt()));
    }

    public static int trailingZeroes(int a) {
        int zeroes = a/5;
        int p =2;
        while(a>=Math.pow(5,p)){
            zeroes += a/(int)Math.pow(5,p);
            p++;

        }
        return zeroes;
    }


}

