package com.google.codejam2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BathroomStalls {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = in.nextInt();
            emptyStalls(i,n,k);
        }
    }

    private static void emptyStalls(int t,int n,int k){
        int y=0,z=0;
        int available = n-k;
        if(available==0){
            y=0;
            z=0;
        }
        else{
            int mid = n%2;
            if(mid==1){
                mid=n/2;

            }
            else{

            }
        }
        System.out.println("Case #" + t + ": " + y + " " + z);
    }
}