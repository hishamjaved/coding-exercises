package com.toptal;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Find the balance point in an array. (The index where the
 *  sum of the elements to the left it is the same as the sum
 *  of the elements to the right of it.)
 */

public class ArrayBalancePoint {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "toptal"
            + File.separator + ArrayBalancePoint.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i]=in.nextInt();
        }
        //System.out.println(balanceNaive(x));
        //System.out.println(balanceImproved(x));
        System.out.println(balanceBest(x));
    }

    /**
     * This function scored 100% and it handles all the edge cases
     * @param A
     * @return
     */
    public static int balanceBest(int[] A){
        if(A.length==0){
            return -1;
        }
        else if(A.length==1){
            return 0;
        }

        long leftSum = A[0];
        long rightSum = 0;

        for(int i=0;i<A.length;i++){
            rightSum += A[i];
        }

        for(int i=0; i<A.length-1;i++){
            if(leftSum==rightSum)
                return i;
            leftSum+=A[i+1];
            rightSum-=A[i];
        }

        leftSum -= A[A.length-1];
        if(leftSum==0){
            return A.length-1;
        }
        else{
            return -1;
        }
    }


    public static int balanceImproved(int[] x){
        int[] leftSums = new int[x.length];
        int[] rightSums = new int[x.length];
        leftSums[0] = x[0];
        rightSums[x.length-1] = x[x.length-1];
        for(int i=1;i<x.length;i++){
            leftSums[i] = leftSums[i-1]+x[i];
        }

        for(int i=x.length-2;i>=0;i--){
            rightSums[i] = rightSums[i+1]+x[i];
        }

        for(int i=0;i<x.length;i++){
            if(rightSums[i]==leftSums[i]){
                return i;
            }
        }
        return -1;
    }



    public static int balanceNaive(int[] x){
        int balancePoint = -1;
        long leftSum;
        long rightSum;
        //For each position we compute left sum and right sum of given index, return index if both leftSum and rightSum
        // are equal.
        for(int i=0;i<x.length;i++){
            leftSum=0;
            rightSum=0;
            for(int j=i;j<x.length;j++){
                rightSum+=x[j];
            }

            for(int k=0;k<=i;k++){
                leftSum+=x[k];
            }

            if(leftSum==rightSum){
                return i;
            }
        }
        return balancePoint;
    }
}

