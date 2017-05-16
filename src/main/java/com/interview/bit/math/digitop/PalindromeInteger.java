package com.interview.bit.math.digitop;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class PalindromeInteger {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println(isPalindrome(in.nextInt()));
    }

    public static boolean isPalindrome(int a) {
        StringBuilder sb = new StringBuilder(String.valueOf(a));
        return sb.reverse().toString().equals(String.valueOf(a));
    }

}

