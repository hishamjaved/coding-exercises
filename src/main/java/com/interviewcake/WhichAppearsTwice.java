package com.interviewcake;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 I have an array where every number in the range 1...n1...n appears once except for one number which appears twice.
 Write a function for finding the number that appears twice.

 1 2 3 4 5 6 6 7 8 9 10

 sum of n numbers = (n^2 + n)/2  OR (n*(n+1))/2

 */

public class WhichAppearsTwice {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewcake"
            + File.separator + WhichAppearsTwice.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }
        System.out.println(solution(a));
    }


    public static int solution(int[] a) {
        if(a.length<2){
            return -1;
        }
        int n = a.length-1;
        long sum = (long)(Math.pow(n,2)+n)/2;
        long arraySum = 0;
        for(int i=0;i<a.length;i++){
            arraySum+=a[i];
        }
        return (int)(arraySum-sum);
    }


}

