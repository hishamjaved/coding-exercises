package com.toptal;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * # Codility challenge for base -2 with the least significative bit at the left.
 #
 # In base -2 integers are represented by sequences of bits in the following way.
 # Bits are ordered from the least to the most significant. Sequence B of N bits
 # represents the number: sum(B[i] * (-2)^i for i = 0..N-1). The empty sequence represents 0.
 # Note that such representation is suitable for both positive and negative numbers.

 # In regular english:
 # You are given a binary number in the form of an array in base -2
 # Base -2 works in the following way:
 # Given an array A = [1,1,0,1,1]
 # The number is: 1 + (-2) + 0 + (-8) + 16 = 7. The numbers come from the following operations.
 # A[0] * -2 ^ 0 = 1 * 1 = 1
 # A[1] * -2 ^ 1 = 1 *-2 = -2
 # A[2] * -2 ^ 2 = 0
 # A[3] * -2 ^ 3 = 1 *-8 = -8
 # A[4] * -2 ^ 4 = 1 *16 = 16

 # Given A = [1,1,0,1,1] = 7 (Decimal)
 # We have to express now -7 in base -2

 # Which would be [1, 0, 0, 1]
 # A[0] * -2 ^ 0 = 1 * 1 = 1
 # A[1] * -2 ^ 1 = 0
 # A[2] * -2 ^ 2 = 0
 # A[3] * -2 ^ 3 = 1 *-8 = -8
 */

public class NegaBinary {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            +"target"+ File.separator +"classes"+ File.separator + "toptal"
            + File.separator + NegaBinary.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        ArrayList<Integer> a = new ArrayList<>();
        int n = in.nextInt();
        System.out.println(n+": "+convertToNegativeBase2(n));
        n = in.nextInt();
        System.out.println(n+": "+convertToNegativeBase2(n));
        n = in.nextInt();
        System.out.println(n+": "+convertToNegativeBase2(n));
        n = in.nextInt();
        System.out.println(n+": "+convertToNegativeBase2(n));

        String m = in.next();
        System.out.println(m+": "+convertToDecimal(m));

        m = in.next();
        System.out.println(m+": "+convertToDecimal(m));

        m = in.next();
        System.out.println(m+": "+convertToDecimal(m));

        m = in.next();
        System.out.println(m+": "+convertToDecimal(m));
    }

    public static String convertToNegativeBase2(int dec) {
        double n = (double) dec;
        ArrayList<Integer> a = new ArrayList<>();
        while(n!=0){
            a.add((int)Math.abs(n%-2));
            n = Math.ceil(n/-2);
        }

        return a.stream().map(Object::toString).collect(Collectors.joining());
    }

    public static int convertToDecimal(String negBase2) {
        int dec =0;
        for (int i = 0; i < negBase2.length(); i++) {
            dec += Integer.valueOf(String.valueOf(negBase2.charAt(i))) * Math.pow(-2, i);
        }
        return dec;
    }
}

