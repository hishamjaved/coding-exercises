package com.toptal;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 *Returns the number of integers within the range [A..B] that are divisible by K.
 Used generators to save memory on large amounts of data.
 Args:
 - A: is an integer within the range [0..2,000,000,000]
 - B: is an integer within the range [0..2,000,000,000] and A <= B
 - K: is an integer within the range [1..2,000,000,000]
 */

public class CountDivisibleByK {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "toptal"
            + File.separator + CountDivisibleByK.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        System.out.println(solution(a,b,k));
    }


    /**
     * Efficient Solution O(1)
     * @param A
     * @param B
     * @param K
     * @return
     */
    public static int solution(int A,int B,int K) {
        if(K==0){
            return 0;
        }
        if(A>B){
            return 0;
        }

        int i;
        for (i=A;i<B;i++){
            // Find the first divisor greater than A
            if (i%K==0){
                break;
            }
        }
        int t=0;
        if (i<B){
            t=(int)Math.ceil((double)(B-i)/(double)K);
        }

        if ((B%K)==0){
            t++;
        }
        return t;
    }

    /**
     * Bad O(n) solution
     * @param A
     * @param B
     * @param K
     * @return
     */
    public static int bruteForceSolution(int A,int B,int K) {
        if(K==0){
            return 0;
        }
        if(A>B){
            return 0;
        }

        int divs_count = 0;
        for(int x=A;x<=B;x++){
            if(x % K == 0){
                divs_count++;
            }
        }
        return divs_count;
    }
}

