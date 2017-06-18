package com.codility;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Write a function:
 * <p/>
 * class Solution { public int solution(int A, int B, int K); }
 * that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:
 * <p/>
 * { i : A ? i ? B, i mod K = 0 }
 * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 * <p/>
 * Assume that:
 * <p/>
 * A and B are integers within the range [0..2,000,000,000];
 * K is an integer within the range [1..2,000,000,000];
 * A ? B.
 * Complexity:
 * <p/>
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 */

public class CountDivs {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "codility"
            + File.separator + CountDivs.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();

        System.out.println("Range: " + a + " ... " + b + "; Divider: " + k);
        System.out.println(solution(a, b, k));
    }

    /**
     * @param A
     * @param B
     * @param K
     * @return
     */
    public static int solution(int A, int B, int K) {
        if(K==0){
            return 0;
        }
        int x = (A-1)/K;
        int y = B/K;

        x=x<0?0:x;

        if(A<=0 || B<=0){
            return (y-x)+1;
        }
        else{
            return y-x;
        }
    }
}

