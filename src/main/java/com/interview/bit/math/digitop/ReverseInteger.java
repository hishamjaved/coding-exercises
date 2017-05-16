package com.interview.bit.math.digitop;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseInteger {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        System.out.println(reverse(in.nextInt()));
    }

    public static int reverse(int a) {
        boolean isNegative = a<0;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(Math.abs(a)));
        Long v = Long.valueOf(isNegative? "-"+sb.reverse().toString(): sb.reverse().toString());
        if(v>Integer.MAX_VALUE || v<Integer.MIN_VALUE){
            return 0;
        }
        else{
            return Integer.valueOf(v.toString());
        }
    }

}

