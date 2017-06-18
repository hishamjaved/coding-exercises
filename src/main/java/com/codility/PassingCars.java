package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**

 */

public class PassingCars {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "codility"
            + File.separator + PassingCars.class.getSimpleName() + File.separator;
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


    public static int solution(int[] A) {
        int ones=0;
        for(int i=0;i<A.length;i++){
            if(A[i]==1){
                ones++;
            }
        }
        int sum=0;
        for(int i=0;i<A.length;i++){
            if(A[i]==1){
                ones--;
            }
            else{
                sum+=ones;
                if(sum>1000000000){
                    return -1;
                }
            }
        }
        return sum;
    }
}

