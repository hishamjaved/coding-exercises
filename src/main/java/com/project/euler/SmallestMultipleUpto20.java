package com.project.euler;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 Smallest multiple
 Problem 5
 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

 What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

public class SmallestMultipleUpto20 {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(solution());
    }

    public static int solution() {
        int n=2;
        int product=0;
        int div;
        while(true){
            product=20*n;
            div=19;
            while(product%div==0 && div>1){
                div--;
            }
            if(div<=1){
                break;
            }
            n++;
        }
        return product;
    }
}

