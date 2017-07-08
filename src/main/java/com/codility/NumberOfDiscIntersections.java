package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 We draw N discs on a plane. The discs are numbered from 0 to N ? 1. A zero-indexed array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

 We say that the J-th disc and K-th disc intersect if J ? K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

 The figure below shows discs drawn for N = 6 and A as follows:

 A[0] = 1
 A[1] = 5
 A[2] = 2
 A[3] = 1
 A[4] = 4
 A[5] = 0


 There are eleven (unordered) pairs of discs that intersect, namely:

 discs 1 and 4 intersect, and both intersect with all the other discs;
 disc 2 also intersects with discs 0 and 3.
 Write a function:

 class Solution { public int solution(int[] A); }
 that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return ?1 if the number of intersecting pairs exceeds 10,000,000.

 Given array A shown above, the function should return 11, as explained above.

 Assume that:

 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [0..2,147,483,647].
 Complexity:

 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class NumberOfDiscIntersections {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + NumberOfDiscIntersections.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextInt();
        }

        System.out.println(Arrays.toString(a));
        System.out.println(solution(a));
    }

    /**
     * Score 100%
     * @param A
     * @return
     */
    public static int solution(int[] A) {
        int n = A.length;
        int[] sum = new int[n];

        for (int i = 0; i < n; i++) {
            int right;
            //if i+A[i]<= n-1, that's it, extract this i+A[i], let sum[i+A[i]]++, means there is one disk that i+A[i]
            if (n - i - 1 >= A[i]){
                right = i + A[i];
            } else {
                right = n - 1;
            }

            sum[right]++;
        }

        for (int i = 1; i < n; i++) {
            sum[i] += sum[i - 1];  //sum[i] means that there are sum[i] number of values that <= i;
        }

        long ans = (long) n * (n - 1) / 2;

        for (int i = 0; i < n; i++) {
            int left;

            if (A[i] > i) {
                left = 0;
            } else {
                left = i - A[i];// Find the positive i-A[i].
            }

            if (left > 0){
                ans -= sum[left - 1];//Find the number that is smaller than 1-A[i], sum[n-1] will never be used as we only need sum[n-1-1] at most.
            }
        }

        if (ans > 10000000) {
            return -1;
        }

        return (int) ans;
    }

    /**
     * Score 68% :|
     * Correctness:100% :) Performance: 37% :(
     * @param A
     * @return
     */
    public static int solution1(int[] A){
        long n = A.length;
        long sum = 0;
        long total = n * (n - 1) / 2;
        for (int i=0; i<n; i++) {
            for (int right = i+1; right<n;right++) {
                if ( (right - A[i]- i) > A[right]) {
                    sum++;
                }
            }
        }

        if ((total - sum) > 10000000) {
            return -1;
        }
        return (int)(total - sum);
    }


    /**
     * Score 12% :(
     * @param A
     * @return
     */
    public static int solution3(int[] A) {
        int count=0;
        int startRange,endRange;
        for(int i=0;i<A.length;i++){
            if(A[i]>0){
                startRange = i - A[i]<0?0:i-A[i];
                endRange = i + A[i]>A.length-1?A.length-1:i + A[i];

                for(int j=startRange; j<=endRange;j++){
                    if(i!=j){
                        if(A[i]>=A[j] || A[j]==0){
                            count++;
                            if(count>10000000){
                                return -1;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}

