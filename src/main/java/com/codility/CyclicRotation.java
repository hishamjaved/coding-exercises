package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *A zero-indexed array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is also moved to the first place.

 For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal is to rotate array A K times; that is, each element of A will be shifted to the right by K indexes.

 Write a function:

 class Solution { public int[] solution(int[] A, int K); }
 that, given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.

 For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].

 Assume that:

 N and K are integers within the range [0..100];
 each element of array A is an integer within the range [?1,000..1,000].
 In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */

public class CyclicRotation {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + CyclicRotation.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int k = in.nextInt();
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = in.nextInt();
        }

        System.out.println("K:"+k);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(solution(a, n, k)));
    }

    /**
     *
     * @param A
     * @param N
     * @param K
     * @return
     */
    public static int[] solution(int[] A,int N, int K) {
        if (N==0)
            return A;
        if (K>=N)
            K %= N;
        if (K==0)
            return A;
        int [] rotA = new  int [N];
        for (int i=0; i<N; i++)
            rotA[i] = (i<K) ? A[N+i-K] : A[i-K];
        return rotA;
    }
}

