package com.google.codejam2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class TidyNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            System.out.printf("Case #%d: %s%n", i, lastTidyNumber(in.nextLong()).toString());
        }
    }

    private static Long lastTidyNumber(Long n){
        if(n>=10){
            if(n==10 || n==20){
                n=n-1;
            }
            else if(n<=10000){
                while(!n.toString().equals(sortedString(n.toString())) || n.toString().endsWith("0")){
                    n = n - 1;
                }
            }
            else{
                while(!n.toString().equals(sortedString(n.toString())) || n.toString().endsWith("0")){
                    for(int i=0;i<n.toString().length()-1;i++){
                        if(n.toString().charAt(i)>n.toString().charAt(i+1)){
                            n = n - Long.valueOf(n.toString().substring(i+1,n.toString().length()));
                            break;
                        }
                    }
                    n = n - 1;
                }
            }
        }
        return n;
    }

    private static String sortedString(String n){
        char[] arr = n.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}