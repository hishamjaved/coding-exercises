package com.toptal;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.

 You can perform a single swap operation in array A. This operation takes two indices I and J, such that 0 ? I ? J < N, and exchanges the values of A[I] and A[J].

 The goal is to check whether array A can be sorted into non-decreasing order by performing at most one swap operation.

 For example, consider array A such that:

 A[0] = 1
 A[1] = 5
 A[2] = 3
 A[3] = 3
 A[4] = 7
 After exchanging the values A[1] and A[3] we obtain an array [1, 3, 3, 5, 7], which is sorted in non-decreasing order.

 Write a function:

 class Solution { public boolean solution(int[] A); }

 that, given a non-empty zero-indexed array A consisting of N integers, returns true if the array can be sorted into non-decreasing order by performing at most one swap operation or false otherwise.

 For example, given:

 A[0] = 1
 A[1] = 2
 A[2] = 5
 A[3] = 3
 A[4] = 7
 the function should return true, as explained above.

 On the other hand, for the following array:

 A[0] = 1
 A[1] = 2
 A[2] = 5
 A[3] = 3
 A[4] = 4
 the function should return false, as there is no single swap operation that sorts the array.

 For the following array:

 A[0] = 1
 A[1] = 2
 A[2] = 5
 the function should return true, as the given array is already sorted.

 Assume that:

 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [1..1,000,000,000].
 Complexity:

 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

public class SingleSwapSorting {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + SingleSwapSorting.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i]=in.nextInt();
        }
        System.out.println(solution(x));
        System.out.println(Arrays.toString(x));
    }

    /**
     *
     * @param A
     * @return
     */
    public static boolean solution(int[] A){
        if(A.length<=2){
            return true;
        }
        int leftIndex = -1;
        int rightIndex = -1;
        int tmp =0;
        int swapCount=0;
        for(int i=0;i<A.length;i++){
            //1<5>3 in [1,{5},3,7] search swap index in forward direction
            //Find left Index
            if((i==0 && i<A.length-2 && A[i]>A[i+1]) ||
                    (i>0 && A[i-1]<A[i] && i<A.length-2 && A[i]>A[i+1]) ||
                    (i>0 && i==A.length-1 && A[i-1]<A[i]) ){
                for(int j=i+1;j<A.length;j++){
                    if(A[i]>A[j]){
                        leftIndex=i;
                        rightIndex=j;
                    }
                    else{
                        break;
                    }
                }
            }

            //0>-1<0 in [0,0,0,0,{-1},0,1,2,3] search swap index in backward direction
            //Find left & right Index
            if((i==0 && i<A.length-2 && A[i]<A[i+1]) ||
                    (i>0 && i<A.length-2 && A[i-1]>A[i] && A[i]<A[i+1]) ||
                    (i>0 && i==A.length-1 && A[i-1]>A[i])){
                for(int j=i-1;j>=0;j--){
                    if(A[i]<A[j]){
                        leftIndex=j;
                        rightIndex=i;
                    }
                    else{
                        break;
                    }
                }
            }

            if(leftIndex>-1 && rightIndex>-1){
                swapCount++;
                if(swapCount>1){
                    break;
                }
                tmp = A[leftIndex];
                A[leftIndex] = A[rightIndex];
                A[rightIndex] = tmp;
                i=leftIndex<rightIndex?leftIndex-1:rightIndex-1;
                leftIndex=-1;
                rightIndex=-1;
            }
        }

        return swapCount<2;
    }

    private static boolean isSorted(int[] A){
        for(int i=0;i<A.length;i++){
            if(i<A.length-1 && A[i]>A[i+1]){
                return false;
            }
        }
        return true;
    }


    /**
     * Worst space complexity
     * @param A
     * @return
     */
    public static boolean solution1(int[] A){
        if(A.length<=2){
            return true;
        }
        int[] B = A.clone();
        Arrays.sort(A);
        int diffs = 0;
        for(int i=0;i<A.length-1;i++){
            if(A[i]!=B[i]){
                diffs++;
            }
        }
        return (diffs<=2);
    }


}

