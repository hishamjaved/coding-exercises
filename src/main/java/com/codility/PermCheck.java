package com.codility;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 A non-empty zero-indexed array A consisting of N integers is given.

 A permutation is a sequence containing each element from 1 to N once, and only once.

 For example, array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 is a permutation, but array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 is not a permutation, because value 2 is missing.

 The goal is to check whether array A is a permutation.

 Write a function:

 class Solution { public int solution(int[] A); }
 that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.

 For example, given array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 the function should return 1.

 Given array A such that:

 A[0] = 4
 A[1] = 1
 A[2] = 3
 the function should return 0.

 Assume that:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [1..1,000,000,000].
 Complexity:

 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class PermCheck {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + PermCheck.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }

        System.out.println(Arrays.toString(a));
        System.out.println(solution1(a));
    }

    /**
     * 100% accurate
     * @param A
     * @return
     */
    public static int solution1(int[] A) {
        int[] perm = new int[A.length];
        for(int i=0; i<A.length; i++) {
            if(A[i]-1>=A.length || perm[A[i]-1]!=0) {
                return 0;
            }
            perm[A[i]-1]++;
        }
        return 1;
    }


    /**
     * 80% accuracy
     * @param A
     * @return
     */
    public static int solution(int[] A) {
        if(A.length==0){
            return 0;
        }
        int max=0;
        for(int i=0;i<A.length;i++){
            if(A[i]>max){
                max=A[i];
            }
        }

        if(A.length==max){
            return 1;
        }
        else{
            return 0;
        }


    }
}

