package com.interview.bit.math.dynamicprogramming;


import java.io.*;
import java.util.*;

public class NumbersOfLengthN {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "interviewbit"
            + File.separator + NumbersOfLengthN.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=1;i<=n;i++){
            a.add(in.nextInt());
        }

        System.out.println(solve(a, in.nextInt(), in.nextInt()));
    }

    private static int solve(ArrayList<Integer> A, int B, int C) {
        ArrayList<Integer> digit;
        int d, d2;
        // Convert number to digit array
        digit = numToVec(C);
        d = A.size();

        //Case 1
        if (B > digit.size() || d == 0)
            return 0;

            // Case 2
        else if (B < digit.size()) {
            // contain 0
            if (A.get(0) == 0 && B != 1)
                return (d - 1) * (int)Math.pow(d, B - 1);
            else
                return (int)Math.pow(d, B);
        }

        //Case 3
        else {
            int[] dp = new int[ B + 1];
            int[] lower = new int[11];
            for (int i = 0; i <= B; i++)
                dp[i] = 0;
            for (int i = 0; i <= 10; i++)
                lower[i] = 0;
            for (int i = 0; i < d; i++)
                lower[A.get(i) + 1] = 1;

            for (int i = 1; i <= 10; i++)
                lower[i] = lower[i - 1] + lower[i];

            boolean flag = true;
            dp[0] = 0;
            for (int i = 1; i <= B; i++) {
                d2 = lower[digit.get(i - 1)];
                dp[i] = dp[i - 1] * d;

                // For first index we can't use 0
                if (i == 1 && A.get(0) == 0 && B != 1)
                    d2 = d2 - 1;

                //Whether (i-1) digit of generated number can be equal to (i - 1) digit of C.
                if (flag)
                    dp[i] += d2;
                //Is digit[i - 1] present in A ?
                flag = flag & (lower[digit.get(i - 1) + 1] == lower[digit.get(i - 1)] + 1);
            }
            return dp[B];
        }
    }

    private static ArrayList<Integer> numToVec(int N) {
        ArrayList<Integer> digit = new ArrayList<>();
        while(N != 0) {
            digit.add(N % 10);
            N = N / 10;
        }
        if(digit.size() == 0)
            digit.add(0);

        Collections.reverse(digit);
        return digit;
    }


}

