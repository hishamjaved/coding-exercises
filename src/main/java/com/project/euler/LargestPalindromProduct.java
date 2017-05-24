package com.project.euler;

import java.io.*;
import java.util.Scanner;

/**
 * Largest palindrome product
 * Problem 4
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p/>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

public class LargestPalindromProduct {


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(solution());
    }


    /**
     * O(n * log n)
     *
     * @return
     */
    public static long solution() {
        int product = 0;
        int maxProduct = 0;
        int k=0;
        for (int i = 999; i > 99; i--) {
            for (int j = 999; j > 99; j--) {
                product = i * j;
                if (product > maxProduct && isPalindrom(product)) {
                    maxProduct = product;
                    break;
                }
            }
        }
        return maxProduct;
    }

    private static boolean isPalindrom(long product) {
        String p = String.valueOf(product);
        return new StringBuilder(p).reverse().toString().equals(p);
    }

}

