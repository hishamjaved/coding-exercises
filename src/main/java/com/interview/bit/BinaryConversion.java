package com.interview.bit;


import java.util.Scanner;
import java.util.stream.Collectors;

public class BinaryConversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(findDigitsInBinary(n));
    }

    /**
     * Decimal to Binary
     * @param a int
     * @return String
     */
    private static String findDigitsInBinary(int a) {
        StringBuilder result = new StringBuilder();
        if(a==0)
            return "0";
        while(a>0){
            result.append(a % 2);
            a = (int)Math.floor(a/2);
        }
        return result.reverse().toString();
    }
}

