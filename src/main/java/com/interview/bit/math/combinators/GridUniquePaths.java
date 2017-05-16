package com.interview.bit.math.combinators;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GridUniquePaths {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println(findPaths(in.nextInt(),in.nextInt()));
    }

    private static int findPaths(int a,int b) {
        if(a==0 || b==0) return 0;
        if(a==1 || b==1) return 1;

        int[][] dp = new int[a][b];

        //left column
        for(int i=0; i<a; i++){
            dp[i][0] = 1;
        }

        //printGrid(dp);
        //top row
        for(int j=0; j<b; j++){
            dp[0][j] = 1;
        }
        //printGrid(dp);


        //fill up the dp table
        for(int i=1; i<a; i++){
            for(int j=1; j<b; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                //printGrid(dp);
            }

        }

        return dp[a-1][b-1];
    }

    private static void printGrid(int[][] dp){
        for(int i=0; i<dp.length; i++){
            System.out.print("| ");
            for(int j=0; j<dp[0].length; j++){
                System.out.print(dp[i][j]+" | ");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------");
    }



}

