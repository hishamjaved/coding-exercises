package com.codility;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A zero-indexed array A consisting of N different integers is given. The array contains integers in the range
 * [1..(N + 1)], which means that exactly one element is missing.

 Your goal is to find that missing element.

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given a zero-indexed array A, returns the value of the missing element.

 For example, given array A such that:

 A[0] = 2
 A[1] = 3
 A[2] = 1
 A[3] = 5
 the function should return 4, as it is the missing element.
 */

public class PermMissingElem {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + PermMissingElem.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        ArrayList<Integer> a = new ArrayList<>();
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            a.add(in.nextInt());
        }
        System.out.println(findMissingNumber(a));
    }

    public static int findMissingNumber(ArrayList<Integer> A) {
        int n = A.size()+1;
        long total = n * (n+1);
        total = total/2;
        long sum = 0;
        for(Integer i: A){
            sum+=i;
        }
        return (int)(total-sum);
    }
}

