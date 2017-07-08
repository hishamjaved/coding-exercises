package com.codility;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 Write a function

 class Solution { public int solution(int[] A); }

 that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.

 Assume that:

 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [?1,000,000..1,000,000].
 For example, given array A consisting of six elements such that:

 A[0] = 2    A[1] = 1    A[2] = 1
 A[3] = 2    A[4] = 3    A[5] = 1
 the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.

 Complexity:

 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class DistinctElement {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + DistinctElement.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }

        System.out.println(Arrays.toString(a));
        //System.out.println(solution(a));
        System.out.println(solution2(a));
    }
    public static int solution2(int[] A) {
        return (int) IntStream.of(A).distinct().count();
    }

    public static int solution(int[] A) {
        Arrays.sort(A);
        int count=1;
        int i=0;
        for(int a:A){
            if(i>0 && a!=A[i-1]){
                count++;
            }
            i++;
        }
        return count;
    }
}

